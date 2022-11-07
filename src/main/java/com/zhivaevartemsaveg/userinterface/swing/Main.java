package com.zhivaevartemsaveg.userinterface.swing;

import com.zhivaevartemsaveg.geometry.Bezier;
import com.zhivaevartemsaveg.geometry.Point;
import com.zhivaevartemsaveg.visual.IDrawable;
import com.zhivaevartemsaveg.visual.VisualBezier;
import com.zhivaevartemsaveg.visual.VisualLine;
import com.zhivaevartemsaveg.visual.context.*;
import com.zhivaevartemsaveg.visual.context.swing.SwingCanvas;

import javax.swing.*;
import java.awt.*;

public class Main {
    public static void main(String[] args) {
        SwingCanvas left = new SwingCanvas(),
                right = new SwingCanvas();

        JFrame frame = new JFrame("Swing");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1280, 720);

        GridLayout layout = new GridLayout(1, 2);
        frame.setLayout(layout);

        frame.add(left);
        frame.add(right);
        frame.setVisible(true);

        SvgCanvas leftSvg = new SvgCanvas(),
                rightSvg = new SvgCanvas();

        IDrawScheme leftScheme = new DrawSchemeGreen(new CanvasComposer(
                left,
                leftSvg
        ));
        IDrawScheme rightScheme = new DrawSchemeBlack(new CanvasComposer(
                right,
                rightSvg
        ));

        IDrawable curve = new VisualBezier(new Point(80, 100), new Point(300, 300), new Point(100, 300), new Point(300, 500));
        IDrawable line = new VisualLine(new Point(100, 100), new Point(250, 350));
        line.draw(leftScheme);
        line.draw(rightScheme);
        curve.draw(leftScheme);
        curve.draw(rightScheme);

        String leftSvgCode = leftSvg.getSvg();
        String rightSvgCode = rightSvg.getSvg();

        System.out.println("leftSvgCode = " + leftSvgCode);
        System.out.println("rightSvgCode = " + rightSvgCode);
    }
}
