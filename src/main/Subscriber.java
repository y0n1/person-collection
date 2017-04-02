package main;


import interfaces.ISubscriber;

public class Subscriber implements ISubscriber {

    @Override
    public <T> void update(T[] data) {
        System.out.printf("%s %s: %s%n", data);
    }
}
