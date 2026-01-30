package zane.commands;

import zane.task.TaskList;
import zane.ui.Storage;
import zane.ui.Ui;
import zane.ui.ZaneException;

public abstract class Command {
    
    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws ZaneException;
    
    public boolean isExit() {
        return false;
    }
}
