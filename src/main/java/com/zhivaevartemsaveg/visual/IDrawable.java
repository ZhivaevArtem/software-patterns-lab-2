package com.zhivaevartemsaveg.visual;

import com.zhivaevartemsaveg.visual.context.IDrawScheme;
import com.zhivaevartemsaveg.visual.iterator.IIterator;

public interface IDrawable {
    void draw(IDrawScheme context);

    default void iterate(IIterator<IDrawable> iterator) {
        iterator.iterate(this);
    }
}
