package com.zhivaevartemsaveg.visual.decorator;

import com.zhivaevartemsaveg.geometry.IArea;
import com.zhivaevartemsaveg.geometry.IPoint;
import com.zhivaevartemsaveg.geometry.MutableVector;
import com.zhivaevartemsaveg.visual.IDrawable;
import com.zhivaevartemsaveg.visual.IDrawableArea;
import com.zhivaevartemsaveg.visual.context.IDrawScheme;

import java.awt.*;

public class VisualMoveAreaDecorator implements IDrawableArea {
    private final IDrawableArea drawable;
    private final MutableVector moveBy;
    private Color color = Color.WHITE;

    public VisualMoveAreaDecorator(IDrawableArea drawable) {
        this.drawable = drawable;
        moveBy = new MutableVector();
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public void move(IPoint movement) {
        moveBy.add(movement);
    }

    public void moveTo(IPoint center) {
        moveBy.setX(center.getX() - drawable.getCenter().getX());
        moveBy.setY(center.getY() - drawable.getCenter().getY());
    }

    @Override
    public void draw(IDrawScheme context) {
        drawable.draw(new IDrawScheme() {
            @Override
            public void drawFirstPoint(IPoint p) {
                context.setColor(color);
                context.drawFirstPoint(moveBy.fromPoint(p));
            }

            @Override
            public void drawSegment(IPoint a, IPoint b) {
                context.setColor(color);
                context.drawSegment(moveBy.fromPoint(a), moveBy.fromPoint(b));
            }

            @Override
            public void drawLastPoint(IPoint p) {
                context.setColor(color);
                context.drawLastPoint(moveBy.fromPoint(p));
            }

            @Override
            public void fillCircle(IPoint p, double rad) {
                context.setColor(color);
                context.fillCircle(moveBy.fromPoint(p), rad);
            }

            @Override
            public void setColor(Color c) {
                context.setColor(color);
                context.setColor(c);
            }

            @Override
            public void clear() {
                context.setColor(color);
                context.clear();
            }
        });
    }

    @Override
    public boolean contains(IPoint p) {
        return drawable.contains(moveBy.toPoint(p));
    }

    @Override
    public IPoint getCenter() {
        return moveBy.toPoint(drawable.getCenter());
    }
}
