package com.zhivaevartemsaveg.geometry;

import com.zhivaevartemsaveg.utils.Algebra;

public class Line extends ACurve {
    public Line(IPoint a, IPoint b) {
        super(a, b);
    }

    public double length() {
        return Math.sqrt(
                Math.pow(getA().getX() - getB().getX(), 2)
                + Math.pow(getA().getY() - getB().getY(), 2)
        );
    }

    @Override
    public IPoint getPoint(double t) {
        double ax = this.getA().getX();
        double ay = this.getA().getY();
        double bx = this.getB().getX();
        double by = this.getB().getY();
        return new Point(
                Algebra.linear(ax, bx, t),
                Algebra.linear(ay, by, t)
        );
    }
}
