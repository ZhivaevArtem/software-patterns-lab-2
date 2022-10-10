package com.zhivaevartemsaveg.visual;

import com.zhivaevartemsaveg.geometry.IPoint;
import com.zhivaevartemsaveg.geometry.Line;

public class VisualLine extends VisualCurve {
    public VisualLine(IPoint a, IPoint b) {
        super(new Line(a, b));
    }
}
