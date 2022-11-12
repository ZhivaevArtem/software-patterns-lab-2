package com.zhivaevartemsaveg.geometry.strategy;

import com.zhivaevartemsaveg.Ref;
import com.zhivaevartemsaveg.geometry.ICurve;
import com.zhivaevartemsaveg.geometry.IPoint;
import com.zhivaevartemsaveg.geometry.Line;
import java.util.ArrayList;
import java.util.List;

public final class SegmentReducer {
    public <T> T reduceSegments(ICurve curve, IReduceSegmentsStrategy<T> strategy) {
        int segmentsCount = strategy.getSegmentsCount();
        Ref<T> accRef = new Ref<>(strategy.getInitialAccumulator());

        List<Line> segments = new ArrayList<>(segmentsCount);
        IPoint lastPoint = curve.getPoint(0);
        for (int i = 0; i < segmentsCount; i++) {
            double t = 1.0 * (i + 1) / segmentsCount;
            IPoint point = curve.getPoint(t);
            Line segment = new Line(lastPoint, point);
            lastPoint = point;
            segments.add(segment);
            if (!strategy.reduce(accRef, t, segments)) break;
        }

        return accRef.value;
    }
}
