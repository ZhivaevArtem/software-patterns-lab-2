package com.zhivaevartemsaveg.visual.context.swing;

import com.zhivaevartemsaveg.geometry.IPoint;
import com.zhivaevartemsaveg.visual.context.ADrawContext;

import javax.swing.*;

public class DrawContextSwing extends ADrawContext {
    private JFrame frame;
    private Canvas canvas;

    private DrawContextSwing() {}

    public static DrawContextSwing create(int w, int h) {
        DrawContextSwing ctx = new DrawContextSwing();
        ctx.frame = new JFrame("Java Swing");
        ctx.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ctx.frame.setSize(w, h);
        ctx.frame.setResizable(false);
        ctx.canvas = new Canvas();
        ctx.frame.add(ctx.canvas);
        ctx.frame.setVisible(true);
        return ctx;
    }

    @Override
    public void drawLine(IPoint a, IPoint b) {
        int x1 = (int) a.getX();
        int y1 = (int) a.getY();
        int x2 = (int) b.getX();
        int y2 = (int) b.getY();
        this.canvas.getGraphics().drawLine(
                x1,
                y1,
                x2,
                y2
        );
    }

    @Override
    public void clean() {
        this.canvas.getGraphics().clearRect(0, 0, 999999, 999999);
    }
}
