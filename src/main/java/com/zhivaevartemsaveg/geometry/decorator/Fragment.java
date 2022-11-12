package com.zhivaevartemsaveg.geometry.decorator;

import com.zhivaevartemsaveg.geometry.ICurve;
import com.zhivaevartemsaveg.geometry.IPoint;
import com.zhivaevartemsaveg.utils.Algebra;

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
        return curve.getPoint(Algebra.map(t, 0, 1, tStart, tEnd));
    }
}
