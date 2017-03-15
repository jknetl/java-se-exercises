package path;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * @author jknetl
 */
public class Exercise3 {

    public static void main(String[] args) throws IOException {
        if (args.length < 1) {
            System.out.printf("You must provide at least one argument with path to file which should be deleted.%n");
            System.exit(1);
        }
        for (Path p : getInputFiles(args)) {
            Files.delete(p);
            System.out.printf("File %s have been deleted.%n",p.toAbsolutePath());
            // you may also use deleteIfExists to avoid NoSuchFileException if no such file exists:
            // Files.deleteIfExists(p);
        }
    }

    private static Path[] getInputFiles(String args[]) {

        Path[] result = new Path[args.length];
        for (int i = 0; i < args.length; i++) {
            result[i] = Paths.get(args[i]);
        }

        return result;
    }
}
