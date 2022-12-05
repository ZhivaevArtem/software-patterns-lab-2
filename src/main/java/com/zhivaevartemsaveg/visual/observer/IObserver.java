package com.zhivaevartemsaveg.visual.observer;

public interface IObserver<T extends IEvent> {
    void update(T event);
}
