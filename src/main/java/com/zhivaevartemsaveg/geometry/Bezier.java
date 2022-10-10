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
        double ax = this.a.getX();
        double ay = this.a.getY();
        double bx = this.b.getX();
        double by = this.b.getY();
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
