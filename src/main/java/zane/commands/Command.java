package zane.commands;

import zane.task.TaskList;
import zane.ui.Storage;
import zane.ui.Ui;
import zane.ui.ZaneException;

/**
 * Represents a command.
 * A Command object is a command that can be executed by the Zane application.
 * Provides methods to execute the command and check if the command is an exit command.
 */
public abstract class Command {
    /**
     * Executes the command.
     * @param tasks The task list to execute the command on.
     * @param ui The UI to display the output.
     * @param storage The storage to save the tasks to.
     * @throws ZaneException If an error occurs while executing the command.
     */
    
    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws ZaneException;
    
    public boolean isExit() {
        return false;
    }
}
