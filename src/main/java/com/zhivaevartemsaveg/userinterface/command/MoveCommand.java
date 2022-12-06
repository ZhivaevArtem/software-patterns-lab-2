package com.zhivaevartemsaveg.userinterface.command;

import com.zhivaevartemsaveg.geometry.IPoint;
import com.zhivaevartemsaveg.visual.decorator.VisualMoveAreaDecorator;

public class MoveCommand extends ACommand {
    private VisualMoveAreaDecorator visual;
    private IPoint center;

    public MoveCommand(VisualMoveAreaDecorator visual, IPoint center) {
        this.visual = visual;
        this.center = center;
    }

    @Override
    protected void doExecute() {
        visual.moveTo(center);
    }

    @Override
    public ICommand cloneCommand() {
        return new MoveCommand(visual, center);
    }
}
