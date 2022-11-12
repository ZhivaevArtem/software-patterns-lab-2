package com.zhivaevartemsaveg.geometry;

public interface ICurve {
    IPoint getPoint(double t);

    double iterateOverSegments(IIterateOverSegmentStrategy strategy);
}
