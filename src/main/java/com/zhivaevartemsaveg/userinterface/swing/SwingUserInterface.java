package com.zhivaevartemsaveg.userinterface.swing;

import com.sun.org.apache.regexp.internal.RE;
import com.zhivaevartemsaveg.Ref;
import com.zhivaevartemsaveg.geometry.IPoint;
import com.zhivaevartemsaveg.geometry.Point;
import com.zhivaevartemsaveg.visual.IDrawable;
import com.zhivaevartemsaveg.visual.IDrawableArea;
import com.zhivaevartemsaveg.visual.context.*;
import com.zhivaevartemsaveg.visual.context.swing.SwingCanvas;
import com.zhivaevartemsaveg.visual.decorator.VisualMoveAreaDecorator;
import com.zhivaevartemsaveg.visual.observer.IObserver;
import com.zhivaevartemsaveg.visual.observer.ISubject;
import com.zhivaevartemsaveg.visual.observer.MouseEvent;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SwingUserInterface extends JFrame implements ISubject<MouseEvent> {
    private ActionListener generateListener;

    public void onGenerate(ActionListener generateListener) {
        this.generateListener = generateListener;
    }

    public SwingUserInterface(String name) {
        super(name);
    }

    public void start(int w, int h) {
//        SwingCanvas left = new SwingCanvas(),
//                right = new SwingCanvas();
        attach(event -> {
            System.out.println("event = " + event);
        });
        SwingCanvas canvas = new SwingCanvas();
        canvas.setBackground(Color.BLACK);

        canvas.addMouseMotionListener(new MouseMotionListener() {
            @Override
            public void mouseDragged(java.awt.event.MouseEvent e) {
                mouseButtonPressed = true;
                lastMousePosition = mousePosition;
                mousePosition = new Point(e.getX(), e.getY());
                notifyObservers();
            }

            @Override
            public void mouseMoved(java.awt.event.MouseEvent e) {
                mouseButtonPressed = false;
                lastMousePosition = mousePosition;
                mousePosition = new Point(e.getX(), e.getY());
                notifyObservers();
            }
        });
        canvas.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent e) { }

            @Override
            public void mousePressed(java.awt.event.MouseEvent e) {
                mouseButtonPressed = true;
                notifyObservers();
            }

            @Override
            public void mouseReleased(java.awt.event.MouseEvent e) {
                mouseButtonPressed = false;
                notifyObservers();
            }

            @Override
            public void mouseEntered(java.awt.event.MouseEvent e) { }

            @Override
            public void mouseExited(java.awt.event.MouseEvent e) { }
        });

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(w, h);

        LayoutManager layout = new GridBagLayout();
        this.setLayout(layout);

        this.setVisible(true);

        SvgCanvas leftSvg = new SvgCanvas(w, h),
                rightSvg = new SvgCanvas(w, h);

//        IDrawScheme leftScheme = new DrawSchemeGreen(new CanvasComposer(
//                left,
//                leftSvg
//        ));
//        IDrawScheme rightScheme = new DrawSchemeBlack(new CanvasComposer(
//                right,
//                rightSvg
//        ));
        IDrawScheme scheme = new DrawSchemeBackground(canvas);

        IDrawScheme composedScheme = new DrawSchemeComposer(
//                leftScheme,
//                rightScheme
                scheme
        );
        drawScheme = composedScheme;

//        JButton leftExport = new JButton("Export");
//        JButton rightExport = new JButton("Export");
        JButton generate = new JButton("Generate");
        JButton undoButton = new JButton("Undo");

        GridBagConstraints c = new GridBagConstraints();
        c.gridy = 0;
        c.gridx = 0;
        c.fill = GridBagConstraints.BOTH;
        c.weightx = 1;
        c.weighty = .5;
//        this.add(left, c);
//        c.gridx = 1;
//        this.add(right, c);
        this.add(canvas, c);

        c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 1;
        this.add(undoButton, c);
//        this.add(leftExport, c);
//        c.gridx = 1;
//        this.add(rightExport, c);

        c.gridy = 2;
        c.gridx = 0;
        c.gridwidth = 2;
        this.add(generate, c);

//        this.addComponentListener(new ComponentAdapter() {
//            @Override
//            public void componentResized(ComponentEvent e) {
//                leftSvg.setSize(left.getWidth(), left.getHeight());
//                rightSvg.setSize(right.getWidth(), right.getHeight());
//            }
//        });

        generate.addActionListener(generateListener);
        undoButton.addActionListener((event) -> {

        });

//        leftExport.addActionListener((e) -> {
//            exportSvg(leftSvg, "leftCanvas");
//        });
//
//        rightExport.addActionListener((e) -> {
//            exportSvg(rightSvg, "rightCanvas");
//        });

        undoButton.addActionListener((e) -> {
            // TODO: undo
        });

//        new Thread(() -> {
//            try {
//                Thread.sleep(2000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            Graphics g = getGraphics();
//            paint(g);
//            repaint();
//            update(g);
//            System.out.println("g = " + g);
//        }).start();
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

    private final List<IDrawableArea> drawables = new ArrayList<>();

    private Ref<Boolean> isAnyDragging = new Ref<>(false);
    public void draw(IDrawableArea drawable) {
        VisualMoveAreaDecorator v = new VisualMoveAreaDecorator(drawable);
        Ref<Color> colorRef = new Ref<>(Color.WHITE);
        Ref<Boolean> isDraggingRef = new Ref<>(false);
        v.setColor(colorRef.value);
        drawables.add(v);

        attach(event -> {
            boolean changed = false;
            if (v.contains(event.getPoint())) {
                if (colorRef.value != Color.GREEN) {
                    colorRef.value = Color.GREEN;
                    v.setColor(colorRef.value);
                    changed = true;
                }
                if (event.isPressed() && !isDraggingRef.value && !isAnyDragging.value) {
                    isDraggingRef.value = true;
                    isAnyDragging.value = true;
                }
            } else {
                if (colorRef.value != Color.WHITE) {
                    colorRef.value = Color.WHITE;
                    v.setColor(colorRef.value);
                    changed = true;
                }
            }
            if (isDraggingRef.value && event.isPressed()) {
                IPoint movement = event.getMovement();
                v.move(movement);
                changed = true;
            }
            if (!event.isPressed()) {
                isDraggingRef.value = false;
                isAnyDragging.value = false;
            }
            if (changed) {
                redraw();
            }
        });
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

    private IPoint lastMousePosition;
    private IPoint mousePosition;
    private boolean mouseButtonPressed;
    private final List<IObserver<MouseEvent>> observers = new ArrayList<>();

    @Override
    public void attach(IObserver<MouseEvent> observer) {
        observers.add(observer);
    }

    @Override
    public void dettach(IObserver<MouseEvent> observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers() {
        observers.forEach(observer -> observer.update(new MouseEvent(mousePosition, lastMousePosition, mouseButtonPressed)));
    }
}
