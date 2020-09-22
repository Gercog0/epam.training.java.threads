package by.epam.hometask3.state;

import by.epam.hometask3.entity.Ship;

public interface ShipState {
    void arriveAtBerth(Ship ship);

    void departFromBerth(Ship ship);

    void loadContainers(Ship ship);

    void unloadContainers(Ship ship);
}
