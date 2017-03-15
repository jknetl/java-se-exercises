package path;

import com.sun.xml.internal.bind.api.impl.NameConverter.Standard;

import java.io.IOException;
import java.nio.file.CopyOption;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Arrays;

/**
 * @author jknetl
 */
public class Exercise2 {

    public enum Mode {
        COPY, MOVE
    }

    public static void main(String[] args) throws IOException {
        if (args.length < 3) {
            printUsage();
            System.exit(1);
        }

        // parse mode
        Mode mode = null;
        if ("-c".equals(args[0])) {
            mode = Mode.COPY;
        } else if ("-m".equals(args[0])) {
            mode = Mode.MOVE;
        } else {
            printUsage();
            System.exit(1);
        }

        Path target = Paths.get(args[args.length - 1]);
        CopyOption[] copyOptions = new CopyOption[] {
                StandardCopyOption.COPY_ATTRIBUTES, StandardCopyOption.REPLACE_EXISTING };

        for (Path p : getInputFiles(args)){
            if (mode == Mode.COPY){
                Files.copy(p, target, copyOptions);
            } else {
                Files.move(p, target, copyOptions);
            }
        }
    }

    private static Path[] getInputFiles(String args[]){

        Path[] result = new Path[args.length - 2];
        for (int i = 0; i < args.length - 2; i++){
            result[i] = Paths.get(args[i + 1]);
        }

        return result;
    }

    private static void printUsage() {
        System.out.printf("You must provide at least 3 parameters.");
    }
}
