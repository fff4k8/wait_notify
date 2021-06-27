package com.company;


public class Data {


    // True if receiver should wait
    // False if sender should wait
    private boolean transfer = true;
    private String packet;


    public synchronized void send(String packet) {
        while (!transfer) {
            try {
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.out.println("Thread interrupted " + e.getMessage());
            }
        }
        transfer = false;
        this.packet = packet;
        notifyAll();
    }


    public synchronized String receive() {
        while (transfer) {
            try {
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.out.println("Thread interrupted " + e.getMessage());
            }
        }
        transfer = true;
        notifyAll();
        return packet;
    }


}
