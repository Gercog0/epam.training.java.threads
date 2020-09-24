package by.epam.hometask3.parser;

import java.util.ArrayList;
import java.util.List;

public class DataParser {
    public List<String[]> parseShipDataFile(List<String> dataFromFile) {
        List<String[]> parsedData = new ArrayList<>();

        for (int i = 0; i < dataFromFile.size(); i++) {
            parsedData.add(dataFromFile.get(i).split(" "));
        }
        return parsedData;
    }
}
