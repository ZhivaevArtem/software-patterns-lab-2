package com.zhivaevartemsaveg.visual.context.swing;

import com.zhivaevartemsaveg.geometry.IPoint;
import com.zhivaevartemsaveg.utils.Algebra;
import com.zhivaevartemsaveg.visual.context.ICanvas;

import java.awt.*;
import java.awt.geom.Arc2D;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class SwingCanvas extends Canvas implements ICanvas {
    private final List<ColoredShape> shapes = new LinkedList<>();

    private Graphics2D graphics;
    private Color color;

    private Graphics2D getGraphicsInstance() {
        if (graphics == null) {
            graphics = (Graphics2D) getGraphics();
        }
        return graphics;
    }

    @Override
    public void paint(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g.clearRect(0, 0, 99999, 99999);
        g.setColor(Color.GREEN);
        List<ColoredShape> shapes = new ArrayList<>(this.shapes);
        for (ColoredShape shape : shapes) {
            g2d.setColor(shape.getColor());
            g2d.draw(shape);
        }
    }

    @Override
    public void setColor(Color c) {
        color = c;
        getGraphicsInstance().setColor(c);
    }

    @Override
    public void clear() {
        shapes.clear();
        getGraphicsInstance().clearRect(0, 0, 99999, 99999);
    }

    private void addShape(Shape shape) {
        shapes.add(new ColoredShape(shape, color));
        repaint();
    }

    @Override
    public void drawLine(IPoint a, IPoint b) {
        addShape(new Line2D.Double(
                a.getX(),
                a.getY(),
                b.getX(),
                b.getY()
        ));
    }

    @Override
    public void drawCircle(IPoint center, double rad) {
        addShape(new Arc2D.Double(center.getX() - rad, center.getY() - rad,
                rad * 2, rad * 2, 0, 360, Arc2D.OPEN));
    }

    @Override
    public void drawSquare(IPoint center, double size) {
        addShape(new Rectangle2D.Double(
                center.getX() - size / 2,
                center.getY() - size / 2,
                size, size
        ));
    }

    @Override
    public void drawArrow(IPoint p, double angle, double length) {
        Polygon polygon = new Polygon();
        polygon.addPoint(0, (int) (2 * length / 3));
        polygon.addPoint((int) (- length / 3), (int) (-length / 3));
        polygon.addPoint((int) (length / 3), (int) (-length / 3));
        rotatePolygon(polygon, angle, new Point(0, 0));

        for (int i = 0; i < polygon.xpoints.length; i++) {
            polygon.xpoints[i] += p.getX();
            polygon.ypoints[i] += p.getY();
        }
        addShape(polygon);
    }

    public static void rotatePolygon(Polygon pg, double rotAngle, Point centroid) {
        List<IPoint> points = new ArrayList<>(pg.xpoints.length);
        for (int i = 0; i < pg.xpoints.length; i++) {
            points.add(new com.zhivaevartemsaveg.geometry.Point(
                    pg.xpoints[i],
                    pg.ypoints[i]
            ));
        }
        Algebra.rotatePoints(points, new com.zhivaevartemsaveg.geometry.Point(
                centroid.x, centroid.y
        ), rotAngle);
        for (int i = 0; i < points.size(); i++) {
            pg.xpoints[i] = (int) Math.round(points.get(i).getX());
            pg.ypoints[i] = (int) Math.round(points.get(i).getY());
        }
    }

}
