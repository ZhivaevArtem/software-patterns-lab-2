package com.zhivaevartemsaveg.visual;

import com.zhivaevartemsaveg.visual.context.IDrawScheme;
import com.zhivaevartemsaveg.visual.iterator.IIterator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class DrawableComposite implements IDrawable {
    private final List<IDrawable> drawables = new ArrayList<>();

    public DrawableComposite(IDrawable ...drawables) {
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

    @Override
    public Iterator<IDrawable> iterator() {
        return new Iterator<IDrawable>() {
            private int index = 0;
            private Iterator<IDrawable> nestedIterator = nextIterator();

            private Iterator<IDrawable> nextIterator() {
                if (index < drawables.size()) {
                    return drawables.get(index++).iterator();
                }
                return null;
            }

            private boolean nestedHasNext() {
                return nestedIterator != null && nestedIterator.hasNext();
            }

            @Override
            public boolean hasNext() {
                return nestedIterator != null;
            }

            @Override
            public IDrawable next() {
                if (nestedIterator == null) return null;
                IDrawable next = nestedIterator.next();
                while (!nestedHasNext()) {
                    nestedIterator = nextIterator();
                    if (nestedIterator == null) {
                        break;
                    }
                }
                return next;
            }
        };
    }
}
