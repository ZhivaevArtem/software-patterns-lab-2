package com.zhivaevartemsaveg.visual;

import com.zhivaevartemsaveg.visual.context.IDrawScheme;
import com.zhivaevartemsaveg.visual.iterator.IIterator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DrawableComposite implements IDrawable {
    private final List<IDrawable> drawables = new ArrayList<>();

    public DrawableComposite(IDrawable drawable, IDrawable ...drawables) {
        this.drawables.add(drawable);
        this.drawables.addAll(Arrays.asList(drawables));
    }

    @Override
    public void draw(IDrawScheme context) {
        drawables.forEach((drawable) -> {
            drawable.draw(context);
        });
    }

    @Override
    public void iterate(IIterator<IDrawable> iterator) {
        drawables.forEach((drawable) -> {
            drawable.iterate(iterator);
        });
    }
}
