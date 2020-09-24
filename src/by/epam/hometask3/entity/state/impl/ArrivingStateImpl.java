package by.epam.hometask3.entity.state.impl;

import by.epam.hometask3.entity.Berth;
import by.epam.hometask3.entity.Port;
import by.epam.hometask3.entity.Ship;
import by.epam.hometask3.entity.state.ShipState;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public class ArrivingStateImpl implements ShipState {
    private static final Logger Logger = LogManager.getLogger(ArrivingStateImpl.class);

    @Override
    public void arriveAtBerth(Ship ship) throws InterruptedException {
        Port port = Port.getInstance();
        Berth berth = port.arriveToPier();
        ship.setBerth(berth);
        ship.setCurrentState(new UnloadingStateImpl());
    }

    @Override
    public void departFromBerth(Ship ship) {
        Logger.warn("Can`t use this method.");
    }

    @Override
    public void loadContainers(Ship ship) {
        Logger.warn("Can`t use this method.");
    }

    @Override
    public void unloadContainers(Ship ship) {
        Logger.warn("Can`t use this method.");
    }
}
