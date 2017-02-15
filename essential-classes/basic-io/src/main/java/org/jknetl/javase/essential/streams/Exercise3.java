package org.jknetl.javase.essential.streams;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * @author jknetl
 */
public class Exercise3 {

    private static final String OUTPUT_FILE = "essential-classes/basic-io/src/main/resources/streams/data-stream-file.out";

    public static void main(String[] args) {

        // 1. write data into data stream
        try (DataOutputStream os = new DataOutputStream(
                new BufferedOutputStream(new FileOutputStream(OUTPUT_FILE)))
        ) {
            os.writeBoolean(true);
            os.writeByte(0);
            os.writeDouble(34.8);
            os.writeLong(3333333333333L);
            os.writeUTF("My message");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // 2. read data from the stream
        try (DataInputStream is = new DataInputStream(
                new BufferedInputStream(new FileInputStream(OUTPUT_FILE)))
        ) {
            System.out.println(is.readBoolean());
            System.out.println(is.readByte());
            System.out.println(is.readDouble());
            System.out.println(is.readLong());
            System.out.println(is.readUTF());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e1) {
            e1.printStackTrace();
        }

        System.out.println("");
        System.out.println("Reading different types than expected:");
        System.out.println("");

        //3. read in wrong order
        try (DataInputStream is = new DataInputStream(
                new BufferedInputStream(new FileInputStream(OUTPUT_FILE)))
        ) {
            // reading byte instead of boolean is OK, it is converted to 0 or 1. (the read method casts read character to byte explicitly)
            System.out.println(is.readByte());
            // reading boolean instead of byte is OK, value is converted to boolean by comparison to zero
            System.out.println(is.readBoolean());
            // reading an integer works, but reads wrong number of bytes, so it breaks reading other types. Try commenting next line out.
            // System.out.println(is.readInt());
            System.out.println(is.readDouble());
            System.out.println(is.readLong());
            // if we try to read UTF string as number, it will be successfully read as number
            System.out.println(is.readUTF());

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e1) {
            e1.printStackTrace();
        }

    }
}
