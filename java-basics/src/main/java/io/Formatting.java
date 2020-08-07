package io;

import java.io.*;
import java.util.Formatter;
import java.util.Locale;
import java.util.Scanner;

public class Formatting {

    private static final String INPUT_FILENAME = "src/main/resources/formatted.txt";
    private static final String OUTPUT_FILENAME = "src/main/resources/formatted-output.txt";

    public static void main(String[] args) {
        writeWithPrintWriter();
    }

    public static void readFormattedFile() {
        try (Scanner sc = new Scanner(new BufferedReader(new FileReader(INPUT_FILENAME)))) {
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
        try (Scanner sc = new Scanner(new BufferedReader(new FileReader(INPUT_FILENAME)));
             Formatter formatter = new Formatter(new BufferedWriter(new FileWriter(OUTPUT_FILENAME)))) {
            sc.useLocale(Locale.US);
            sc.useDelimiter("[\\t\\n\\r]+");
            double firstLine = sc.nextDouble();
            formatter.format("%.2f\n", firstLine);
            int secondLine = sc.nextInt();
            formatter.format("%d\n", secondLine);
            long thirdLine = sc.nextLong();
            formatter.format("%d\n", thirdLine);
            String fourthLine = sc.next();
            formatter.format("%s\n", fourthLine);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void writeWithPrintWriter() {
        try (Scanner sc = new Scanner(new BufferedReader(new FileReader(INPUT_FILENAME)));
             PrintWriter printWriter = new PrintWriter(new FileWriter(OUTPUT_FILENAME))) {
            sc.useLocale(Locale.US);
            sc.useDelimiter("[\\t\\n\\r]+");
            double firstLine = sc.nextDouble();
            printWriter.printf("%.2f\n", firstLine);
            int secondLine = sc.nextInt();
            printWriter.printf("%d\n", secondLine);
            long thirdLine = sc.nextLong();
            printWriter.printf("%d\n", thirdLine);
            String fourthLine = sc.next();
            printWriter.printf("%s\n", fourthLine);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
