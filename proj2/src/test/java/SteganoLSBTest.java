import static org.junit.jupiter.api.Assertions.*;

import java.awt.*;
import java.awt.image.BufferedImage;

public class SteganoLSBTest {

    //checks if last two bits of every pixel in encoded image are the same as secret image pixel bytes
    private void are2latestEqual(BufferedImage encoded, BufferedImage secret) {
        BufferedImage twoBitSecret = convertTo2bitImage(secret);

        for (int w = 0; w < encoded.getWidth(); w++) {
            for (int h = 0; h < encoded.getHeight(); h++) {
                Color encColor = new Color(encoded.getRGB(w, h));
                Color secColor = new Color(twoBitSecret.getRGB(w, h));

                assertEquals(convertFrom8To2BitValue(secColor.getRed()), convertFrom2BitTo8BitValue(encColor.getRed() % 4));
                assertEquals(convertFrom8To2BitValue(secColor.getGreen()), convertFrom2BitTo8BitValue(encColor.getGreen() % 4));
                assertEquals(convertFrom8To2BitValue(secColor.getBlue()), convertFrom2BitTo8BitValue(encColor.getBlue() % 4));
                assertEquals(convertFrom8To2BitValue(secColor.getAlpha()), convertFrom2BitTo8BitValue(encColor.getAlpha() % 4));
            }
        }
    }

    //converts image to converted image (8bit to 2bit values)
    private BufferedImage convertTo2bitImage(BufferedImage image) {

        for (int w = 0; w < image.getWidth(); w++) {
            for (int h = 0; h < image.getHeight(); h++) {
                Color c = new Color(image.getRGB(w, h));
                int r = convertFrom8To2BitValue(c.getRed());
                int g = convertFrom8To2BitValue(c.getGreen());
                int b = convertFrom8To2BitValue(c.getBlue());
                int a = convertFrom8To2BitValue(c.getAlpha());

                Color newColor = new Color(r, g, b, a);
                image.setRGB(w, h, newColor.getRGB());
            }
        }
        return image;
    }

    //conversion from 8 to 2 bit value - returns int 0-255
    private int convertFrom8To2BitValue(int value) {
        int twoBit = Math.round(((float) value / 255) * 3);
        return Math.round(((float) twoBit / 3) * 255);
    }

    //conversion from 2 (0-3) to 8 bit value - returns int 0-255
    private int convertFrom2BitTo8BitValue(int value) {
        return Math.round(((float)value / 3) * 255 );
    }


}
