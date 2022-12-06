package com.zhivaevartemsaveg.userinterface.command;

import com.zhivaevartemsaveg.geometry.Point;
import com.zhivaevartemsaveg.userinterface.swing.SwingUserInterface;
import com.zhivaevartemsaveg.visual.IDrawableArea;
import com.zhivaevartemsaveg.visual.VisualDisk;
import java.util.Arrays;
import java.util.List;

public class InitCommand extends ACommand {
    private SwingUserInterface userInterface;

    public InitCommand(SwingUserInterface userInterface) {
        this.userInterface = userInterface;
    }

    @Override
    public ICommand cloneCommand() {
        return new InitCommand(userInterface);
    }

    @Override
    protected void doExecute() {
        userInterface.clear();
        List<IDrawableArea> drawables = Arrays.asList(
                new VisualDisk(new Point(300, 300), 80),
                new VisualDisk(new Point(300, 500), 80),
                new VisualDisk(new Point(500, 300), 80),
                new VisualDisk(new Point(500, 500), 80)
        );
        drawables.forEach(userInterface::draw);
    }
}
