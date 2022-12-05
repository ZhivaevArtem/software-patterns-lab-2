package com.zhivaevartemsaveg.visual.observer;

public interface ISubject<T extends IEvent> {
    void attach(IObserver<T> observer);
    void dettach(IObserver<T> observer);

    void notifyObservers(T event);
}
