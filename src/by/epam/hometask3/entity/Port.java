package by.epam.hometask3.entity;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.util.*;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Port {
    private static final Port instance = new Port();
    private static final int BERTH_CAPACITY = 4;
    private Deque<Berth> freeBerth;
    private Queue<Berth> busyBerth;
    private Lock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();
    private static Logger logger = LogManager.getLogger(Port.class);


    private Port() {
        this.freeBerth = new ArrayDeque<>();
        this.busyBerth = new LinkedList<>();
        fillPort();
    }

    public static Port getInstance() {
        return instance;
    }

    public Berth arriveToPier() {
        try {
            lock.lock();
            while (freeBerth.isEmpty()) {
                condition.await();
            }
            Berth berth = freeBerth.poll();
            busyBerth.offer(berth);
            return berth;
        } catch (InterruptedException exp) {
            return null;
        } finally {
            lock.unlock();
        }
    }

    public void departFromBerth(Berth berth) {
        try {
            lock.lock();
            freeBerth.offer(berth);
            busyBerth.remove();
        } finally {
            lock.unlock();
        }
    }

    private void fillPort() {
        for (int i = 0; i < BERTH_CAPACITY; i++) {
            freeBerth.add(new Berth(i + 1));
        }
    }
}
