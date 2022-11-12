package com.zhivaevartemsaveg.visual;

import com.zhivaevartemsaveg.geometry.ICurve;
import com.zhivaevartemsaveg.geometry.IPoint;
import com.zhivaevartemsaveg.visual.context.IDrawScheme;

public class VisualCurve implements IDrawable, ICurve {
    private ICurve curve;

    public VisualCurve(ICurve curve) {
        this.curve = curve;
    }

    @Override
    public IPoint getPoint(double t) {
        return this.curve.getPoint(t);
    }

    @Override
    public void draw(IDrawScheme context) {
        int segmentsCount = 20;

        context.drawFirstPoint(getPoint(0));

        IPoint lastPoint = getPoint(0);
        for (int i = 1; i <= segmentsCount; i++) {
            double t = (1.0 * i) / segmentsCount;
            IPoint point = getPoint(t);
            context.drawSegment(lastPoint, point);
            lastPoint = point;
        }

        context.drawLastPoint(getPoint(1));
    }
}
