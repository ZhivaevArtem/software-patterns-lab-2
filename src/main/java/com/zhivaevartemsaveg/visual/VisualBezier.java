package com.zhivaevartemsaveg.visual;

import com.zhivaevartemsaveg.geometry.Bezier;
import com.zhivaevartemsaveg.geometry.IPoint;
import com.zhivaevartemsaveg.visual.context.IDrawScheme;

public class VisualBezier extends VisualCurve {
    public VisualBezier(IPoint a, IPoint b, IPoint c, IPoint d) {
        super(new Bezier(a, b, c, d));
    }

    @Override
    public void draw(IDrawScheme context) {
        context.drawFirstPoint(getPoint(0));
        final int n = 20;
        for (int i = 0; i < 20; i++) {
            context.drawSegment(getPoint((1.0 * i) / n), getPoint(1.0 * (i+1) / n));
        }
        context.drawLastPoint(getPoint(1));
    }
}
