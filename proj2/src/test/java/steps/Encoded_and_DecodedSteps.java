package steps;

import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;
import steg.FileManager;
import steg.Stegano;
import steg.SteganoLSB;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class Encoded_and_DecodedSteps {
    private final FileManager fileManager = new FileManager();
    private final Stegano stegano = new SteganoLSB();
    private final String path = "src/test/to_test_pics/2000x600/";

    private BufferedImage source;
    private BufferedImage secret;
    private BufferedImage encoded;

    @Given("correct paths to source and secret image")
    public void readingCorrectFiles() throws IOException {
        source = fileManager.readFile(path + "2000x600_BW_MIX.png");
        secret = fileManager.readFile(path + "2000x600_BW_MOUNTAINS.png");
    }

    @When("secret image is encoded into source image")
    public void encoding() {
        encoded = stegano.encode(source, secret);
    }

    @Then("saving image should give properly encoded image")
    public void encodedLast2BitsShouldBeEqualToSourceConvertedTo2BitImage() {
        are2YoungestEqual(encoded, convertTo2bitImage(secret));

    }

    @Then("result image should be properly decoded into 2 bit image")
    public void decodedImageShouldBeEqualToSecretConvertedTo2BitImage() {
        BufferedImage expected = convertTo2bitImage(secret);

        checkEquality(expected, stegano.decode(encoded));
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
