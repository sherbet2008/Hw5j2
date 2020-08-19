package com.company;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Semaphore;

public class PassengerThread extends Thread {

    private CountDownLatch cdl;
    private Semaphore semaphore;
    private int id;

    public PassengerThread(CountDownLatch cdl, Semaphore sem, int id) {
        this.cdl = cdl;
        this.semaphore = sem;
        this.id = id;
    }

    public synchronized void run() {
        try {
            semaphore.acquire();
            System.out.println("Пассажир "+id+" покупает билет");
            sleep(1000);
            System.out.println("Пассажир "+id+" купил билет");
            semaphore.release();
            sleep(1000);
            System.out.println("Пассажир "+id+" сел в автобус");
            cdl.countDown();
            cdl.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
