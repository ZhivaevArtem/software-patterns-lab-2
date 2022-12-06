package com.zhivaevartemsaveg.visual.observer;

import com.zhivaevartemsaveg.Ref;
import com.zhivaevartemsaveg.geometry.IPoint;
import com.zhivaevartemsaveg.visual.decorator.VisualMoveAreaDecorator;

import java.awt.*;
import java.util.function.Function;

public class VisualMoveAreaDecoratorObserver implements IObserver<MouseEvent> {
    private final VisualMoveAreaDecorator v;
    private final Ref<Color> colorRef = new Ref<>(Color.WHITE);
    private final Ref<Boolean> isDraggingRef = new Ref<>(false);
    private final Ref<VisualMoveAreaDecorator> draggableObject;

    private Function<Void, Void> onChangeFunction = null;

    public VisualMoveAreaDecoratorObserver(VisualMoveAreaDecorator shape, Ref<VisualMoveAreaDecorator> draggableObject) {
        this.v = shape;
        this.draggableObject = draggableObject;
        v.setColor(colorRef.value);
    }

    public void onChange(Function<Void, Void> f) {
        onChangeFunction = f;
    }

    public VisualMoveAreaDecorator getShape() {
        return v;
    }

    @Override
    public void update(MouseEvent event) {
        boolean changed = false;
        if (v.contains(event.getPoint())) {
            if (colorRef.value != Color.GREEN) {
                colorRef.value = Color.GREEN;
                v.setColor(colorRef.value);
                changed = true;
            }
            if (event.isPressed() && !isDraggingRef.value && draggableObject.value == null) {
                isDraggingRef.value = true;
                draggableObject.value = v;
            }
        } else {
            if (colorRef.value != Color.WHITE) {
                colorRef.value = Color.WHITE;
                v.setColor(colorRef.value);
                changed = true;
            }
        }
        if (isDraggingRef.value && event.isPressed()) {
            IPoint movement = event.getMovement();
            v.move(movement);
            changed = true;
        }
        if (!event.isPressed()) {
            isDraggingRef.value = false;
            draggableObject.value = null;
        }
        if (changed) {
            if (onChangeFunction != null) {
                onChangeFunction.apply(null);
            }
        }
    }
}
