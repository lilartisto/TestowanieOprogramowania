package steg;



import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

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

    public void saveFile(BufferedImage image, String path) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

}
