package org.jknetl.javase.essential.streams;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;

/**
 * @author jknetl
 */
public class Exercise1 {

    static final String inputFile = "essential-classes/basic-io/src/main/resources/FileA.txt";
    static final String outputFile1 = "essential-classes/basic-io/src/main/resources/FileB.txt.tmp";
    static final String outputFile2 = "essential-classes/basic-io/src/main/resources/FileB.bytes.tmp";

    public static void main(String[] args) {

        /**
         * Read content from input file and store it to output file using line oriented buffered IO.
         */
        try (
                BufferedReader reader = new BufferedReader(new FileReader(inputFile));
                BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile1))
        ) {

            String line = null;
            while ((line = reader.readLine()) != null) {
                writer.write(line);
                writer.newLine();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        /**
         * Read content from input file and store it to output file using character/byte io.
         */

        try (
                BufferedReader reader = new BufferedReader(new FileReader(inputFile));
                BufferedOutputStream os = new BufferedOutputStream(new FileOutputStream(outputFile2))
        ) {
            int c;
            while ((c = reader.read()) != -1){
                os.write(c);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
