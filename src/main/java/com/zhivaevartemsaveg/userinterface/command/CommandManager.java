package com.zhivaevartemsaveg.userinterface.command;

import java.util.ArrayList;
import java.util.List;

public class CommandManager {
    private static CommandManager instance = null;

    private final List<ICommand> commands = new ArrayList<>();

    public static CommandManager getInstance() {
        if (instance == null) {
            instance = new CommandManager();
        }
        return instance;
    }

    private CommandManager() {}

    private boolean lock = false;
    public void registry(ICommand command) {
        if (!lock) {
            commands.add(command);
        }
    }

    public void undo() {
        if (!lock && commands.size() > 1) {
            lock = true;
            commands.remove(commands.size() - 1);
            for (ICommand command : commands) {
                command.execute();
            }
            lock = false;
        }
    }
}
