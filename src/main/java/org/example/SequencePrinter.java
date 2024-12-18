package org.example;

import java.util.concurrent.Semaphore;

public class SequencePrinter extends Thread {
    private static final int MAX = 10; // Maximum number to print
    private static int current = 1; // Shared counter

    private final Semaphore mySemaphore; // Semaphore for the current thread
    private final Semaphore nextSemaphore; // Semaphore for the next thread
    private final String threadName; // Name of the thread

    public SequencePrinter(String threadName, Semaphore mySemaphore, Semaphore nextSemaphore) {
        this.threadName = threadName;
        this.mySemaphore = mySemaphore;
        this.nextSemaphore = nextSemaphore;
    }

    @Override
    public void run() {
        while (true) {
            try {
                mySemaphore.acquire(); // Acquire this thread's semaphore
                if (current > MAX) {
                    nextSemaphore.release(); // Ensure the next thread isn't stuck
                    break; // Exit when all numbers are printed
                }
                System.out.println(threadName + current);
                current++;
                nextSemaphore.release(); // Release the next thread's semaphore
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}
