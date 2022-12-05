package com.zhivaevartemsaveg.visual.observer;

import com.zhivaevartemsaveg.geometry.IPoint;
import com.zhivaevartemsaveg.geometry.Point;

public class MouseEvent implements IEvent {
    private final IPoint point;
    private final boolean pressed;
    private final IPoint movement;

    public IPoint getPoint() {
        return point;
    }

    public boolean isPressed() {
        return pressed;
    }

    public IPoint getMovement() {
        return movement;
    }

    public MouseEvent(IPoint point, IPoint prevPoint, boolean pressed) {
        this.point = point;
        this.pressed = pressed;
        if (prevPoint != null) {
            movement = new Point(
                    point.getX() - prevPoint.getX(),
                    point.getY() - prevPoint.getY()
            );
        } else {
            movement = new Point(0, 0);
        }
    }

    @Override
    public String toString() {
        return point.toString() + " " + pressed;
    }
}
