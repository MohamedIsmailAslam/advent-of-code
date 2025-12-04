package com.github.mia.util;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class FileReader {

    public static List<String> readFile(int year, int day, boolean testrun) {
        try {
            String inputFileName = String.format("y%d/day%d/%s.txt",
                    year,
                    day,
                    testrun ? "test_input" : "input");
            Path path = Paths.get(FileReader.class
                    .getClassLoader()
                    .getResource(inputFileName).toURI());
            return Files.readAllLines(path);
        } catch (IOException | URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }

    public static void printBanner() {
        try {
            Path path = Paths.get(FileReader.class
                    .getClassLoader()
                    .getResource("banner.txt").toURI());
            Files.lines(path).forEach(System.out::println);
        } catch (IOException | URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }

}
