package com.zhivaevartemsaveg.userinterface.swing;

import com.zhivaevartemsaveg.geometry.Point;
import com.zhivaevartemsaveg.geometry.strategy.GetLengthStrategy;
import com.zhivaevartemsaveg.geometry.strategy.GetParameterStrategy;
import com.zhivaevartemsaveg.visual.VisualBezier;
import com.zhivaevartemsaveg.visual.VisualCurve;
import com.zhivaevartemsaveg.visual.VisualLine;
import com.zhivaevartemsaveg.visual.context.CanvasComposer;
import com.zhivaevartemsaveg.visual.context.DrawSchemeBlack;
import com.zhivaevartemsaveg.visual.context.DrawSchemeComposer;
import com.zhivaevartemsaveg.visual.context.DrawSchemeGreen;
import com.zhivaevartemsaveg.visual.context.IDrawScheme;
import com.zhivaevartemsaveg.visual.context.SvgCanvas;
import com.zhivaevartemsaveg.visual.context.swing.SwingCanvas;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.LayoutManager;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JFrame;

public class Main {
    private static final List<VisualCurve> drawables = Arrays.asList(
            new VisualBezier(new Point(80, 50), new Point(300, 250), new Point(100, 250), new Point(300, 450)),
            new VisualLine(new Point(100, 100), new Point(450, 250)),
            new VisualLine(new Point(160, 70), new Point(520, 150)),
            new VisualBezier(new Point(80, 150), new Point(500, 250), new Point(100, 450), new Point(300, 550))
    );
    private static int drawablesTop = 0;

    private static void exportSvg(SvgCanvas svg, String name) {
        try (FileWriter writer = new FileWriter(name + ".svg")) {
            writer.write(svg.getSvg());
            writer.flush();
        } catch (IOException ex) {
            System.out.println("ex = " + ex);
        }
    }

    public static int HEIGHT = 720, WIDTH = 1280;

    public static void main(String[] args) {
        SwingCanvas left = new SwingCanvas(),
                right = new SwingCanvas();

        JFrame frame = new JFrame("Swing");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(WIDTH, HEIGHT);

        LayoutManager layout = new GridBagLayout();
        frame.setLayout(layout);

        frame.setVisible(true);

        SvgCanvas leftSvg = new SvgCanvas(WIDTH, HEIGHT),
                rightSvg = new SvgCanvas(WIDTH, HEIGHT);

        IDrawScheme leftScheme = new DrawSchemeGreen(new CanvasComposer(
                left,
                leftSvg
        ));
        IDrawScheme rightScheme = new DrawSchemeBlack(new CanvasComposer(
                right,
                rightSvg
        ));

        IDrawScheme composedScheme = new DrawSchemeComposer(
                leftScheme,
                rightScheme
        );

        JButton leftExport = new JButton("Export");
        JButton rightExport = new JButton("Export");
        JButton generate = new JButton("Generate");

        GridBagConstraints c = new GridBagConstraints();
        c.gridy = 0;
        c.gridx = 0;
        c.fill = GridBagConstraints.BOTH;
        c.weightx = .5;
        c.weighty = .5;
        frame.add(left, c);
        c.gridx = 1;
        frame.add(right, c);

        c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 1;
        frame.add(leftExport, c);
        c.gridx = 1;
        frame.add(rightExport, c);

        c.gridy = 2;
        c.gridx = 0;
        c.gridwidth = 2;
        frame.add(generate, c);

        frame.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                leftSvg.setSize(left.getWidth(), left.getHeight());
                rightSvg.setSize(right.getWidth(), right.getHeight());
            }
        });

        generate.addActionListener((e) -> {
            if (drawablesTop >= drawables.size()) {
                drawablesTop = 0;
                composedScheme.clear();
            } else {
                VisualCurve visualCurve = drawables.get(drawablesTop++);
                visualCurve.draw(composedScheme);
                double length = visualCurve.reduceSegments(new GetLengthStrategy(1));
                double midT = visualCurve.reduceSegments(new GetParameterStrategy(length / 2));
                System.out.println("midT = " + midT);
                composedScheme.drawFirstPoint(visualCurve.getPoint(midT));
            }
        });

        leftExport.addActionListener((e) -> {
            exportSvg(leftSvg, "leftCanvas");
        });

        rightExport.addActionListener((e) -> {
            exportSvg(rightSvg, "rightCanvas");
        });

        Graphics g = frame.getGraphics();
        frame.paint(g);
    }
}
