package com.zhivaevartemsaveg.userinterface.command;

import com.zhivaevartemsaveg.geometry.IPoint;
import com.zhivaevartemsaveg.visual.decorator.VisualMoveAreaDecorator;

public class MoveCommand extends ACommand {
    private VisualMoveAreaDecorator visual;
    private IPoint movement;

    public MoveCommand(VisualMoveAreaDecorator visual, IPoint movement) {
        this.visual = visual;
        this.movement = movement;
    }

    @Override
    protected void doExecute() {
        visual.setMoveBy(movement);
    }

    @Override
    public ICommand cloneCommand() {
        return new MoveCommand(visual, movement);
    }
}
