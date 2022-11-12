package com.zhivaevartemsaveg.geometry;

import com.zhivaevartemsaveg.Ref;

import java.util.ArrayList;
import java.util.List;

public abstract class ACurve implements ICurve {
    private IPoint a, b;

    protected ACurve(IPoint a, IPoint b) {
        this.a = a;
        this.b = b;
    }

    protected IPoint getA() {
        return a;
    }

    protected IPoint getB() {
        return b;
    }

    @Override
    public double iterateOverSegments(IIterateOverSegmentStrategy strategy) {
        int segmentsCount = strategy.getPrecision();
        IPoint[] points = new IPoint[segmentsCount + 1];
        Ref<Double> acc = new Ref<>(0.0);
        points[0] = getPoint(0);
        List<Line> segments = new ArrayList<>(segmentsCount);
        for (int i = 1; i <= segmentsCount; i++) {
            double t = 1.0 * i / segmentsCount;
            points[i] = getPoint(t);
            segments.add(new Line(points[i-1], points[i]));
            if (!strategy.reduceSegment(acc, segments, t))
                break;
        }
        return acc.value;
    }
}
