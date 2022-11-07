package com.zhivaevartemsaveg.geometry;

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
}
