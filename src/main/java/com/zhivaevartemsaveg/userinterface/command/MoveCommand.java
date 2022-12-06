package com.zhivaevartemsaveg.userinterface.command;

import com.zhivaevartemsaveg.geometry.IPoint;
import com.zhivaevartemsaveg.visual.decorator.VisualMoveAreaDecorator;
import com.zhivaevartemsaveg.visual.observer.VisualMoveAreaDecoratorObserver;

import java.util.List;

public class MoveCommand extends ACommand {
//    private VisualMoveAreaDecorator visual;
//    private IPoint movement;
//
//    public MoveCommand(VisualMoveAreaDecorator visual, IPoint movement) {
//        this.visual = visual;
//        this.movement = movement;
//    }

    private final List<VisualMoveAreaDecoratorObserver> drawables;
    private final int i;
    private final IPoint movement;

    public MoveCommand(List<VisualMoveAreaDecoratorObserver> drawables, int i, IPoint movement) {
        this.drawables = drawables;
        this.i = i;
        this.movement = movement;
    }

//    @Override
//    protected void doExecute() {
//        visual.setMoveBy(movement);
//    }

//    @Override
//    public ICommand cloneCommand() {
//        return new MoveCommand(visual, movement);
//    }

    @Override
    protected void doExecute() {
        drawables.get(i).getShape().setMoveBy(movement);
    }

    @Override
    public ICommand cloneCommand() {
        return new MoveCommand(drawables, i, movement);
    }
}
