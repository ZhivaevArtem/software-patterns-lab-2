package com.zhivaevartemsaveg.visual;

import com.zhivaevartemsaveg.geometry.ICurve;
import com.zhivaevartemsaveg.geometry.IPoint;
import com.zhivaevartemsaveg.visual.context.IDrawContext;

public abstract class VisualCurve implements IDrawable, ICurve {
    private ICurve curve;

    protected VisualCurve(ICurve curve) {
        this.curve = curve;
    }

    @Override
    public IPoint getPoint(double t) {
        return this.curve.getPoint(t);
    }

    @Override
    public void draw(IDrawContext context) {
        context.drawCurve(this);
    }
}
