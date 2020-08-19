package com.company;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Semaphore;

public class Main {

    public static void main(String[] args) {
        Semaphore sem = new Semaphore(4, true);
        CountDownLatch latch = new CountDownLatch(100);
        for (int i = 1; i < 101; i++) {
            new PassengerThread(latch, sem, i).start();
        }

        try {
            latch.await();

            Thread.currentThread().sleep(1000);
            System.out.println("Автобус наполнился!");
            Thread.currentThread().sleep(1000);
            System.out.println("Автобус выехал!");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}



