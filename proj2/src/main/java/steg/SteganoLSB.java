package steg;

import steg.Stegano;

import java.awt.*;
import java.awt.image.BufferedImage;

public class SteganoLSB implements Stegano {

    private boolean isAlpha(BufferedImage img) {
        for (int x = 0; x < img.getWidth(); x++) {
            for (int y = 0; y < img.getHeight(); y++) {
                int alpha = img.getRGB(x, y) >>> 24;

                if (alpha != 255) {
                    return true;
                }
            }
        }

        return false;
    }

    @Override
    public BufferedImage encode(BufferedImage source, BufferedImage secret) {
        if (source == null || secret == null) {
            throw new IllegalArgumentException("Passed images cannot be null");
        }
        if (isAlpha(source) || isAlpha(secret)) {
            throw new IllegalArgumentException("Passed images cannot contain alpha transparency");
        }

        return encodeImg(source, secret);
    }

    private BufferedImage encodeImg(BufferedImage src, BufferedImage secret) {
        int width = src.getWidth();
        int height = src.getHeight();
        BufferedImage encodedImg = new BufferedImage(width, height, src.getType());

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                encodedImg.setRGB(x, y, encodeRGBPixel(
                        src.getRGB(x, y), secret.getRGB(x, y)
                ));
            }
        }

        return encodedImg;
    }

    private int encodeRGBPixel(int srcPixel, int secretPixel) {
        Color srcColor = new Color(srcPixel);
        Color secretColor = new Color(secretPixel);

        return new Color(
                encode8bAs2b(srcColor.getRed(), secretColor.getRed()),
                encode8bAs2b(srcColor.getGreen(), secretColor.getGreen()),
                encode8bAs2b(srcColor.getBlue(), secretColor.getBlue()),
                encode8bAs2b(srcColor.getAlpha(), secretColor.getAlpha())
        ).getRGB();
    }

    private int encode8bAs2b(int src, int secret) {
        int secret2b = Math.round(((float)secret / 255) * 3);

        return (src & 252) | secret2b;
    }

    @Override
    public BufferedImage decode(BufferedImage encoded) {
        if (encoded == null) {
            throw new IllegalArgumentException("Passed image cannot be null");
        }
        if (isAlpha(encoded)) {
            throw new IllegalArgumentException("Passed image cannot contain alpha transparency");
        }

        return decodeImg(encoded);
    }

    private BufferedImage decodeImg(BufferedImage encoded) {
        int width = encoded.getWidth();
        int height = encoded.getHeight();
        BufferedImage decodedImg = new BufferedImage(width, height, encoded.getType());

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                decodedImg.setRGB(x, y, decodeRGBPixel(encoded.getRGB(x, y)));
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
