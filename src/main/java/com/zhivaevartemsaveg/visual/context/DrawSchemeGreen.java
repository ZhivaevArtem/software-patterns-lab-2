package com.zhivaevartemsaveg.visual.context;

import com.zhivaevartemsaveg.geometry.IPoint;
import com.zhivaevartemsaveg.utils.Algebra;

import java.awt.*;

public class DrawSchemeGreen implements IDrawScheme {
    private final ICanvas canvas;
    private final Color color = Color.GREEN;
    private final double circleRadius = 5;
    private final double arrowSize = 15;

    private double angle = 0;

    public DrawSchemeGreen(ICanvas canvas) {
        this.canvas = canvas;
    }

    @Override
    public void drawFirstPoint(IPoint p) {
        canvas.setColor(color);
        canvas.drawCircle(p, circleRadius);
    }

    @Override
    public void drawSegment(IPoint a, IPoint b) {
        canvas.setColor(color);
        canvas.drawLine(a, b);
        angle = Algebra.angle(a, b);
    }

    @Override
    public void drawLastPoint(IPoint p) {
        canvas.setColor(color);
        canvas.drawArrow(p, angle, arrowSize);
    }

    @Override
    public void setColor(Color c) {}

    @Override
    public void clear() {
        canvas.clear();
    }
}
