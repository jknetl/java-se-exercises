package path;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.UserDefinedFileAttributeView;

/**
 * @author jknetl
 */
public class Exercise7 {

    private static final String USER_DEFINED_ATTRIBUTE = "java-se-exercises";

    /**
     * This exercise changes user-defined attribute of a file.
     * <p>
     * Run this exercise with a path to file as an argument.
     *
     * @author jknetl
     */
    public static void main(String[] args) throws IOException {

        if (args.length != 1) {
            System.out.println("Launch this script with two arguments (owner and path to file).");
        }
        Path file = Paths.get(args[0]);

        System.out.printf("This program reads and writes attribute %s to a file with its value. If you are on linux, you need to mount filesystem with enabled extended attributes%n", USER_DEFINED_ATTRIBUTE);

        UserDefinedFileAttributeView attributeView = Files.getFileAttributeView(file, UserDefinedFileAttributeView.class);

        ByteBuffer buffer = ByteBuffer.allocate(100);
        int bytes = 0;
        try {
            bytes = attributeView.read(USER_DEFINED_ATTRIBUTE, buffer);
        } catch (IOException e) {
            //there is no such attribute on the file, we will write it
            System.out.printf("There is no attribute '%s' defined on the file %s.%n", USER_DEFINED_ATTRIBUTE, file);
            System.out.printf("Writing attribute '%s' defined on the file %s to value true.%n", USER_DEFINED_ATTRIBUTE, file);
            attributeView.write(USER_DEFINED_ATTRIBUTE, Charset.defaultCharset().encode("true"));
        }

        buffer.flip();
        String value = Charset.defaultCharset().decode(buffer).toString();
        System.out.printf("File %s has attribute %s set to: %s%n", file, USER_DEFINED_ATTRIBUTE, value);


    }
}
