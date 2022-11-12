package com.zhivaevartemsaveg.geometry;

import com.zhivaevartemsaveg.geometry.strategy.IReduceSegmentsStrategy;

public interface ICurve {
    IPoint getPoint(double t);

    <T> T reduceSegments(IReduceSegmentsStrategy<T> strategy);
}
