package com.zhivaevartemsaveg.geometry;

import com.zhivaevartemsaveg.utils.Algebra;

public class Bezier extends ACurve {
    private IPoint c, d;

    public Bezier(IPoint a, IPoint b, IPoint c, IPoint d) {
        super(a, b);
        this.c = c;
        this.d = d;
    }

    @Override
    public IPoint getPoint(double t) {
        double ax = this.getA().getX();
        double ay = this.getA().getY();
        double bx = this.getB().getX();
        double by = this.getB().getY();
        double cx = this.c.getX();
        double cy = this.c.getY();
        double dx = this.d.getX();
        double dy = this.d.getY();
        return new Point(
                Algebra.bezier(ax, bx, cx, dx, t),
                Algebra.bezier(ay, by, cy, dy, t)
        );
    }
}
