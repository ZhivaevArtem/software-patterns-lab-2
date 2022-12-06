package com.zhivaevartemsaveg.visual.context;

import com.zhivaevartemsaveg.geometry.IPoint;

import java.awt.*;

public class DrawSchemeBackground implements IDrawScheme {
    private final ICanvas canvas;

    public DrawSchemeBackground(ICanvas canvas) {
        this.canvas = canvas;
    }

    @Override
    public void drawFirstPoint(IPoint p) {

    }

    @Override
    public void drawSegment(IPoint a, IPoint b) {

    }

    @Override
    public void drawLastPoint(IPoint p) {

    }

    @Override
    public void fillCircle(IPoint p, double rad) {
        canvas.fillCircle(p, rad);
    }

    @Override
    public void setColor(Color c) {
        canvas.setColor(c);
    }

    @Override
    public void clear() {
        canvas.clear();
    }
}
