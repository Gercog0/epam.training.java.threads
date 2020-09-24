package by.epam.hometask3.entity.state.impl;

import by.epam.hometask3.entity.Berth;
import by.epam.hometask3.entity.Port;
import by.epam.hometask3.entity.Ship;
import by.epam.hometask3.entity.state.ShipState;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public class LoadingStateImpl implements ShipState {
    private static final Logger Logger = LogManager.getLogger(LoadingStateImpl.class);


    @Override
    public void arriveAtBerth(Ship ship) {
        Logger.warn("Can`t use this method.");
    }

    @Override
    public void departFromBerth(Ship ship) {
        Berth berth = ship.getBerth();
        if (berth != null) {
            Port port = Port.getInstance();
            port.departFromBerth(berth);
            ship.setBerth(null);
        }

        ship.setCurrentState(new ArrivingStateImpl());
    }

    @Override
    public void loadContainers(Ship ship) {
        Berth berth = ship.getBerth();
        if (berth != null) {
            berth.loadFromStorageContainer(ship);
        }

        ship.setCurrentState(new DepartingStateImpl());
    }

    @Override
    public void unloadContainers(Ship ship) {
        Logger.warn("Can`t use this method.");
    }
}
