package com.zhivaevartemsaveg.visual.context;

import com.zhivaevartemsaveg.geometry.IPoint;

import java.awt.*;

public interface ICanvas {
    void setColor(Color c);
    void clear();

    void setSize(int w, int h);

    void drawLine(IPoint a, IPoint b);
    void drawCircle(IPoint center, double rad);
    void drawSquare(IPoint center, double size);
    void drawArrow(IPoint p, double angle, double length);
}
