package com.zhivaevartemsaveg.userinterface;

import com.zhivaevartemsaveg.geometry.IPoint;
import com.zhivaevartemsaveg.geometry.Point;
import com.zhivaevartemsaveg.visual.VisualBezier;
import com.zhivaevartemsaveg.visual.VisualLine;
import com.zhivaevartemsaveg.visual.context.IDrawScheme;

import java.util.Scanner;

public class Main {
    private static VisualLine generateRandomLine(int width, int height) {
        IPoint a = new Point(
                Math.random() * width,
                Math.random() * height
        );
        IPoint b = new Point(
                Math.random() * width,
                Math.random() * height
        );
        System.out.println("Line: a" + a + " b" + b);
        return new VisualLine(a, b);
    }

    private static VisualBezier generateRandomBezier(int width, int height) {

        IPoint a = new Point(
                Math.random() * width,
                Math.random() * height
        );
        IPoint b = new Point(
                Math.random() * width,
                Math.random() * height
        );
        IPoint c = new Point(
                Math.random() * width,
                Math.random() * height
        );
        IPoint d = new Point(
                Math.random() * width,
                Math.random() * height
        );
        System.out.println("Bezier: a" + a + " b" + b + " c" + c + " d" + d);
        return new VisualBezier(a, b, c, d);
    }

    private static VisualLine generateLine(int x1, int y1, int x2, int y2) {
        IPoint a = new Point(x1, y1);
        IPoint b = new Point(x2, y2);
        System.out.println("Line: a" + a + " b" + b);
        return new VisualLine(a, b);
    }

    private static VisualBezier generateBezier(int x1, int y1, int x2, int y2, int x3, int y3, int x4, int y4) {
        IPoint a = new Point(x1, y1);
        IPoint b = new Point(x2, y2);
        IPoint c = new Point(x3, y3);
        IPoint d = new Point(x4, y4);
        System.out.println("Bezier: a" + a + " b" + b + " c" + c + " d" + d);
        return new VisualBezier(a, b, c, d);
    }

    public static void main(String[] args) throws InterruptedException {
//        IDrawScheme ctx = DrawContextFactory.createContext();

        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            String[] commands = sc.nextLine().split(" ");
            switch (commands[0]) {
                case "clear":
//                    ctx.clear();
                    break;
                case "line":
                    VisualLine line = generateLine(
                            Integer.parseInt(commands[1]),
                            Integer.parseInt(commands[2]),
                            Integer.parseInt(commands[3]),
                            Integer.parseInt(commands[4])
                    );
//                    line.draw(ctx);
                    break;
                case "bezier":
                    VisualBezier bezier = generateBezier(
                            Integer.parseInt(commands[1]),
                            Integer.parseInt(commands[2]),
                            Integer.parseInt(commands[3]),
                            Integer.parseInt(commands[4]),
                            Integer.parseInt(commands[5]),
                            Integer.parseInt(commands[6]),
                            Integer.parseInt(commands[7]),
                            Integer.parseInt(commands[8])
                    );
//                    bezier.draw(ctx);
                    break;
            }
        }

    }
}
