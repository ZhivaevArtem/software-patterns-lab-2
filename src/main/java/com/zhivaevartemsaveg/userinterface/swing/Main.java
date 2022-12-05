package com.zhivaevartemsaveg.userinterface.swing;

import com.zhivaevartemsaveg.geometry.IPoint;
import com.zhivaevartemsaveg.geometry.Point;
import com.zhivaevartemsaveg.visual.*;

import javax.swing.*;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class Main extends JFrame {
    private static final IDrawable composite =
            new DrawableComposite(
                    new VisualBezier(new Point(400, 300), new Point(80, 50), new Point(300, 450), new Point(100, 250)),
                    new VisualLine(new Point(100, 100), new Point(450, 250)),
                    new VisualLine(new Point(160, 70), new Point(520, 150)),
                    new VisualBezier(new Point(80, 150), new Point(500, 250), new Point(100, 450), new Point(300, 550)),
                    new DrawableComposite(
                            new VisualLine(new Point(100, 100), new Point(450, 250)),
                            new DrawableComposite(),
                            new VisualLine(new Point(160, 70), new Point(520, 150)),
                            new VisualBezier(new Point(80, 150), new Point(500, 250), new Point(100, 450), new Point(300, 550)),
                            new DrawableComposite(
                                    new VisualLine(new Point(160, 70), new Point(520, 150)),
                                    new VisualBezier(new Point(80, 150), new Point(500, 250), new Point(100, 450), new Point(300, 550)),
                                    new DrawableComposite(
                                            new VisualBezier(new Point(80, 150), new Point(500, 250), new Point(100, 450), new Point(300, 550)),
                                            new DrawableComposite(
                                                    new DrawableComposite(
                                                            new DrawableComposite(
                                                                    new DrawableComposite(
                                                                        new VisualBezier(new Point(80, 150), new Point(500, 250), new Point(100, 450), new Point(300, 550))
                                                                    )
                                                            )
                                                    )
                                            )
                                    )
                            )
                    )
            );

    private static final List<IDrawable> drawables = Arrays.asList(
            new VisualDisk(new Point(300, 300), 80),
            new VisualDisk(new Point(300, 500), 80),
            new VisualDisk(new Point(500, 300), 80),
            new VisualDisk(new Point(500, 500), 80)
//            new VisualBezier(new Point(400, 300), new Point(80, 50), new Point(300, 450), new Point(100, 250)),
//            new VisualLine(new Point(100, 100), new Point(450, 250)),
//            new VisualLine(new Point(160, 70), new Point(520, 150)),
//            new VisualBezier(new Point(80, 150), new Point(500, 250), new Point(100, 450), new Point(300, 550))
//            new VisualLine(new Point(500, 500), new Point(600, 600)),
//            new VisualLine(new Point(500, 500), new Point(400, 600)),
//            new VisualLine(new Point(500, 500), new Point(400, 400)),
//            new VisualLine(new Point(500, 500), new Point(600, 400))
    );
    private static int drawablesTop = 0;

    public static void main(String[] args) {
        AtomicInteger drawablesCount = new AtomicInteger();
        composite.iterate((drawable) -> {
            drawablesCount.getAndIncrement();
        });
        System.out.println("drawablesCount = " + drawablesCount);
        AtomicInteger drawablesCount1 = new AtomicInteger();
        composite.forEach((drawable) -> {
            drawablesCount1.getAndIncrement();
        });
        System.out.println("drawablesCount1 = " + drawablesCount1);
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
