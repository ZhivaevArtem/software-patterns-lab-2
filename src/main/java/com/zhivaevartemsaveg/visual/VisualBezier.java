package com.zhivaevartemsaveg.visual;

import com.zhivaevartemsaveg.geometry.Bezier;
import com.zhivaevartemsaveg.geometry.IPoint;

public class VisualBezier extends VisualCurve {
    public VisualBezier(IPoint a, IPoint b, IPoint c, IPoint d) {
        super(new Bezier(a, b, c, d));
    }
}
