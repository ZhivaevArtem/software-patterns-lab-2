package com.zhivaevartemsaveg.visual.context;

import com.zhivaevartemsaveg.geometry.IPoint;

import java.awt.*;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class CanvasComposer implements ICanvas {
    private final List<ICanvas> canvases = new LinkedList<>();

    public CanvasComposer(ICanvas ...canvases) {
        this.canvases.addAll(Arrays.asList(canvases));
    }

    @Override
    public void setColor(Color c) {
        canvases.forEach(canvas -> canvas.setColor(c));
    }

    @Override
    public void clear() {
        canvases.forEach(ICanvas::clear);
    }

    @Override
    public void drawLine(IPoint a, IPoint b) {
        canvases.forEach(c -> c.drawLine(a, b));
    }

    @Override
    public void drawCircle(IPoint center, double rad) {
        canvases.forEach(c -> c.drawCircle(center, rad));
    }

    @Override
    public void drawSquare(IPoint center, double size) {
        canvases.forEach(c -> c.drawSquare(center, size));
    }

    @Override
    public void drawArrow(IPoint p, double angle, double length) {
        canvases.forEach(c -> c.drawArrow(p, angle, length));
    }

    @Override
    public void fillCircle(IPoint p, double rad) {
        canvases.forEach(c -> c.fillCircle(p, rad));
    }

    @Override
    public void setSize(int w, int h) {
        canvases.forEach(c -> c.setSize(w, h));
    }
}
