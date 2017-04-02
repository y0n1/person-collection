package interfaces;


import main.PersonCollection;


public interface IPersonCollection<T extends IPerson> {

    void add(T person);

    IPerson remove();

    void subscribe(PersonCollection.EventsEnum eventName, ISubscriber subscriber);
}
