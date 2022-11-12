package com.zhivaevartemsaveg.visual;

import com.zhivaevartemsaveg.geometry.IPoint;
import com.zhivaevartemsaveg.geometry.Line;
import com.zhivaevartemsaveg.visual.context.IDrawScheme;

public class VisualLine extends VisualCurve {
    public VisualLine(IPoint a, IPoint b) {
        this(new Line(a, b));
    }

    public VisualLine(Line line) {
        super(line);
    }

    @Override
    public void draw(IDrawScheme context) {
        context.drawFirstPoint(getPoint(0));
        context.drawSegment(getPoint(0), getPoint(1));
        context.drawLastPoint(getPoint(1));
    }
}
