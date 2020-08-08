package io;

import java.io.*;

public class ByteStream {

    private static final String INPUT_FILENAME = "java-basics/src/main/resources/cat.jpg";
    private static final String OUTPUT_FILENAME = "java-basics/src/main/resources/copied-cat.jpg";

    public static void main(String[] args) {
        copySingleBytes();
        copyUsingBufferedStreams();
    }

    public static void copySingleBytes() {
        long startTime, elapsedTime;

        File fileIn = new File(INPUT_FILENAME);
        System.out.println("File size is " + fileIn.length() + " bytes");

        try (FileInputStream in = new FileInputStream(INPUT_FILENAME);
             FileOutputStream out = new FileOutputStream(OUTPUT_FILENAME)) {

            startTime = System.nanoTime();
            int byteRead;

            while ((byteRead = in.read()) != -1) {
                out.write(byteRead);
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

        try (FileInputStream in = new FileInputStream(INPUT_FILENAME);
             FileOutputStream out = new FileOutputStream(OUTPUT_FILENAME)) {

            startTime = System.nanoTime();
            byte[] byteBuf = new byte[4096];
            int numBytesRead;
            while ((numBytesRead = in.read(byteBuf)) != -1) {
                out.write(byteBuf, 0, numBytesRead);
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

        try (BufferedInputStream in = new BufferedInputStream(new FileInputStream(INPUT_FILENAME));
             BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(OUTPUT_FILENAME))) {

            startTime = System.nanoTime();
            int numBytesRead;
            while ((numBytesRead = in.read()) != -1) {
                out.write(numBytesRead);
            }

            elapsedTime = System.nanoTime() - startTime;
            System.out.println("Elapsed Time is " + (elapsedTime / 1000000.0) + " msec");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
