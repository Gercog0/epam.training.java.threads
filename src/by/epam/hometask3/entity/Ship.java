package by.epam.hometask3.entity;

import by.epam.hometask3.state.ShipState;
import by.epam.hometask3.state.impl.ArrivingStateImpl;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.util.concurrent.Callable;
import java.util.concurrent.atomic.AtomicInteger;

public class Ship implements Callable<Ship> {
    private String name;
    private AtomicInteger quantityContainers; // current capacity (current quantity of containers)
    private AtomicInteger capacityContainers; // max capacity of containers
    private Berth berth;
    private ShipState currentState;

    private static Logger logger = LogManager.getLogger(Ship.class);


    public Ship(String name, AtomicInteger quantityContainers, AtomicInteger capacityContainers) {
        this.name = name;
        this.quantityContainers = quantityContainers;
        this.capacityContainers = capacityContainers;
        this.currentState = new ArrivingStateImpl();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public AtomicInteger getQuantityContainers() {
        return quantityContainers;
    }

    public void setQuantityContainers(AtomicInteger quantityContainers) {
        this.quantityContainers = quantityContainers;
    }

    public AtomicInteger getCapacityContainers() {
        return capacityContainers;
    }

    public void setCapacityContainers(AtomicInteger capacityContainers) {
        this.capacityContainers = capacityContainers;
    }

    public Berth getBerth() {
        return berth;
    }

    public void setBerth(Berth berth) {
        this.berth = berth;
    }

    public ShipState getCurrentState() {
        return currentState;
    }

    public void setCurrentState(ShipState currentState) {
        this.currentState = currentState;
    }

    public boolean addContainer() {
        boolean isAdd = true;
        if (quantityContainers.get() < capacityContainers.get()) {
            quantityContainers.incrementAndGet();
            logger.debug("Container was successful added on " + this.getName());
        } else {
            isAdd = false;
            logger.debug("Container was not added. No free space on" + this.getName() + "ship");
        }
        return isAdd;
    }

    public boolean deleteContainer() {
        boolean isDelete = true;
        if (quantityContainers.get() > 0) {
            quantityContainers.decrementAndGet();
            logger.debug("Container was successful deleted on " + this.getName());
        } else {
            isDelete = false;
            logger.debug("Container was not deleted. No containers to delete" + this.getName() + "ship");

        }
        return isDelete;
    }

    @Override
    public Ship call() throws Exception {
        currentState.arriveAtBerth(this);
        currentState.unloadContainers(this);
        currentState.loadContainers(this);
        currentState.departFromBerth(this);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Ship ship = (Ship) o;

        if (name != null ? !name.equals(ship.name) : ship.name != null) {
            return false;
        }
        if (quantityContainers != null ? !quantityContainers.equals(ship.quantityContainers) : ship.quantityContainers != null) {
            return false;
        }
        if (capacityContainers != null ? !capacityContainers.equals(ship.capacityContainers) : ship.capacityContainers != null) {
            return false;
        }
        if (berth != null ? !berth.equals(ship.berth) : ship.berth != null) {
            return false;
        }
        return currentState != null ? currentState.equals(ship.currentState) : ship.currentState == null;
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (quantityContainers != null ? quantityContainers.hashCode() : 0);
        result = 31 * result + (capacityContainers != null ? capacityContainers.hashCode() : 0);
        result = 31 * result + (berth != null ? berth.hashCode() : 0);
        result = 31 * result + (currentState != null ? currentState.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Ship{");
        sb.append("name='").append(name).append('\'');
        sb.append(", quantityContainers=").append(quantityContainers.get());
        sb.append(", capacityContainers=").append(capacityContainers.get());
        sb.append(", berth=").append(berth);
        sb.append(", currentState=").append(currentState);
        sb.append('}');
        return sb.toString();
    }
}
