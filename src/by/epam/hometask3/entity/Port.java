package by.epam.hometask3.entity;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public class Port {
    private static final Port instance = new Port();

    private static Logger logger = LogManager.getLogger(Port.class);


    private Port() {
    }

    public static Port getInstance() {
        return instance;
    }
}
