package com.zhivaevartemsaveg.visual.observer;

import com.zhivaevartemsaveg.geometry.IPoint;

public class MouseEvent implements IEvent {
    private final IPoint point;
    private final boolean pressed;

    public IPoint getPoint() {
        return point;
    }

    public boolean isPressed() {
        return pressed;
    }

    public MouseEvent(IPoint point, boolean pressed) {
        this.point = point;
        this.pressed = pressed;
    }

    @Override
    public String toString() {
        return point.toString() + " " + pressed;
    }
}
