package com.zhivaevartemsaveg.geometry;

import com.zhivaevartemsaveg.utils.Algebra;

public class Point implements IPoint {
    private double x, y;

    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public Point(java.awt.Point point) {
        this.x = point.x;
        this.y = point.y;
    }

    @Override
    public double getX() {
        return x;
    }

    @Override
    public double getY() {
        return y;
    }

    @Override
    public String toString() {
        return "(" + Algebra.round(x, 2) + "; " + Algebra.round(y, 2) + ")";
    }
}
