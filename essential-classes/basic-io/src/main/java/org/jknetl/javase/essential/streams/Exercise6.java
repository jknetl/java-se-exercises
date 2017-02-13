package org.jknetl.javase.essential.streams;

import java.io.PrintStream;
import java.util.Calendar;
import java.util.Locale;

/**
 * @author jknetl
 */
public class Exercise6 {

    public static void main(String[] args) {
        System.out.println("===================== With US locale =============================");
        printValues(System.out, Locale.US);
        System.out.println("===================== With German locale =============================");
        printValues(System.out, Locale.GERMAN);
    }
    private static void printValues(PrintStream ps, Locale l) {
        // PrintStream  format specifiers:  %[argument_index$][flags][width][.precision]conversion
        ps.format(l, "%%n stands for newline %n");
        ps.format(l, "You can print decimal number using %%d pattern: '%d'%n", 5);
        ps.format(l, "You may also specify width using %%5d: '%5d'%n", 51);
        ps.format(l, "You may use flags (e.g. to align to the left) width using %%-5d: '%-5d'%n", 51);
        ps.format(l, "You may use flags (e.g. display sign for numbers) using %%+5d: '%+5d'%n", 51);

        ps.format(l, "%n");

        ps.format(l, "you may specify precision for floating point using %%6.3f: '%6.3f%n'",  Math.PI);
        ps.format(l, "you may use multiple format specifiers for an arguments with %%1$6.3f and %%1$10.6f: '%1$6.3f', '%1$10.6f'",  Math.PI);

        ps.format(l, "%n");

        Calendar c = Calendar.getInstance();
        ps.format(l, " You may print time using multiple specifiers such as %%1$tH, %%1$tM, %%1$tS: '%1$tH':'%1$tM':'%1$tS'%n", c);

    }
}
