package com.sample.mall.common;

import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.Semaphore;

public class SemaphoreTest {

    private static final SimpleDateFormat sdf = new SimpleDateFormat("mm:ss");

    @Test
    public void testAcquireAndRelease() throws Exception {

        Semaphore semaphore = new Semaphore(4);

        semaphore.acquire(3);
        print(semaphore);

        new Thread(() -> {
            try {
                print(semaphore);
                semaphore.acquire(2);
                print(semaphore);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        Thread.sleep(5000);
        semaphore.release(1);

        Thread.sleep(1000);
        print(semaphore);
    }

    @Test
    public void testInterrupt() throws Exception {

        Semaphore semaphore = new Semaphore(4);

        semaphore.acquire(3);
        print(semaphore);

        Thread newThread = new Thread(() -> {
            try {
                print(semaphore);
                semaphore.acquire(2);
                print(semaphore);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        newThread.start();

        Thread.sleep(5000);
        newThread.interrupt();

        Thread.sleep(1000);
        print(semaphore);
    }

    public void print(Semaphore semaphore) {
        System.out.println(sdf.format(new Date()) + "\t" + Thread.currentThread().getName() + "\t" + semaphore.toString());
    }
}
