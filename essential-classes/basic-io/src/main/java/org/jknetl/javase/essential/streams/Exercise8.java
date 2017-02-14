package org.jknetl.javase.essential.streams;

import java.io.Console;

/**
 * @author jknetl
 */
public class Exercise8 {

    public static void main(String[] args) {
        Console console = System.console();

        if (console == null){
            System.out.println("Console is not supported on this system or program was launched in non-interactive environment. Exiting...");
            System.exit(1);
        }

        console.format("Enter password:");
        char[] password = console.readPassword();
//        System.out.println(password);

        console.format("Your password was securely stored.%n");
        console.format("You may enter arbitrary text now. Each line will then be written into output.%n");


        // cat utility
        String line;
        StringBuilder builder = new StringBuilder();
        while ((line = console.readLine()) != null){
            builder.append(line);
            builder.append(System.lineSeparator());
        }

        console.format(builder.toString());
    }

}
