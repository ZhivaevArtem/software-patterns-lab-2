package com.zhivaevartemsaveg.geometry.decorator;

import com.zhivaevartemsaveg.geometry.ICurve;
import com.zhivaevartemsaveg.geometry.IPoint;
import com.zhivaevartemsaveg.geometry.Point;
import com.zhivaevartemsaveg.geometry.strategy.IReduceSegmentsStrategy;

public class MoveTo implements ICurve {
    private final ICurve curve;
    private final IPoint moveBy;

    public MoveTo(ICurve curve, IPoint moveTo) {
        this.curve = curve;
        IPoint firstPoint = curve.getPoint(0);
        moveBy = new Point(
                moveTo.getX() - firstPoint.getX(),
                moveTo.getY() - firstPoint.getY()
        );
    }

    @Override
    public IPoint getPoint(double t) {
        IPoint p = curve.getPoint(t);
        return new Point(
                p.getX() + moveBy.getX(),
                p.getY() + moveBy.getY()
        );
    }

    @Override
    public <T> T reduceSegments(IReduceSegmentsStrategy<T> strategy) {
        return curve.reduceSegments(strategy);
    }
}
