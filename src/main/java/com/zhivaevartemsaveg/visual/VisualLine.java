package com.zhivaevartemsaveg.visual;

import com.zhivaevartemsaveg.geometry.IPoint;
import com.zhivaevartemsaveg.geometry.Line;
import com.zhivaevartemsaveg.visual.context.IDrawContext;

public class VisualLine extends VisualCurve {
    public VisualLine(IPoint a, IPoint b) {
        super(new Line(a, b));
    }

    @Override
    public void draw(IDrawContext context) {
        context.drawLine(curve.getPoint(0.0), curve.getPoint(1.0));
    }
}
