package by.epam.hometask3.reader;

import by.epam.hometask3.exception.ProjectException;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

public class DataReader {
    private static final String DEFAULT_PATH = "resources/data.txt";

    public List<String> readAll(String filePath) throws ProjectException {
        if (filePath == null || !Files.exists(Paths.get(filePath))) {
            filePath = DEFAULT_PATH;
        }

        List<String> lines;
        try (FileReader reader = new FileReader(filePath);
             BufferedReader bufferedReader = new BufferedReader(reader)) {
            lines = bufferedReader.lines().
                    collect(Collectors.toList());
        } catch (IOException exp) {
            throw new ProjectException("Error while opening file: " + filePath, exp);
        }
        return lines;
    }
}
