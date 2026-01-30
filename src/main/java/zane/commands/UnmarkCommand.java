package zane.commands;

import zane.task.Task;
import zane.task.TaskList;
import zane.ui.Storage;
import zane.ui.Ui;
import zane.ui.ZaneException;

/**
 * Represents an unmark command.
 * An unmark command is a command that unmarks a task as done.
 * Provides methods to execute the unmark command.
 */
public class UnmarkCommand extends Command {
    private int index;
    
    /**
     * Constructor for the UnmarkCommand class.
     * @param index The index of the task to unmark.
     */
    public UnmarkCommand(int index) {
        this.index = index;
    }
    
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws ZaneException {
        Task task = tasks.getTask(index);
        task.unsetDone();
        storage.save(tasks);
        ui.printMessage("OK, I've marked this task as not done yet:\n  " + task.toString());
    }
}
