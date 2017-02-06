package org.jknetl.javase.essential.streams;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

/**
 * @author jknetl
 */
public class Exercise2 {

    private static final String ENCRYPTED_FILE = "essential-classes/basic-io/src/main/resources/encrypted";
    private static final int SHIFT = 7;
    private static final int ALPHABET_LENGTH = 'z' - 'a' + 1;
    private static final String OUTPUT_FILE = "essential-classes/basic-io/src/main/resources/encrypted.out";

    /**
     * Class which writes streams using simple caesar cipher. The cipher encrypts all alphabetic characters by shifting their code.
     */
    public static class Encoder extends OutputStreamWriter {

        int shift = 0;

        /**
         * Creates encoder.
         *
         * @param out   output stream used for storing data
         * @param shift shift of ceasar cipher.
         */
        public Encoder(OutputStream out, int shift) {
            super(out);
            this.shift = shift;
        }

        @Override
        public void write(int c) throws IOException {
            super.write(encodeCharacter(c));
        }


        @Override
        public void write(char[] cbuf) throws IOException {
            char[] encrypted = new char[cbuf.length];
            for (int i = 0; i < cbuf.length; i++) {
                encrypted[i] = (char) (encodeCharacter(cbuf[i]));
            }
            super.write(encrypted);
        }

        @Override
        public void write(String str) throws IOException {
            this.write(str.toCharArray());
        }

        private int encodeCharacter(int c) {
            if (Character.isAlphabetic(c)) {
                char firstLetter;

                if (Character.isLowerCase(c)) {
                    firstLetter = 'a';
                } else {
                    firstLetter = 'A';
                }

                c = (((c - firstLetter) + shift) % ALPHABET_LENGTH) + firstLetter;
            }

            return c;
        }

    }

    /**
     * Decoder is implementation of Reader which converts alphabet letters using Caesar cipher.
     */
    public static class Decoder extends InputStreamReader {
        private int shift;

        public Decoder(InputStream in, int shift) {
            super(in);
            this.shift = shift;
        }

        @Override
        public int read() throws IOException {
            return decodeCharacter(super.read());
        }

        /*
         * Implementation of method which reads multiple characters at once skipped since it won't be used in example.
         */

        private int decodeCharacter(int c) {
            if (Character.isAlphabetic(c)) {
                char firstLetter;

                if (Character.isLowerCase(c)) {
                    firstLetter = 'a';
                } else {
                    firstLetter = 'A';
                }

                c = ((c - firstLetter) - shift);
                //make result positive
                while (c < 0) {
                    c += ALPHABET_LENGTH;
                }
                c = (c % ALPHABET_LENGTH) + firstLetter;
            }

            return c;
        }

    }

    public static void main(String[] args) {

        // 1. read the encrypted file using decoder with correct key (shift 7)
        try (FileInputStream is = new FileInputStream(ENCRYPTED_FILE);
             Decoder decoder = new Decoder(is, SHIFT)
        ) {
            int c;
            while ((c = decoder.read()) != -1) {
                System.out.printf("%c", c);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // 2. writing my message using encoder

        try (FileOutputStream os = new FileOutputStream(OUTPUT_FILE);
             Encoder encoder = new Encoder(os, SHIFT)
        ) {
            encoder.write("My encoded message.");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.print(System.lineSeparator());

        //3. read encrypted text
        try (BufferedReader reader = new BufferedReader(new FileReader(OUTPUT_FILE))) {
            int c;
            while ((c = reader.read()) != -1) {
                System.out.format("%c",c);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
