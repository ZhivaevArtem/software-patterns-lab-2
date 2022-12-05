package com.zhivaevartemsaveg.geometry;

import com.zhivaevartemsaveg.visual.observer.IObserver;
import com.zhivaevartemsaveg.visual.observer.ISubject;
import com.zhivaevartemsaveg.visual.observer.MouseHoverEvent;

import java.util.ArrayList;
import java.util.List;

public class MutableVector implements IPoint {
    private double x = 0, y = 0;

    public IPoint fromPoint(IPoint p) {
        return new Point(p.getX() + x, p.getY() + y);
    }

    public IPoint toPoint(IPoint p) {
        return new Point(p.getX() - x, p.getY() - y);
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
        return x;
    }

    @Override
    public double getY() {
        return y;
    }

    public void add(IPoint movement) {
        x += movement.getX();
        y += movement.getY();
    }
}
