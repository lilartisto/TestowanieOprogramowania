import org.junit.jupiter.api.Test;

import java.awt.*;
import java.awt.image.BufferedImage;

public class SteganoLSBTest {

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

    private int convertFrom8To2BitValue(int value) {
        int twoBit = Math.round(((float) value / 255) * 3);
        return Math.round(((float) twoBit / 3) * 255);
    }


}
