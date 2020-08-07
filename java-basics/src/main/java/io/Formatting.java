package io;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Locale;
import java.util.Scanner;

public class Formatting {

    private static final String FILENAME = "src/main/resources/formatted.txt";

    public static void main(String[] args) {
        readFormattedFile();
    }

    public static void readFormattedFile() {
        try (Scanner sc = new Scanner(new BufferedReader(new FileReader(FILENAME)))) {
            sc.useLocale(Locale.US);
            sc.useDelimiter("[\\t\\n\\r]+");
            double firstLine = sc.nextDouble();
            System.out.println("Double value: " + firstLine);
            int secondLine = sc.nextInt();
            System.out.println("Int value: " + secondLine);
            long thirdLine = sc.nextLong();
            System.out.println("Long value: " + thirdLine);
            String fourthLine = sc.next();
            System.out.println("String value: " + fourthLine);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void writeWithFormatter() {

    }

    public static void writeWithPrintWriter() {

    }
}
