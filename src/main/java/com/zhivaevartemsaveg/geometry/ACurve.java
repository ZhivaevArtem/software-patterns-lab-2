package com.zhivaevartemsaveg.geometry;

public abstract class ACurve implements ICurve {
    protected final IPoint a, b;

    public ACurve(IPoint a, IPoint b) {
        this.a = a;
        this.b = b;
    }
}
