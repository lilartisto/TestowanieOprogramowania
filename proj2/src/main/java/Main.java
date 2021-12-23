import steg.FileManager;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Scanner;

public class Main {

    private static final FileManager fileManager = new FileManager();

    public static void main(String[] args) {
        try {
            if (args.length < 1)
                throw new IOException("Wrong number of arguments [isEncode(0 or 1) | sourcePath | (secretPath) | savePath]");
            boolean encode = args[0].equals("1");
            boolean decode = args[0].equals("0");
            if (encode)
                proceedEncode(args);
            else if (decode)
                proceedDecode(args);
            else
                throw new IOException("Wrong isEncode flag");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Try again:");
            Scanner scanner = new Scanner(System.in);
            String[] arguments = scanner.nextLine().split(" ");
            main(arguments);
        }
    }

    public static void proceedEncode(String[] args) throws IOException {
        if (args.length != 4)
            throw new IOException("Wrong number of arguments [isEncode(0 or 1) | sourcePath | secretPath | savePath]");
        String sourcePath = args[1];
        String secretPath = args[2];
        String savePath = args[3];

        BufferedImage encoded = fileManager.readForEncode(sourcePath, secretPath);

        fileManager.saveFile(encoded, savePath);
    }

    public static void proceedDecode(String[] args) throws IOException {
        if (args.length != 3)
            throw new IOException("Wrong number of arguments [isEncode(0 or 1) | sourcePath | savePath]");
        String sourcePath = args[1];
        String savePath = args[2];

        BufferedImage decoded = fileManager.readForDecode(sourcePath);

        fileManager.saveFile(decoded, savePath);
    }
}
