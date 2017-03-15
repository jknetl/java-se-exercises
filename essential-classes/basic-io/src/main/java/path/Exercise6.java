package path;

import java.io.IOException;
import java.nio.file.FileSystem;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.UserPrincipal;

/**
 * @author jknetl
 */
public class Exercise6 {

    /**
     *
     * This exercise changes owner of file.
     *
     * Run this exercise with two arguments. First is owner name and second is path to file.
     *
     * For example: username /path/to/file
     *
     * @author jknetl
     */
    public static void main(String[] args) throws IOException {

        if (args.length != 2){
            System.out.println("Launch this script with two arguments (owner and path to file).");
        }
        Path file = Paths.get(args[1]);

        UserPrincipal principal = Files.getOwner(file);
        System.out.printf("File %s is owned by user %s.%n", file, principal.getName());

        UserPrincipal newOwner = file.getFileSystem().getUserPrincipalLookupService().lookupPrincipalByName(args[0]);
        System.out.printf("Setting owner of a file to: %s", newOwner.getName());
        Files.setOwner(file,newOwner);
    }
}
