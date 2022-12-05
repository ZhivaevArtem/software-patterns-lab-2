package com.zhivaevartemsaveg.geometry;

public class MutablePoint implements IPoint {
    private double x = 0, y = 0;

    public MutablePoint() {
    }

    public MutablePoint(double x, double y) {
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

    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }
}
