package by.epam.hometask3.runner;

import by.epam.hometask3.creator.ShipCreator;
import by.epam.hometask3.entity.Ship;
import by.epam.hometask3.exception.ProjectException;
import by.epam.hometask3.parser.DataParser;
import by.epam.hometask3.reader.DataReader;

import java.util.List;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

public class ThreadRunner {
    public static void main(String[] args) throws InterruptedException, ProjectException {
        List<String> dataFile = new DataReader().readAll("resources/data.txt");
        List<String[]> parsedDataFromFile = new DataParser().parseShipDataFile(dataFile);
        List<Ship> createdShips = new ShipCreator().createShipsFromFile(parsedDataFromFile);

        try {
            for (Ship ship : createdShips) {
                FutureTask<Ship> future = new FutureTask<>(ship);
                Thread thread = new Thread(future);
                thread.start();
                TimeUnit.MILLISECONDS.sleep(2000);
            }
        } catch (Exception exp) {
            exp.printStackTrace();
        }
    }
}

