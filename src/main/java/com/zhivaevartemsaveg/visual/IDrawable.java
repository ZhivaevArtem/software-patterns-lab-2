package com.zhivaevartemsaveg.visual;

import com.zhivaevartemsaveg.visual.context.IDrawScheme;

import java.util.Iterator;

public interface IDrawable extends Iterable<IDrawable> {
    void draw(IDrawScheme context);

    @Override
    default Iterator<IDrawable> iterator() {
        return new Iterator<IDrawable>() {
            IDrawable nextItem = IDrawable.this;

            @Override
            public boolean hasNext() {
                return nextItem != null;
            }

            @Override
            public IDrawable next() {
                IDrawable next = nextItem;
                nextItem = null;
                return next;
            }
        };
    }
}
