package com.zhivaevartemsaveg.userinterface;

import com.zhivaevartemsaveg.geometry.IPoint;
import com.zhivaevartemsaveg.geometry.Line;
import com.zhivaevartemsaveg.geometry.Point;
import com.zhivaevartemsaveg.visual.VisualBezier;
import com.zhivaevartemsaveg.visual.VisualLine;
import com.zhivaevartemsaveg.visual.context.DrawContextFactory;
import com.zhivaevartemsaveg.visual.context.IDrawContext;
import com.zhivaevartemsaveg.visual.context.swing.Canvas;

import javax.swing.*;
import java.util.Scanner;

public class Main {
    private static VisualLine generateLine(int width, int height) {
        IPoint a = new Point(
                Math.random() * 800,
                Math.random() * 600
        );
        IPoint b = new Point(
                Math.random() * 800,
                Math.random() * 600
        );
        System.out.println("Line: a" + a + " b" + b);
        return new VisualLine(a, b);
    }

    private static VisualBezier generateBezier(int width, int height) {

        IPoint a = new Point(
                Math.random() * 800,
                Math.random() * 600
        );
        IPoint b = new Point(
                Math.random() * 800,
                Math.random() * 600
        );
        IPoint c = new Point(
                Math.random() * 800,
                Math.random() * 600
        );
        IPoint d = new Point(
                Math.random() * 800,
                Math.random() * 600
        );
        System.out.println("Bezier: a" + a + " b" + b + " c" + c + " d" + d);
        return new VisualBezier(a, b, c, d);
    }

    public static void main(String[] args) throws InterruptedException {
        IDrawContext ctx = DrawContextFactory.createContext();

        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            System.out.println();
            switch (sc.nextLine().trim()) {
                case "clear":
                    ctx.clean();
                    break;
                case "line":
                    VisualLine line = generateLine(800, 600);
                    line.draw(ctx);
                    break;
                case "bezier":
                    VisualBezier bezier = generateBezier(800, 600);
                    bezier.draw(ctx);
                    break;
            }
        }

    }
}
