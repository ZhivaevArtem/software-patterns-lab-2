package com.zhivaevartemsaveg.visual;

import com.zhivaevartemsaveg.visual.context.IDrawScheme;
import com.zhivaevartemsaveg.visual.iterator.IIterable;
import com.zhivaevartemsaveg.visual.iterator.IIterator;

import java.util.Iterator;

public interface IDrawable extends Iterable<IDrawable>, IIterable<IDrawable> {
    void draw(IDrawScheme context);

    @Override
    default void iterate(IIterator<IDrawable> iterator) {
        iterator.iterate(this);
    }

    @Override
    default Iterator<IDrawable> iterator() {
        return new Iterator<IDrawable>() {
            IDrawable next = IDrawable.this;

            @Override
            public boolean hasNext() {
                return next != null;
            }

            @Override
            public IDrawable next() {
                IDrawable next = this.next;
                if (next != null) {
                    this.next = null;
                }
                return next;
            }
        };
    }
}
