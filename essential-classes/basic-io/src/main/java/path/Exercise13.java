package path;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardWatchEventKinds;
import java.nio.file.WatchEvent;
import java.nio.file.WatchEvent.Kind;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;
import java.util.List;

import static java.nio.file.StandardWatchEventKinds.*;

/**
 * @author jknetl
 */
public class Exercise13 {

    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("Argument with path to a directory is missing.");
            System.exit(1);
        }

        Path dir = Paths.get(args[0]);

        if (!Files.exists(dir) || !Files.isDirectory(dir)) {
            System.out.printf("%s is not a directory.%n", dir);
            System.exit(2);
        }

        try (WatchService watchService = dir.getFileSystem().newWatchService()){
            WatchKey registerKey = dir.register(watchService, ENTRY_CREATE, ENTRY_MODIFY, ENTRY_DELETE);
            System.out.printf("Watching dir %s for changes%n", dir);

            // infinite event loop
            while (true){
                WatchKey key = watchService.take();

                for (WatchEvent<?> e : key.pollEvents()) {
                    WatchEvent<Path> event = (WatchEvent<Path>) e;

                    Path ctx = event.context();

                    Path file = dir.resolve(ctx);

                    if (Files.exists(file) && Files.isRegularFile(file)){
                        System.out.printf("File '%s' changed. Content:%n", file);
                        List<String> lines = Files.readAllLines(file);
                        for (String line : lines) {
                            System.out.println(line);
                        }
                        System.out.printf("%n%n");
                    }
                }

                // it is required to reset the key in order to get another events
                key.reset();
            }

        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


}
