package by.epam.hometask3.entity.state;

import by.epam.hometask3.entity.Ship;

public interface ShipState {
    void arriveAtBerth(Ship ship) throws InterruptedException;

    void departFromBerth(Ship ship);

    void loadContainers(Ship ship);

    void unloadContainers(Ship ship);
}
