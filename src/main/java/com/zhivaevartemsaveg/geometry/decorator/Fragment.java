package com.zhivaevartemsaveg.geometry.decorator;

import com.zhivaevartemsaveg.geometry.ICurve;
import com.zhivaevartemsaveg.geometry.IPoint;
import com.zhivaevartemsaveg.geometry.strategy.IReduceSegmentsStrategy;

public class Fragment implements ICurve {
    private final ICurve curve;
    private final double tStart;
    private final double tEnd;

    public Fragment(ICurve curve, double tStart, double tEnd) {
        this.curve = curve;
        this.tStart = tStart;
        this.tEnd = tEnd;
    }

    @Override
    public IPoint getPoint(double t) {
        return curve.getPoint(t * (tEnd - tStart) + tStart);
    }

    @Override
    public <T> T reduceSegments(IReduceSegmentsStrategy<T> strategy) {
        return curve.reduceSegments(strategy);
    }
}
