package path;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SeekableByteChannel;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

/**
 * @author jknetl
 */
public class Exercise9 {

    private static final String INSERTED_STRING = "------------ middle of a file ---------------";

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


        try (
                SeekableByteChannel byteChannel = Files.newByteChannel(file, StandardOpenOption.WRITE, StandardOpenOption.WRITE);
        ) {
            long length = Files.size(file);
            long middle = length/2;

            byteChannel.position(middle);
            ByteBuffer bf = ByteBuffer.wrap(INSERTED_STRING.getBytes());
            byteChannel.write(bf);
            System.out.printf("String %s written to the file %s", INSERTED_STRING, file);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
