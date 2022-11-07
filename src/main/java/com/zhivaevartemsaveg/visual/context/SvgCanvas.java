package com.zhivaevartemsaveg.visual.context;

import com.zhivaevartemsaveg.geometry.IPoint;
import com.zhivaevartemsaveg.geometry.Point;

import java.awt.*;
import java.util.*;
import java.util.List;
import java.util.stream.Collectors;

public class SvgCanvas implements ICanvas {
    private Color color;
    private final List<String> tags = new LinkedList<>();

    public String getSvg() {
        return "<svg>\n"
                + tags.stream().map(s -> "\t" + s).collect(Collectors.joining("\n"))
                + "\n</svg>";
    }

    @Override
    public void setColor(Color c) {
        color = c;
    }

    @Override
    public void clear() {
        tags.clear();
    }

    private String hashColor(Color c) {
        return "rgba(" + c.getRed() + ", " + c.getGreen() + ", " + c.getBlue() + ", " + (c.getAlpha() * 1.0 / 255) + ")";
    }

    @Override
    public void drawLine(IPoint a, IPoint b) {
        tags.add(String.format(Locale.US, "<line x1=\"%f\" y1=\"%f\" x2=\"%f\" y2=\"%f\" stroke=\"%s\" />",
                a.getX(), a.getY(), b.getX(), b.getY(),
                hashColor(color)
        ));
    }

    @Override
    public void drawCircle(IPoint center, double rad) {
        tags.add(String.format(Locale.US, "<circle cx=\"%f\" cy=\"%f\" r=\"%f\" fill=\"none\" stroke=\"%s\" />",
                center.getX(), center.getY(), rad, hashColor(color)));
    }

    @Override
    public void drawSquare(IPoint center, double size) {
        tags.add(String.format(Locale.US, "<rect fill=\"none\" stroke=\"%s\" x=\"%f\" y=\"%f\" width=\"%f\" height=\"%f\" />",
                hashColor(color),
                center.getX() - size / 2, center.getY() - size / 2,
                size, size));
    }

    private void rotatePoints(List<IPoint> points, IPoint centroid, double rotAngle) {
        List<IPoint> newPoints = new ArrayList<>(points);
        double x, y;
        for (int i = 0; i < points.size(); i++) {
            x = points.get(i).getX() - centroid.getX();
            y = points.get(i).getY() - centroid.getY();
            newPoints.set(i, new Point(
                    centroid.getX() + (int) Math.round(((x * Math.cos(rotAngle)) - (y * Math.sin(rotAngle)))),
                    centroid.getY() + (int) Math.round(((x * Math.sin(rotAngle)) + (y * Math.cos(rotAngle))))
            ));
        }

        for (int i = 0; i < newPoints.size(); i++) {
            points.set(i, newPoints.get(i));
        }
    }

    @Override
    public void drawArrow(IPoint p, double angle, double length) {
        IPoint a = new Point(0, 2 * length / 3);
        IPoint b = new Point(-length / 3, -length / 3);
        IPoint c = new Point(length / 3, -length / 3);
        List<IPoint> points = new ArrayList<>(Arrays.asList(a, b, c));

        rotatePoints(points, new Point(0, 0), angle);
        for (int i = 0; i < points.size(); i++) {
            IPoint point = points.get(i);
            IPoint newPoint = new Point(
                    point.getX() + p.getX(),
                    point.getY() + p.getY()
            );
            points.set(i, newPoint);
        }

        a = points.get(0);
        b = points.get(1);
        c = points.get(2);

        tags.add(String.format(Locale.US, "<polygon points=\"%f,%f %f,%f %f,%f\" fill=\"none\" stroke=\"%s\" />",
                a.getX(), a.getY(),
                b.getX(), b.getY(),
                c.getX(), c.getY(),
                hashColor(color)
        ));
    }
}