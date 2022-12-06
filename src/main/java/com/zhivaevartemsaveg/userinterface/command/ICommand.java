package com.zhivaevartemsaveg.userinterface.command;

public interface ICommand {
    void execute();

    ICommand cloneCommand();
}
