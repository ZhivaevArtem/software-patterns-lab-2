package com.zhivaevartemsaveg.visual.context;

import com.zhivaevartemsaveg.geometry.IPoint;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DrawSchemeComposer implements IDrawScheme {
    private final List<IDrawScheme> canvases = new ArrayList<>();

    public DrawSchemeComposer(List<IDrawScheme> canvases) {
        this.canvases.addAll(canvases);
    }

    public DrawSchemeComposer(IDrawScheme ...canvases) {
        this(Arrays.asList(canvases));
    }

    public void clearCanvases() {
        canvases.clear();
    }

    public void addCanvas(IDrawScheme canvas) {
        canvases.add(canvas);
    }

    @Override
    public void drawFirstPoint(IPoint p) {
        canvases.forEach((canvas) -> {
            canvas.drawFirstPoint(p);
        });
    }

    @Override
    public void drawSegment(IPoint a, IPoint b) {
        canvases.forEach((canvas) -> {
            canvas.drawSegment(a, b);
        });
    }

    @Override
    public void drawLastPoint(IPoint p) {
        canvases.forEach((canvas) -> {
            canvas.drawLastPoint(p);
        });
    }

    @Override
    public void setColor(Color c) {
        canvases.forEach((canvas) -> {
            canvas.setColor(c);
        });
    }

    @Override
    public void clear() {
        canvases.forEach((canvas) -> {
            canvas.clear();
        });
    }
}
