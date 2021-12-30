package steps;

import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;
import steg.FileManager;
import steg.Stegano;
import steg.SteganoLSB;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class WrongOutputFileFormatSteps {

    private final FileManager fileManager = new FileManager();
    private final Stegano stegano = new SteganoLSB();
    private final String path = "src/test/to_test_pics/200x200/";

    private BufferedImage source;
    private BufferedImage secret;
    private BufferedImage encoded;

    @Given("correct paths to source and secret image")
    public void readingCorrectFiles() throws IOException {
        System.out.println(new File(path + "200x200_BW_COWS.png").getAbsolutePath());
        source = fileManager.readFile(path + "200x200_BW_COWS.png");
        secret = fileManager.readFile(path + "200x200_COLOR_COWS.png");
    }

    @When("secret image is encoded into source image")
    public void encoding() {
        encoded = stegano.encode(source, secret);
    }

    @Then("saving image as a text file should inform about wrong file format")
    public void savingEncodedAsTextFileShouldThrowException() {
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> fileManager.saveFile(encoded, path + "encoded.txt")
        );
        assertEquals("Unsupported filename extension.", exception.getMessage());
    }

}
