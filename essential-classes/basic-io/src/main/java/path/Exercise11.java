package path;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * @author jknetl
 */
public class Exercise11 {

    public static void main(String[] args) {

        if (args.length != 1) {
            System.out.println("Argument is missing (path-to-file)");
            System.exit(1);
        }
        Path file = Paths.get(args[0]);

        if (!Files.exists(file)) {
            System.out.println("File does not exists");
            System.exit(2);
        }

        try {
            Path symlink = file.resolveSibling(file.getFileName() + ".symlink.tmp");
            Files.createSymbolicLink(symlink, file);
            System.out.printf("Symbolic link created: %s->%s%n", symlink, file);
            if (!Files.isDirectory(file)) {
                Path hardlink = file.resolveSibling(file.getFileName() + ".hardlink.tmp");
                Files.createLink(hardlink, file);
                System.out.printf("Hard link created: %s->%s%n", hardlink, file);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
