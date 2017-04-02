package main;

import interfaces.IPerson;
import interfaces.IPersonCollection;
import interfaces.ISubscriber;

import java.util.*;
import java.util.concurrent.PriorityBlockingQueue;


public class PersonCollection implements IPersonCollection<IPerson> {

    public enum EventsEnum {
        ADDED,
        REMOVED
    }

    private static final int INITIAL_CAPACITY = 16;
    private PriorityBlockingQueue<IPerson> _maxHeap;
    private final HashSet<Integer> removedPersons = new HashSet<>();
    private final HashMap<EventsEnum, Queue<ISubscriber>> _subscribers = new HashMap<>();

    PersonCollection(Comparator<IPerson> comparator) {
        _subscribers.put(EventsEnum.ADDED, new LinkedList<>());
        _subscribers.put(EventsEnum.REMOVED, new LinkedList<>());
        _maxHeap = new PriorityBlockingQueue<>(INITIAL_CAPACITY, comparator.reversed());
    }


    @Override
    public synchronized void add(IPerson person) {
        if (removedPersons.contains(_maxHeap.peek().getId())) {
            _maxHeap.remove();
        }

        _maxHeap.add(person);
        new Thread(() -> publish(EventsEnum.ADDED, person)).start();
    }

    @Override
    public synchronized IPerson remove() {
        IPerson removedPerson = _maxHeap.peek();
        removedPersons.add(removedPerson.getId());

        new Thread(() -> publish(EventsEnum.REMOVED, removedPerson)).start();
        return removedPerson;
    }

    @Override
    public synchronized void subscribe(EventsEnum eventName, ISubscriber subscriber) {
        _subscribers.get(eventName).add(subscriber);
    }

    private void publish(EventsEnum eventName, IPerson person) {
        _subscribers.get(eventName).forEach(subscriber -> subscriber.update(eventName, person));
    }
}
