package zane.commands;

import zane.task.Task;
import zane.task.TaskList;
import zane.ui.Storage;
import zane.ui.Ui;
import zane.ui.ZaneException;

/**
 * Represents a delete command.
 * A delete command is a command that deletes a task from the task list.
 * Provides methods to execute the delete command.
 */
public class DeleteCommand extends Command {
    private int targetIndex;

    /**
     * Constructor for the DeleteCommand class.
     * @param targetIndex The 0-based index of the task to delete in the task list.
     */
    public DeleteCommand(int targetIndex) {
        this.targetIndex = targetIndex;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws ZaneException {
        Task task = tasks.getTask(targetIndex);
        tasks.removeTask(targetIndex);
        storage.save(tasks);
        return ui.getRemovedTaskMessage(task, tasks.size());
    }
}
