package com.zhivaevartemsaveg.visual.context;

import com.zhivaevartemsaveg.geometry.ICurve;
import com.zhivaevartemsaveg.geometry.IPoint;

import java.awt.*;

public interface IDrawScheme {
    void drawFirstPoint(IPoint p);
    void drawSegment(IPoint a, IPoint b);
    void drawLastPoint(IPoint p);

    void fillCircle(IPoint p, double rad);

    void setColor(Color c);
    void clear();
}
