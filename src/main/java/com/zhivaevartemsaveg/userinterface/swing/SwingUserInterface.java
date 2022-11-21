package com.zhivaevartemsaveg.userinterface.swing;

import com.zhivaevartemsaveg.geometry.Point;
import com.zhivaevartemsaveg.geometry.decorator.Fragment;
import com.zhivaevartemsaveg.geometry.decorator.MoveTo;
import com.zhivaevartemsaveg.geometry.strategy.GetLengthStrategy;
import com.zhivaevartemsaveg.geometry.strategy.GetParameterStrategy;
import com.zhivaevartemsaveg.geometry.strategy.SegmentReducer;
import com.zhivaevartemsaveg.visual.IDrawable;
import com.zhivaevartemsaveg.visual.VisualCurve;
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
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JFrame;

public class SwingUserInterface extends JFrame {
    private ActionListener generateListener;

    public void onGenerate(ActionListener generateListener) {
        this.generateListener = generateListener;
    }

    public SwingUserInterface(String name) {
        super(name);
    }

    public void start(int w, int h) {
        SwingCanvas left = new SwingCanvas(),
                right = new SwingCanvas();

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(w, h);

        LayoutManager layout = new GridBagLayout();
        this.setLayout(layout);

        this.setVisible(true);

        SvgCanvas leftSvg = new SvgCanvas(w, h),
                rightSvg = new SvgCanvas(w, h);

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
        drawScheme = composedScheme;

        JButton leftExport = new JButton("Export");
        JButton rightExport = new JButton("Export");
        JButton generate = new JButton("Generate");

        GridBagConstraints c = new GridBagConstraints();
        c.gridy = 0;
        c.gridx = 0;
        c.fill = GridBagConstraints.BOTH;
        c.weightx = .5;
        c.weighty = .5;
        this.add(left, c);
        c.gridx = 1;
        this.add(right, c);

        c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 1;
        this.add(leftExport, c);
        c.gridx = 1;
        this.add(rightExport, c);

        c.gridy = 2;
        c.gridx = 0;
        c.gridwidth = 2;
        this.add(generate, c);

        this.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                leftSvg.setSize(left.getWidth(), left.getHeight());
                rightSvg.setSize(right.getWidth(), right.getHeight());
            }
        });

        generate.addActionListener(generateListener);

        leftExport.addActionListener((e) -> {
            exportSvg(leftSvg, "leftCanvas");
        });

        rightExport.addActionListener((e) -> {
            exportSvg(rightSvg, "rightCanvas");
        });

        Graphics g = this.getGraphics();
        this.paint(g);
    }

    private void exportSvg(SvgCanvas svg, String name) {
        try (FileWriter writer = new FileWriter(name + ".svg")) {
            writer.write(svg.getSvg());
            writer.flush();
        } catch (IOException ex) {
            System.out.println("ex = " + ex);
        }
    }

    private IDrawScheme drawScheme;

    private final List<IDrawable> drawables = new ArrayList<>();

    public void draw(IDrawable drawable) {
        drawables.add(drawable);
        redraw();
    }

    public void clear() {
        drawables.clear();
        redraw();
    }

    public void redraw() {
        drawScheme.clear();
        drawables.forEach((c) -> {
            c.draw(drawScheme);
//            double len = new SegmentReducer().reduceSegments(c, new GetLengthStrategy(1));
//            double midT = new SegmentReducer().reduceSegments(c, new GetParameterStrategy(len / 2));
//            drawScheme.drawFirstPoint(c.getPoint(midT));
        });
    }
}
