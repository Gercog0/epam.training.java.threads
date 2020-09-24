package by.epam.hometask3.creator;

import by.epam.hometask3.entity.Ship;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class ShipCreator {
    public List<Ship> createShipsFromFile(List<String[]> data) {
        List<Ship> ships = new ArrayList<>();
        for (int i = 0; i < data.size(); i++) {
            String[] currentDataShip = data.get(i);
            String shipName = currentDataShip[0];
            AtomicInteger currentCapacity = new AtomicInteger(Integer.parseInt(currentDataShip[1]));
            AtomicInteger maxCapacity = new AtomicInteger(Integer.parseInt(currentDataShip[2]));
            Ship ship = new Ship(shipName, currentCapacity, maxCapacity);
            ships.add(ship);
        }
        return ships;
    }
}