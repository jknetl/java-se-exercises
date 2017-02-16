package path;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * @author jknetl
 */
public class Exercise1 {

    public static final String RESOURCES_PREFIX = "essential-classes/basic-io/src/main/resources";

    public static void main(String[] args) throws URISyntaxException, IOException {
        Path exerciseDir = Paths.get(RESOURCES_PREFIX, "path/ex1");

        System.out.format("Directory for this exercise: %s%n", exerciseDir);

        // you may also use URI
        Path currentWorkingDir = Paths.get("");
        String uriString = "file://" + currentWorkingDir.toAbsolutePath().toString() + FileSystems.getDefault().getSeparator() + RESOURCES_PREFIX + "/path/ex1";
        Path exerciseDir2 = Paths.get(new URI(uriString));

        // comparing if the paths are same (they point to same path but they are not the same since the second one is absolute path)
        System.out.format("Are boths paths same (%s,%s)? Result: %b%n", exerciseDir, exerciseDir2, exerciseDir.equals(exerciseDir2));

        // you may get various info about path:
        Path helloFile = exerciseDir.resolve("hello.txt");
        System.out.format("filename: %s%n", helloFile.getFileName());
        System.out.format("names in the path: %s%n", helloFile.getNameCount());

        // you may even iterate over paths:

        for (Path p : helloFile){
            System.out.println("Element in path: " + p);
        }

        Path usualFile = exerciseDir.resolve("dir2/usual-file");

        // you may also create a path between paths:
        Path fromExerciseToUsualFile =  exerciseDir.relativize(usualFile);
        System.out.printf("Path from exercise dir to usual-file: %s%n", fromExerciseToUsualFile);

        // symlink
        Path symlink = exerciseDir.resolve("important-file-symlink");
        System.out.format("file %s is symlink: %b%n", symlink, Files.isSymbolicLink(symlink));
        if (Files.isSymbolicLink(symlink)) {
            Path target = Files.readSymbolicLink(symlink);
            System.out.printf("Symlink content: %s-->%s%n", symlink, Files.readSymbolicLink(symlink));
        }


    }
}
