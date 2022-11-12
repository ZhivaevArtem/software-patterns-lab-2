package com.zhivaevartemsaveg.geometry.strategy;

import com.zhivaevartemsaveg.Ref;
import com.zhivaevartemsaveg.geometry.Line;
import java.util.List;

public interface IReduceSegmentsStrategy<T> {
    default int getSegmentsCount() {
        return 1000;
    }

    default T getInitialAccumulator() {
        return null;
    }

    boolean reduce(Ref<T> accRef, double t, List<Line> segments);
}