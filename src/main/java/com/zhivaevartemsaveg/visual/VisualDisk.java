package com.zhivaevartemsaveg.visual;

import com.zhivaevartemsaveg.geometry.IPoint;
import com.zhivaevartemsaveg.visual.context.IDrawScheme;

public class VisualDisk implements IDrawableArea {
    private final IPoint center;
    private final double radius;

    public VisualDisk(IPoint center, double radius) {
        this.center = center;
        this.radius = radius;
        this.r2 = radius * radius;
    }

    @Override
    public void draw(IDrawScheme context) {
        context.fillCircle(center, radius);
    }

    private final double r2;
    @Override
    public boolean contains(IPoint p) {
        double a = p.getX() - center.getX();
        double b = p.getY() - center.getY();

        return a*a + b*b < r2;
    }
}
