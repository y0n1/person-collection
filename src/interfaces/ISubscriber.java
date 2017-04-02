package interfaces;

public interface ISubscriber {
    <T> void update(T... data);
}
