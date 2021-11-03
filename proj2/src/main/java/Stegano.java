import java.awt.image.BufferedImage;

public interface Stegano {

    BufferedImage encode(BufferedImage source, BufferedImage secret);

    BufferedImage decode(BufferedImage encoded);

}
