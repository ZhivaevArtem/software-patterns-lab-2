package com.zhivaevartemsaveg.visual;

import com.zhivaevartemsaveg.geometry.ICurve;

public abstract class VisualCurve implements IDrawable {
    protected final ICurve curve;

    protected VisualCurve(ICurve curve) {
        this.curve = curve;
    }
}
