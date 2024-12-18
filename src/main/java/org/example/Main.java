package org.example;

import java.util.concurrent.Semaphore;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        // Semaphores to control thread execution
        Semaphore sem1 = new Semaphore(1); // Semaphore for Thread-1 (initially available)
        Semaphore sem2 = new Semaphore(0); // Semaphore for Thread-2
        Semaphore sem3 = new Semaphore(0); // Semaphore for Thread-3

        // Create and start threads
        SequencePrinter thread1 = new SequencePrinter("THREAD-1 : ", sem1, sem2);
        SequencePrinter thread2 = new SequencePrinter("THREAD-2 : ", sem2, sem3);
        SequencePrinter thread3 = new SequencePrinter("THREAD-3 : ", sem3, sem1);
        thread1.start();
        thread2.start();
        thread3.start();
    }
}