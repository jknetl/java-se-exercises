package org.jknetl.javase.essential.streams;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Locale;
import java.util.Scanner;

/**
 * @author jknetl
 */
public class Exercise5 {

    public static String INPUT_FILE = "essential-classes/basic-io/src/main/resources/streams/tokens.txt";

    public static void main(String[] args) {

        try (BufferedInputStream is = new BufferedInputStream(new FileInputStream(INPUT_FILE));
             Scanner scanner = new Scanner(is)) {

            //tokenizing
            while (scanner.hasNext()) {
                //return string token
                String token = scanner.next();
                System.out.println(token);


            }

            /* you may also scan for specific type (e.g. scanner.nextInt()) however, if the token does not
             * contain correct type, then you'll get InputMismatchException. Therfore, you should check using
             * a scanner.hasNextInt() method
             */

            // read only ints
            while (scanner.hasNext()){
                if (scanner.hasNextInt()){
                    System.out.println(scanner.nextInt());
                } else {
                    // skip token, which is not number
                    scanner.next();
                }
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("==== Integers only ====");
        /* you may also scan for specific type (e.g. scanner.nextInt()) however, if the token does not
         * contain correct type, then you'll get InputMismatchException. Therfore, you should check using
         * a scanner.hasNextInt() method
         */
        try (BufferedInputStream is = new BufferedInputStream(new FileInputStream(INPUT_FILE));
             Scanner scanner = new Scanner(is)) {

            // read only ints
            while (scanner.hasNext()){
                if (scanner.hasNextInt()){
                    System.out.println(scanner.nextInt());
                } else {
                    // skip token, which is not number
                    scanner.next();
                }
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


        //Read double with different locales
        System.out.println("==== Doubles with US locale ====");
        readDoubles(Locale.US);
        System.out.println("==== Doubles with czech locale (notice the second number is different) ====");
        readDoubles(new Locale("cs"));
    }

    private static void readDoubles(Locale locale) {

        try (BufferedInputStream is = new BufferedInputStream(new FileInputStream(INPUT_FILE));
             Scanner scanner = new Scanner(is)) {
            scanner.useLocale(locale);

            // read only ints
            while (scanner.hasNext()){
                if (scanner.hasNextDouble()){
                    System.out.println(scanner.nextDouble());
                } else {
                    // skip token, which is not number
                    scanner.next();
                }
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
