import org.junit.jupiter.api.Test;

import javax.imageio.ImageIO;

import static org.junit.jupiter.api.Assertions.*;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class SteganoLSBTest {

    private final Stegano stegano = new SteganoLSB();

    //ENCODE TESTS

    @Test
    public void shouldThrowIllegalArgumentExceptionWhenGivenNullImage() {
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> stegano.encode(null, null)
        );
        assertEquals("Passed images cannot be null", exception.getMessage());
    }

    @Test
    public void shouldWorkProperlyWhenGivenBothColorImages() throws IOException {
        BufferedImage source = ImageIO.read(new File("src/test/to_test_pics/200x200/200x200_COLOR_COWS.png"));
        BufferedImage secret = ImageIO.read(new File("src/test/to_test_pics/200x200/200x200_COLOR_HORSE.png"));

        are2YoungestEqual(stegano.encode(source, secret), convertTo2bitImage(secret));
    }

    @Test
    public void shouldThrowIllegalArgumentExceptionWhenGivenBothTransparentImages() throws IOException {
        BufferedImage source = ImageIO.read(new File("src/test/to_test_pics/200x200/200x200_BW_TRANSPARENT_COWS.png"));
        BufferedImage secret = ImageIO.read(new File("src/test/to_test_pics/200x200/200x200_COLOR_TRANSPARENT_HORSE.png"));

        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> stegano.encode(source, secret)
        );
        assertEquals("Passed images cannot contain alpha transparency", exception.getMessage());
    }

    @Test
    public void shouldWorkProperlyWhenGivenBothBWImages() throws IOException {
        BufferedImage source = ImageIO.read(new File("src/test/to_test_pics/2000x600/2000x600_BW_MOUNTAINS.png"));
        BufferedImage secret = ImageIO.read(new File("src/test/to_test_pics/2000x600/2000x600_BW_MIX.png"));

        are2YoungestEqual(stegano.encode(source, secret), convertTo2bitImage(secret));
    }

    @Test
    public void shouldWorkProperlyWhenGivenBWandColorImages() throws IOException {
        BufferedImage source = ImageIO.read(new File("src/test/to_test_pics/2000x600/2000x600_BW_MOUNTAINS.png"));
        BufferedImage secret = ImageIO.read(new File("src/test/to_test_pics/2000x600/2000x600_COLOR_MIX.png"));

        are2YoungestEqual(stegano.encode(source, secret), convertTo2bitImage(secret));
    }

    @Test
    public void shouldThrowIllegalArgumentExceptionWhenGivenBWandTransparentImages() throws IOException {
        BufferedImage source = ImageIO.read(new File("src/test/to_test_pics/200x200/200x200_BW_HORSE.png"));
        BufferedImage secret = ImageIO.read(new File("src/test/to_test_pics/200x200/200x200_COLOR_TRANSPARENT_HORSE.png"));

        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> stegano.encode(source, secret)
        );
        assertEquals("Passed images cannot contain alpha transparency", exception.getMessage());
    }

    @Test
    public void shouldThrowIllegalArgumentExceptionWhenGivenColorAndTransparentImages() throws IOException {
        BufferedImage source = ImageIO.read(new File("src/test/to_test_pics/200x200/200x200_COLOR_COWS.png"));
        BufferedImage secret = ImageIO.read(new File("src/test/to_test_pics/200x200/200x200_COLOR_TRANSPARENT_HORSE.png"));

        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> stegano.encode(source, secret)
        );
        assertEquals("Passed images cannot contain alpha transparency", exception.getMessage());
    }
    //DECODE TESTS

    @Test
    public void shouldThrowIllegalArgumentExceptionWhenGivenNullEncodedImage() {
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> stegano.decode(null)
        );
        assertEquals("Passed image cannot be null", exception.getMessage());
    }

    @Test
    public void shouldWorkProperlyWhenGivenBWEncodedImage200x200() throws IOException {
        BufferedImage source = ImageIO.read(new File("src/test/to_test_pics/200x200/200x200_BW_COWS.png"));
        BufferedImage secret = ImageIO.read(new File("src/test/to_test_pics/200x200/200x200_BW_HORSE.png"));
        BufferedImage convertedSecret = convertTo2bitImage(secret);

        BufferedImage encoded = stegano.encode(source, secret);
        BufferedImage decoded = stegano.decode(encoded);

        checkEquality(convertedSecret, decoded);

    }

    @Test
    public void shouldWorkProperlyWhenGivenBWEncodedImage2000x600() throws IOException {
        BufferedImage source = ImageIO.read(new File("src/test/to_test_pics/2000x600/2000x600_BW_MOUNTAINS.png"));
        BufferedImage secret = ImageIO.read(new File("src/test/to_test_pics/2000x600/2000x600_BW_MIX.png"));
        BufferedImage convertedSecret = convertTo2bitImage(secret);

        BufferedImage encoded = stegano.encode(source, secret);
        BufferedImage decoded = stegano.decode(encoded);

        checkEquality(convertedSecret, decoded);

    }

    @Test
    public void shouldWorkProperlyWhenGivenColorEncodedImage200x200() throws IOException {
        BufferedImage source = ImageIO.read(new File("src/test/to_test_pics/200x200/200x200_COLOR_COWS.png"));
        BufferedImage secret = ImageIO.read(new File("src/test/to_test_pics/200x200/200x200_COLOR_HORSE.png"));
        BufferedImage convertedSecret = convertTo2bitImage(secret);

        BufferedImage encoded = stegano.encode(source, secret);
        BufferedImage decoded = stegano.decode(encoded);

        checkEquality(convertedSecret, decoded);
    }

    @Test
    public void shouldWorkProperlyWhenGivenColorEncodedImage2000x600() throws IOException {
        BufferedImage source = ImageIO.read(new File("src/test/to_test_pics/2000x600/2000x600_COLOR_MOUNTAINS.png"));
        BufferedImage secret = ImageIO.read(new File("src/test/to_test_pics/2000x600/2000x600_COLOR_MIX.png"));
        BufferedImage convertedSecret = convertTo2bitImage(secret);

        BufferedImage encoded = stegano.encode(source, secret);
        BufferedImage decoded = stegano.decode(encoded);

        checkEquality(convertedSecret, decoded);
    }

    @Test
    public void shouldThrowIllegalArgumentExceptionWhenGivenTransparentEncodedImage200x200() throws IOException {
        BufferedImage encoded = ImageIO.read(new File("src/test/to_test_pics/200x200/200x200_BW_TRANSPARENT_COWS.png"));

        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> stegano.decode(encoded)
        );
        assertEquals("Passed image cannot contain alpha transparency", exception.getMessage());
    }

    @Test
    public void shouldThrowIllegalArgumentExceptionWhenGivenTransparentEncodedImage2000x600() throws IOException {
        BufferedImage encoded = ImageIO.read(new File("src/test/to_test_pics/2000x600/2000x600_COLOR_TRANSPARENCY_MOUNTAINS.png"));

        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> stegano.decode(encoded)
        );
        assertEquals("Passed image cannot contain alpha transparency", exception.getMessage());
    }

    //HELPER FUNCTIONS

    //checks if last two bits of every pixel in encoded image are the same as secret image pixel bytes
    private void are2YoungestEqual(BufferedImage encoded, BufferedImage secret) {
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
        return Math.round(((float) value / 3) * 255);
    }

    private void checkEquality(BufferedImage expected, BufferedImage actual) {

        assertEquals(expected.getWidth(), actual.getWidth());
        assertEquals(expected.getHeight(), actual.getHeight());

        for (int w = 0; w < expected.getWidth(); w++) {
            for (int h = 0; h < expected.getHeight(); h++) {
                assertEquals(expected.getRGB(w, h), actual.getRGB(w, h));
            }
        }
    }

}
