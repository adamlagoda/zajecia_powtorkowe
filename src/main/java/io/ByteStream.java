package io;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class ByteStream {

    private static final String INPUT_FILENAME = "input.txt";
    private static final String OUTPUT_FILENAME = "output.txt";

    public static void main(String[] args) {

    }

    public static void copySingleBytes() {
        long startTime, elapsedTime;

        File fileIn = new File(INPUT_FILENAME);
        System.out.println("File size is " + fileIn.length() + " bytes");

        try (FileInputStream in = new FileInputStream(INPUT_FILENAME);
             FileOutputStream out = new FileOutputStream(OUTPUT_FILENAME)) {

            startTime = System.nanoTime();
            int byteRead;

// przepisywanie bajtu po bajcie z inputStream do outputStream

            elapsedTime = System.nanoTime() - startTime;
            System.out.println("Elapsed Time is " + (elapsedTime / 1000000.0) + " msec");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
