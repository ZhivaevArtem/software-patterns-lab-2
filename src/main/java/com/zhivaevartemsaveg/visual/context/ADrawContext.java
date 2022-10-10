package com.zhivaevartemsaveg.visual.context;

import com.zhivaevartemsaveg.geometry.ICurve;
import com.zhivaevartemsaveg.geometry.IPoint;

import java.util.ArrayList;
import java.util.List;

public abstract class ADrawContext implements IDrawContext {
    @Override
    public void drawCurve(ICurve curve) {
        final int pointCount = 20;
        List<IPoint> points = new ArrayList<>(pointCount);
        for (int i = 0; i < pointCount; i++) {
            points.add(curve.getPoint(i * 1.0 / (pointCount - 1)));
        }
        for (int i = 1; i < points.size(); i++) {
            IPoint a = points.get(i - 1);
            IPoint b = points.get(i);
            drawLine(a, b);
        }
    }
}
