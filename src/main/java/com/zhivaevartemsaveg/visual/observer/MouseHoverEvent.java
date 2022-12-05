package com.zhivaevartemsaveg.visual.observer;

public class MouseHoverEvent implements IEvent {
    public final boolean hoverState;

    public MouseHoverEvent(boolean hoverState) {
        this.hoverState = hoverState;
    }
}
