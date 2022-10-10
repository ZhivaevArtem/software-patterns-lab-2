package com.zhivaevartemsaveg.geometry;

import com.zhivaevartemsaveg.utils.Algebra;

public class Line extends ACurve {
    public Line(IPoint a, IPoint b) {
        super(a, b);
    }

    @Override
    public IPoint getPoint(double t) {
        double ax = this.a.getX();
        double ay = this.a.getY();
        double bx = this.b.getX();
        double by = this.b.getY();
        return new Point(
                Algebra.linear(ax, ay, t),
                Algebra.linear(bx, by, t)
        );
    }
}
