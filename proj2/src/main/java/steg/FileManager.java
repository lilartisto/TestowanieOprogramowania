package steg;



import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;

public class FileManager {
    SteganoLSB steganoLSB = new SteganoLSB();

    public BufferedImage readFile(String path) throws IOException {
        return ImageIO.read(new File(path));
    }

    public BufferedImage readForEncode(String source_path, String secret_path) throws IOException {
        return steganoLSB.encode(readFile(source_path), readFile(secret_path));
    }

    public BufferedImage readForDecode(String encoded_path) throws IOException {
        return steganoLSB.decode(readFile(encoded_path));
    }

    public void saveFile(BufferedImage image, String path) throws IOException, IllegalArgumentException {
        if (!path.contains(".")) {
            throw new IllegalArgumentException("File path must contain an extension.");
        }

        String extension = path.substring(path.lastIndexOf(".") + 1);
        // WHY DOES IMAGEWRITER NOT HAVE .GETFORMATNAME()
        Iterator<ImageReader> readers = ImageIO.getImageReadersBySuffix(extension);
        if (!readers.hasNext()) {
            throw new IllegalArgumentException("Unsupported filename extension.");
        }

        ImageIO.write(image, readers.next().getFormatName(), new File(path));
    }

}
