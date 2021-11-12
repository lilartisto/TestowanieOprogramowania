import java.awt.*;
import java.awt.image.BufferedImage;

public class SteganoLSB implements Stegano {

    @Override
    public BufferedImage encode(BufferedImage source, BufferedImage secret) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    @Override
    public BufferedImage decode(BufferedImage encoded) {
        if (encoded == null) {
            throw new IllegalArgumentException("Passed image cannot be null");
        }

        return decodeImg(encoded);
    }

    private BufferedImage decodeImg(BufferedImage encoded) {
        int width = encoded.getWidth();
        int height = encoded.getHeight();
        BufferedImage decodedImg = new BufferedImage(width, height, encoded.getType());

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                decodedImg.setRGB(x, y, decodeRGBPixel(decodedImg.getRGB(x, y)));
            }
        }

        return decodedImg;
    }

    private int decodeRGBPixel(int pixel) {
        Color pixelColor = new Color(pixel);

        return new Color(
                normalize2lsbTo8b(pixelColor.getRed()),
                normalize2lsbTo8b(pixelColor.getGreen()),
                normalize2lsbTo8b(pixelColor.getBlue()),
                normalize2lsbTo8b(pixelColor.getAlpha())
        ).getRGB();
    }

    private int normalize2lsbTo8b(int number) {
        return (number % 4) * 255 / 3;
    }

}
