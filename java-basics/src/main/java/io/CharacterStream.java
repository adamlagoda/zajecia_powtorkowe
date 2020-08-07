package io;

import java.io.*;
import java.nio.charset.StandardCharsets;

public class CharacterStream {
    private static final String INPUT_FILENAME = "src/main/resources/input.txt";
    private static final String OUTPUT_FILENAME = "src/main/resources/output.txt";

    public static void main(String[] args) {

    }

    public static void copySingleCharacters() {
        long startTime, elapsedTime;

        File fileIn = new File(INPUT_FILENAME);
        System.out.println("File size is " + fileIn.length() + " bytes");

        try (FileReader in = new FileReader(INPUT_FILENAME);
             FileWriter out = new FileWriter(OUTPUT_FILENAME)) {

            startTime = System.nanoTime();
            int charsRead;

            while ((charsRead = in.read()) != -1) {
                out.write(charsRead);
            }

            elapsedTime = System.nanoTime() - startTime;
            System.out.println("Elapsed Time is " + (elapsedTime / 1000000.0) + " msec");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public static void copyUsingBuffer() {
        long startTime, elapsedTime;

        File fileIn = new File(INPUT_FILENAME);
        System.out.println("File size is " + fileIn.length() + " bytes");

        try (FileReader in = new FileReader(INPUT_FILENAME);
             FileWriter out = new FileWriter(OUTPUT_FILENAME)) {

            startTime = System.nanoTime();
            char[] charBuf = new char[4096];
            int charsRead;
            while ((charsRead = in.read(charBuf)) != -1) {
                out.write(charBuf, 0, charsRead);
            }

            elapsedTime = System.nanoTime() - startTime;
            System.out.println("Elapsed Time is " + (elapsedTime / 1000000.0) + " msec");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public static void copyUsingBufferedStreams() {
        long startTime, elapsedTime;

        File fileIn = new File(INPUT_FILENAME);
        System.out.println("File size is " + fileIn.length() + " bytes");

        try (BufferedReader in = new BufferedReader(new FileReader(INPUT_FILENAME));
             BufferedWriter out = new BufferedWriter(new FileWriter(OUTPUT_FILENAME))) {

            startTime = System.nanoTime();
            int charsRead;
            while ((charsRead = in.read()) != -1) {
                out.write(charsRead);
            }

            elapsedTime = System.nanoTime() - startTime;
            System.out.println("Elapsed Time is " + (elapsedTime / 1000000.0) + " msec");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public static void copyWithEncoding() {
        long startTime, elapsedTime;

        File fileIn = new File(INPUT_FILENAME);
        System.out.println("File size is " + fileIn.length() + " bytes");

        try (InputStreamReader in = new InputStreamReader(new FileInputStream(INPUT_FILENAME), StandardCharsets.UTF_8);
             OutputStreamWriter out = new OutputStreamWriter(new FileOutputStream(OUTPUT_FILENAME), StandardCharsets.UTF_8)) {

            startTime = System.nanoTime();
            int charsRead;
            while ((charsRead = in.read()) != -1) {
                out.write(charsRead);
            }

            elapsedTime = System.nanoTime() - startTime;
            System.out.println("Elapsed Time is " + (elapsedTime / 1000000.0) + " msec");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
