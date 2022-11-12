package com.zhivaevartemsaveg.geometry;

import com.zhivaevartemsaveg.Ref;

import java.util.List;

public interface IIterateOverSegmentStrategy {
    default int getPrecision() {
        return 20;
    }

    boolean reduceSegment(Ref<Double> acc, List<Line> segments, double t);
}
