package com.zhivaevartemsaveg.geometry;

import com.zhivaevartemsaveg.Ref;
import com.zhivaevartemsaveg.geometry.strategy.IReduceSegmentsStrategy;
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
    public final <T> T reduceSegments(IReduceSegmentsStrategy<T> strategy) {
        int segmentsCount = strategy.getSegmentsCount();
        Ref<T> accRef = new Ref<>(strategy.getInitialAccumulator());

        List<Line> segments = new ArrayList<>(segmentsCount);
        IPoint lastPoint = getPoint(0);
        for (int i = 0; i < segmentsCount; i++) {
            double t = 1.0 * (i + 1) / segmentsCount;
            IPoint point = getPoint(t);
            Line segment = new Line(lastPoint, point);
            lastPoint = point;
            segments.add(segment);
            if (!strategy.reduce(accRef, t, segments)) break;
        }

        return accRef.value;
    }
}
