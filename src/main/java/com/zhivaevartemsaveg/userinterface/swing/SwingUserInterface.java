package com.zhivaevartemsaveg.userinterface.swing;

import com.zhivaevartemsaveg.Ref;
import com.zhivaevartemsaveg.geometry.IPoint;
import com.zhivaevartemsaveg.geometry.Point;
import com.zhivaevartemsaveg.userinterface.command.CommandManager;
import com.zhivaevartemsaveg.userinterface.command.InitCommand;
import com.zhivaevartemsaveg.userinterface.command.MoveCommand;
import com.zhivaevartemsaveg.visual.IDrawableArea;
import com.zhivaevartemsaveg.visual.context.DrawSchemeBackground;
import com.zhivaevartemsaveg.visual.context.DrawSchemeComposer;
import com.zhivaevartemsaveg.visual.context.IDrawScheme;
import com.zhivaevartemsaveg.visual.context.SvgCanvas;
import com.zhivaevartemsaveg.visual.context.swing.SwingCanvas;
import com.zhivaevartemsaveg.visual.decorator.VisualMoveAreaDecorator;
import com.zhivaevartemsaveg.visual.observer.IObserver;
import com.zhivaevartemsaveg.visual.observer.ISubject;
import com.zhivaevartemsaveg.visual.observer.MouseEvent;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.LayoutManager;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JFrame;

public class SwingUserInterface extends JFrame implements ISubject<MouseEvent> {
    public SwingUserInterface(String name) {
        super(name);
    }

    public void start(int w, int h) {
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
                if (draggableObject.value != null) {
                    new MoveCommand(draggableObject.value, new Point(e.getPoint())).execute();
                }
                draggableObject.value = null;
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

        drawScheme = new DrawSchemeBackground(canvas);

        JButton generate = new JButton("Generate");
        JButton undoButton = new JButton("Undo");

        GridBagConstraints c = new GridBagConstraints();
        c.gridy = 0;
        c.gridx = 0;
        c.fill = GridBagConstraints.BOTH;
        c.weightx = 1;
        c.weighty = .5;
        this.add(canvas, c);

        c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 1;
        this.add(undoButton, c);

        c.gridy = 2;
        c.gridx = 0;
        c.gridwidth = 2;
        this.add(generate, c);

        new InitCommand(this).execute();

        generate.addActionListener(e -> {

        });
        undoButton.addActionListener((event) -> {
            CommandManager.getInstance().undo();
            redraw();
        });
    }

    private IDrawScheme drawScheme;

    private final List<IDrawableArea> drawables = new ArrayList<>();

    private Ref<VisualMoveAreaDecorator> draggableObject = new Ref<>(null);
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
                if (event.isPressed() && !isDraggingRef.value && draggableObject.value == null) {
                    isDraggingRef.value = true;
                    draggableObject.value = v;
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
                draggableObject.value = null;
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
        //            double len = new SegmentReducer().reduceSegments(c, new GetLengthStrategy(1));
        //            double midT = new SegmentReducer().reduceSegments(c, new GetParameterStrategy(len / 2));
        //            drawScheme.drawFirstPoint(c.getPoint(midT));
        for (IDrawableArea c : drawables) {
            c.draw(drawScheme);
        }
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
