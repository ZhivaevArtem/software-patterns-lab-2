package com.zhivaevartemsaveg.visual;

import com.zhivaevartemsaveg.visual.context.IDrawScheme;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
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
    public Iterator<IDrawable> iterator() {
        return new DrawableCompositeIterator();
    }

    private class DrawableCompositeIterator implements Iterator<IDrawable> {
        private int index = 0;
        private Iterator<IDrawable> nestedIterator = null;
        private final DrawableComposite parent = DrawableComposite.this;

        private DrawableCompositeIterator() {
            if (parent.drawables.size() > index) {
                nestedIterator = parent.drawables.get(index++).iterator();
            }
        }

        @Override
        public boolean hasNext() {
            if (nestedIterator != null) {
                return nestedIterator.hasNext();
            }
            return false;
        }

        @Override
        public IDrawable next() {
            if (nestedIterator != null) {
                IDrawable next = nestedIterator.next();
                if (!nestedIterator.hasNext()) {
                    nestedIterator = index >= parent.drawables.size()
                            ? null
                            : parent.drawables.get(index++).iterator();
                }
                return next;
            }
            return null;
        }
    }
}
