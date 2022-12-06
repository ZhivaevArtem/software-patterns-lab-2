package com.zhivaevartemsaveg.userinterface.command;

public abstract class ACommand implements ICommand {
    @Override
    public final void execute() {
        CommandManager.getInstance().registry(this.cloneCommand());
        doExecute();
    }

    protected abstract void doExecute();
}
