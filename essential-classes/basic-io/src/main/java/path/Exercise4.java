package path;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributeView;
import java.nio.file.attribute.BasicFileAttributes;

/**
 * @author jknetl
 */
public class Exercise4 {

    public static final String HELLO_FILE = "essential-classes/basic-io/src/main/resources/path/ex1/hello.txt";

    public static void main(String[] args) throws IOException {

        Path helloFile = Paths.get(HELLO_FILE);

        //you may check single attributes using various Files methods:
        System.out.printf("File %s exists: %b%n", helloFile, Files.exists(helloFile));
        System.out.printf("File %s is a regular file: %b%n", helloFile, Files.isRegularFile(helloFile));
        System.out.printf("File %s is a directory: %b%n", helloFile, Files.isDirectory(helloFile));
        System.out.printf("File %s is a readable: %b%n", helloFile, Files.isReadable(helloFile));
        System.out.printf("File %s is a writeable: %b%n", helloFile, Files.isWritable(helloFile));
        System.out.printf("File %s is a executable: %b%n", helloFile, Files.isExecutable(helloFile));
        System.out.printf("File %s is a executable: %b%n", helloFile, Files.isExecutable(helloFile));
        System.out.printf("File %s size: %d B%n", helloFile, Files.size(helloFile));
        System.out.printf("File %s owner: %s%n", helloFile, Files.getOwner(helloFile));
        System.out.printf("File %s last modified on: %tD%n", helloFile, Files.getLastModifiedTime(helloFile).toMillis());

        // you may get arbitrary attribute by requesting its name in form "view:attribute-name", e.g.

        System.out.printf("");

        System.out.printf("File %s size fetched using attribute-string: %d B%n", helloFile, Files.getAttribute(helloFile, "basic:size"));

        // you may get all atributes of given type as a bulk operation (more efficient if you need to work with multiple attributes)
        BasicFileAttributes attributes = Files.readAttributes(helloFile, BasicFileAttributes.class);
        System.out.printf("File %s last accessed on: %tD%n", helloFile, attributes.lastAccessTime().toMillis());
    }
}
