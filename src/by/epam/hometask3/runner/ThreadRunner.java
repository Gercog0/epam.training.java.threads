package by.epam.hometask3.runner;

import by.epam.hometask3.exception.ProjectException;
import by.epam.hometask3.reader.DataReader;

public class ThreadRunner {
    public static void main(String[] args) throws InterruptedException, ProjectException {
        System.out.println(new DataReader().readAll("resources/data.txt"));
    }
}

class ExceptThread extends Thread {
    @Override
    public void run() {
        if (true){
            throw new RuntimeException();
        }
        System.out.println("End of except thread");
    }
}
