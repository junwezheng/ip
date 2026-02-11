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
    private int targetIndex;

    /**
     * Constructor for the UnmarkCommand class.
     * @param targetIndex The 0-based index of the task to unmark in the task list.
     */
    public UnmarkCommand(int targetIndex) {
        this.targetIndex = targetIndex;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws ZaneException {
        Task task = tasks.getTask(targetIndex);
        task.unsetDone();
        storage.save(tasks);
        return "OK, I've marked this task as not done yet:\n  " + task.toString();
    }
}
