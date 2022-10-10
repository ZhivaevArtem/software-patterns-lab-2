package com.zhivaevartemsaveg.geometry;

import com.zhivaevartemsaveg.utils.Algebra;

public class Point implements IPoint {
    private double x, y;

    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public double getX() {
        return x;
    }

    @Override
    public void setX(double x) {
        this.x = x;
    }

    @Override
    public double getY() {
        return y;
    }

    @Override
    public void setY(double y) {
        this.y = y;
    }

    @Override
    public String toString() {
        return "(" + Algebra.round(x, 2) + "; " + Algebra.round(y, 2) + ")";
    }
}
