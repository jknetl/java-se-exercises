package path;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

/**
 * @author jknetl
 */
public class Exercise8 {

    /**
     * Start a program. An argument with path to file is expected. New file with .out extension will be created.
     *
     * @param args
     */
    public static void main(String[] args) throws IOException {

        if (args.length != 1) {
            System.out.println("Argument is missing (path-to-file)");
            System.exit(1);
        }

        Path file = Paths.get(args[0]);

        if (!Files.exists(file)) {
            System.out.printf("File %s does not exists.%n", file);
            System.exit(2);
        }

        Path outFile = file.resolveSibling(file.getFileName() + ".out");

        if (Files.size(file) < 1024) {
            byte[] content = Files.readAllBytes(file);
            Files.write(outFile, content, StandardOpenOption.CREATE);
        } else {

            try (
                    InputStream is = Files.newInputStream(file);
                    OutputStream os = Files.newOutputStream(outFile)
            ) {
                byte[] buffer = new byte[1024];

                int numOfChars = 0;
                while ((numOfChars = is.read(buffer)) > 0) {
                    os.write(buffer,0, numOfChars);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

        System.out.printf("File %s has been written", outFile);

    }
}
