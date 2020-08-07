package io;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Stream;

public class CopyFile {

    private static final int FILE_SIZE_THRESHOLD = 4096;
    private static final String[] TEXT_FILE_EXTENSIONS = {"txt", "csv"};

    public static void main(String[] args) {
        Path source = Paths.get(args[0]);
        Path destination = Paths.get(args[1]);
        if (!Files.exists(source) || !Files.exists(destination)) {
            System.err.println("Either source or destination path not valid");
            return;
        }
        if (!Files.isRegularFile(source)) {
            System.err.println("Source path must be regular file");
            return;
        }
        if (!Files.isDirectory(destination)) {
            System.err.println("Destination path must be directory");
            return;
        }
        try {
            copySourceToDestination(source, destination.resolve(source.getFileName()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void copySourceToDestination(Path source, Path destination) throws IOException {
        if (Files.size(source) == 0) {
            throw new IllegalArgumentException("File cannot be empty");
        }
        if (Stream.of(TEXT_FILE_EXTENSIONS)
                .anyMatch(ext -> source.toFile().getName().endsWith(ext))) {
            copyTextFileToDestination(source, destination);
        } else {
            copyBinaryFileToDestination(source, destination);
        }
    }

    private static void copyTextFileToDestination(Path source, Path destination) throws IOException {
        if (Files.size(source) > FILE_SIZE_THRESHOLD) {
            String inputLine;
            try (BufferedReader reader = Files.newBufferedReader(source);
                 BufferedWriter writer = Files.newBufferedWriter(destination)) {
                while ((inputLine = reader.readLine()) != null) {
                    writer.write(inputLine);
                }
            }
        } else {
            List<String> lines = Files.readAllLines(source);
            StringBuilder sb = new StringBuilder();
            lines.stream().forEach(line -> {
                sb.append(line);
                sb.append("\n");
            });
            Files.writeString(destination, sb.toString().trim());
        }
    }

    private static void copyBinaryFileToDestination(Path source, Path destination) throws IOException {
        if (Files.size(source) > FILE_SIZE_THRESHOLD) {
            int inputBytes;
            try (BufferedInputStream inputStream = new BufferedInputStream(Files.newInputStream(source));
                 BufferedOutputStream outputStream = new BufferedOutputStream(Files.newOutputStream(destination))) {
                while ((inputBytes = inputStream.read()) != -1) {
                    outputStream.write(inputBytes);
                }
            }
        } else {
            byte[] bytes = Files.readAllBytes(source);
            Files.write(destination, bytes);
        }
    }
}
