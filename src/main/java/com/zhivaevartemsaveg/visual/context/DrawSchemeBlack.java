package com.zhivaevartemsaveg.visual.context;

import com.zhivaevartemsaveg.geometry.IPoint;
import com.zhivaevartemsaveg.geometry.Line;

import java.awt.*;
import java.util.LinkedList;
import java.util.List;

public class DrawSchemeBlack implements IDrawScheme {
    private final ICanvas canvas;
    private final Color color = Color.BLACK;
    private final double squareSize = 10;
    private final double dashSize = 20;

    private double dashRest = 0;
    private boolean filledDash = true;

    public DrawSchemeBlack(ICanvas canvas) {
        this.canvas = canvas;
    }

    @Override
    public void drawFirstPoint(IPoint p) {
        canvas.setColor(color);
        canvas.drawSquare(p, squareSize);
    }

    @Override
    public void drawSegment(IPoint a, IPoint b) {
        canvas.setColor(color);
        Line seg = new Line(a, b);
        double segLen = seg.length();
        double dRes = (dashSize - dashRest) % dashSize;
        int dashCount = (int) Math.ceil((segLen + dRes) / dashSize);
        double tSize = 1.0 / dashCount;
        double tStart = -dRes / tSize,
                tEnd = tStart + tSize * dashCount;

        List<IPoint> points = new LinkedList<>();
        List<Line> dashes = new LinkedList<>();

        points.add(seg.getPoint(0));
        for (int i = 1; i < dashCount; i++) {
            points.add(seg.getPoint(tStart + tSize * i));
        }
        points.add(seg.getPoint(1));
        for (int i = 0; i < dashCount; i++) {
            dashes.add(new Line(points.get(i), points.get(i + 1)));
        }
        dashRest = new Line(points.get(points.size() - 2), seg.getPoint(tEnd)).length() - dashes.get(dashes.size() - 1).length();

        for (Line dash : dashes) {
            if (filledDash) canvas.drawLine(dash.getPoint(0), dash.getPoint(1));
            filledDash = !filledDash;
        }
    }

    @Override
    public void drawLastPoint(IPoint p) {
        canvas.setColor(color);
        canvas.drawSquare(p, squareSize);
    }

    @Override
    public void setColor(Color c) {}

    @Override
    public void clear() {
        canvas.clear();
    }
}
