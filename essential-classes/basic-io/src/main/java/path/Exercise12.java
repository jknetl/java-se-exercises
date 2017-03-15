package path;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.FileVisitor;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;

/**
 * Finds a file by name recursively starting from current directory.
 *
 * @author jknetl
 */
public class Exercise12 {

    public static void main(String[] args) throws IOException {

        if (args.length != 1) {
            System.out.printf("Pass a file name to be found as a first argument");
            System.exit(1);
        }

        Path currentDir = Paths.get("");

        FileVisitor<Path> visitor = new FileFinder(args[0]);

        Files.walkFileTree(currentDir, visitor);
    }

    public static class FileFinder extends SimpleFileVisitor<Path> {

        private String pattern;

        public FileFinder(String pattern) {
            this.pattern = pattern;
        }

        @Override
        public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
            if (file.getFileName().toString().equals(pattern)) {
                System.out.printf("File found: %s%n", file);
            }
            return super.visitFile(file, attrs);
        }
    }
}
