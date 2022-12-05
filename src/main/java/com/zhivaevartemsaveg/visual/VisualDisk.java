package com.zhivaevartemsaveg.visual;

import com.zhivaevartemsaveg.geometry.IPoint;
import com.zhivaevartemsaveg.visual.context.IDrawScheme;

public class VisualDisk implements IDrawable {
    private final IPoint center;
    private final double radius;

    public VisualDisk(IPoint center, double radius) {
        this.center = center;
        this.radius = radius;
    }

    @Override
    public void draw(IDrawScheme context) {
        context.fillCircle(center, radius);
    }
}
