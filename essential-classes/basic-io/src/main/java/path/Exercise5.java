package path;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.FileAttribute;
import java.nio.file.attribute.PosixFileAttributes;
import java.nio.file.attribute.PosixFilePermission;
import java.nio.file.attribute.PosixFilePermissions;
import java.util.Iterator;
import java.util.Set;

/**
 *
 * Run this exercise with two argumetns. For example: rwxr-x-r-- /path/to/file
 * @author jknetl
 */
public class Exercise5 {

    public static void main(String[] args) throws IOException {

        if (args.length != 2) {
            System.out.println("2 arguments are expected.");
        }

        Path file = Paths.get(args[1]);

        PosixFileAttributes attributes = Files.readAttributes(file, PosixFileAttributes.class);
        Set<PosixFilePermission> permissions = attributes.permissions();
        System.out.format("File %s has had following permissions: %s%n", file, PosixFilePermissions.toString(permissions));

        Set<PosixFilePermission> newPermissions = PosixFilePermissions.fromString(args[0]);

        System.out.format("Changing permisions to: %s%n", PosixFilePermissions.toString(newPermissions));
        Files.setPosixFilePermissions(file, newPermissions);

    }
}
