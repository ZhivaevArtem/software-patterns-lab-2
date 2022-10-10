package com.zhivaevartemsaveg.visual.context;

import com.zhivaevartemsaveg.geometry.ICurve;
import com.zhivaevartemsaveg.geometry.IPoint;

public interface IDrawContext {
    void clean();

    void drawLine(IPoint a, IPoint b);
    void drawCurve(ICurve curve);
}
