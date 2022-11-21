package com.zhivaevartemsaveg.userinterface.swing;

import com.zhivaevartemsaveg.geometry.Point;
import com.zhivaevartemsaveg.visual.DrawableComposite;
import com.zhivaevartemsaveg.visual.IDrawable;
import com.zhivaevartemsaveg.visual.VisualBezier;
import com.zhivaevartemsaveg.visual.VisualLine;

import javax.swing.*;
import java.util.Arrays;
import java.util.List;

public class Main extends JFrame {
    private static final IDrawable composite =
            new DrawableComposite(
                    new VisualBezier(new Point(400, 300), new Point(80, 50), new Point(300, 450), new Point(100, 250)),
                    new VisualLine(new Point(100, 100), new Point(450, 250)),
                    new VisualLine(new Point(160, 70), new Point(520, 150)),
                    new VisualBezier(new Point(80, 150), new Point(500, 250), new Point(100, 450), new Point(300, 550)),
                    new DrawableComposite(
                            new VisualLine(new Point(100, 100), new Point(450, 250)),
                            new VisualLine(new Point(160, 70), new Point(520, 150)),
                            new VisualBezier(new Point(80, 150), new Point(500, 250), new Point(100, 450), new Point(300, 550)),
                            new DrawableComposite(
                                    new VisualLine(new Point(160, 70), new Point(520, 150)),
                                    new VisualBezier(new Point(80, 150), new Point(500, 250), new Point(100, 450), new Point(300, 550)),
                                    new DrawableComposite(
                                            new VisualBezier(new Point(80, 150), new Point(500, 250), new Point(100, 450), new Point(300, 550))
//                                            new DrawableComposite()
                                    )
                            )
                    )
            );

    private static final List<IDrawable> drawables = Arrays.asList(
            new VisualBezier(new Point(400, 300), new Point(80, 50), new Point(300, 450), new Point(100, 250)),
            new VisualLine(new Point(100, 100), new Point(450, 250)),
            new VisualLine(new Point(160, 70), new Point(520, 150)),
            new VisualBezier(new Point(80, 150), new Point(500, 250), new Point(100, 450), new Point(300, 550))
//            new VisualLine(new Point(500, 500), new Point(600, 600)),
//            new VisualLine(new Point(500, 500), new Point(400, 600)),
//            new VisualLine(new Point(500, 500), new Point(400, 400)),
//            new VisualLine(new Point(500, 500), new Point(600, 400))
    );
    private static int drawablesTop = 0;

    public static void main(String[] args) {
        int drawablesCount = 0;
        for (IDrawable drawable : composite) {
            drawablesCount++;
        }
        System.out.println("drawablesCount = " + drawablesCount);
        SwingUserInterface swing = new SwingUserInterface("Swing");
        swing.onGenerate((e) -> {
            if (drawablesTop >= drawables.size()) {
                drawablesTop = 0;
                swing.clear();
                return;
            }
            swing.draw(drawables.get(drawablesTop++));
        });
        swing.start(1280, 720);
    }
}
