package com.zhivaevartemsaveg.visual.context.swing;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.PathIterator;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

public class ColoredShape implements Shape {
    private final Shape shape;
    private final Color color;
    private boolean filled = false;

    public ColoredShape(Shape shape, Color color) {
        this.shape = shape;
        this.color = color;
    }

    public ColoredShape(Shape shape, Color color, boolean filled) {
        this(shape, color);
        this.filled = filled;
    }

    public boolean isFilled() {
        return filled;
    }

    public Color getColor() {
        return color;
    }

    @Override
    public Rectangle getBounds() {
        return shape.getBounds();
    }

    @Override
    public Rectangle2D getBounds2D() {
        return shape.getBounds2D();
    }

    @Override
    public boolean contains(double x, double y) {
        return shape.contains(x, y);
    }

    @Override
    public boolean contains(Point2D p) {
        return shape.contains(p);
    }

    @Override
    public boolean intersects(double x, double y, double w, double h) {
        return shape.intersects(x, y, w, h);
    }

    @Override
    public boolean intersects(Rectangle2D r) {
        return shape.intersects(r);
    }

    @Override
    public boolean contains(double x, double y, double w, double h) {
        return shape.contains(x, y, w, h);
    }

    @Override
    public boolean contains(Rectangle2D r) {
        return shape.contains(r);
    }

    @Override
    public PathIterator getPathIterator(AffineTransform at) {
        return shape.getPathIterator(at);
    }

    @Override
    public PathIterator getPathIterator(AffineTransform at, double flatness) {
        return shape.getPathIterator(at, flatness);
    }
}
