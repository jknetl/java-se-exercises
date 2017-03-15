package path;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * @author jknetl
 */
public class Exercise10 {

    public static void main(String[] args) {
        Path dir;
        if (args.length == 0) {
            // get first filesystem root
            dir = FileSystems.getDefault().getRootDirectories().iterator().next();
        } else {
            dir = Paths.get(args[0]);
        }

        if (!Files.exists(dir)) {
            System.out.println("Directory does not exists.");
            System.exit(2);
        }
        if (!Files.isDirectory(dir)) {
            System.out.println("Path does not point to directory.");
            System.exit(3);
        }

        String globFilter = (args.length >= 2) ? args[1] : null;

        try (DirectoryStream<Path> ds = createDirStream(dir, globFilter);) {
            for (Path file : ds) {
                System.out.println(file);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static DirectoryStream<Path> createDirStream(Path dir, String globFilter) throws IOException {
        if (globFilter == null){
            return Files.newDirectoryStream(dir);
        } else {
            return Files.newDirectoryStream(dir, globFilter);
        }
    }

}
