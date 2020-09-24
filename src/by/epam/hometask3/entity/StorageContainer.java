package by.epam.hometask3.entity;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class StorageContainer {
    private static final StorageContainer instance = new StorageContainer();
    private static final int MAX_CAPACITY = 10;
    private AtomicInteger currentCapacity;

    private static Logger logger = LogManager.getLogger(StorageContainer.class);


    private StorageContainer() {
        currentCapacity = new AtomicInteger();
    }

    public static StorageContainer getInstance() {
        return instance;
    }

    public AtomicInteger getCurrentCapacity() {
        return currentCapacity;
    }

    public void setCurrentCapacity(AtomicInteger currentCapacity) {
        this.currentCapacity = currentCapacity;
    }

    public AtomicInteger loadContainers(Ship ship) {
        AtomicInteger quantityLoaded = new AtomicInteger(0);

        while (currentCapacity.get() <= MAX_CAPACITY && ship.deleteContainer()) {
            currentCapacity.decrementAndGet();
            quantityLoaded.incrementAndGet();
            try {
                TimeUnit.MILLISECONDS.sleep(500);
            } catch (InterruptedException exp) {
                logger.debug(exp.getMessage());
            }
        }
        logger.debug("load: " + quantityLoaded.get() + ", to ship: " + ship);
        return quantityLoaded;
    }

    public AtomicInteger unloadContainers(Ship ship) {
        AtomicInteger quantityUnloaded = new AtomicInteger(0);
        while (currentCapacity.get() != MAX_CAPACITY && ship.addContainer()) {
            currentCapacity.incrementAndGet();
            quantityUnloaded.incrementAndGet();
            try {
                TimeUnit.MILLISECONDS.sleep(500);
            } catch (InterruptedException exp) {
                logger.debug(exp.getMessage());
            }
        }
        logger.debug("unload: " + quantityUnloaded.get() + ",from ship: " + ship);
        return quantityUnloaded;
    }
}
