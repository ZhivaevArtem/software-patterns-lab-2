package com.zhivaevartemsaveg.geometry;

import com.zhivaevartemsaveg.visual.observer.IObserver;
import com.zhivaevartemsaveg.visual.observer.ISubject;
import com.zhivaevartemsaveg.visual.observer.MouseHoverEvent;

import java.util.ArrayList;
import java.util.List;

public class MutableVector implements IPoint, ISubject<MouseHoverEvent> {
    private double x = 0, y = 0;

    public IPoint fromPoint(IPoint p) {
        return new Point(p.getX() + x, p.getY() + y);
    }

    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }

    public MutableVector() {
    }

    public MutableVector(double x, double y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public double getX() {
        return 0;
    }

    @Override
    public double getY() {
        return 0;
    }

    private final List<IObserver<MouseHoverEvent>> observers = new ArrayList<>();

    @Override
    public void attach(IObserver<MouseHoverEvent> observer) {
        observers.add(observer);
    }

    @Override
    public void dettach(IObserver<MouseHoverEvent> observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers(MouseHoverEvent event) {
        for (IObserver<MouseHoverEvent> observer : observers) {
            observer.update(event);
        }
    }
}
