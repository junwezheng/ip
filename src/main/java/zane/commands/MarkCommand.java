package zane.commands;

import zane.task.Task;
import zane.task.TaskList;
import zane.ui.Storage;
import zane.ui.Ui;
import zane.ui.ZaneException;

/**
 * Represents a mark command.
 * A mark command is a command that marks a task as done.
 * Provides methods to execute the mark command.
 */
public class MarkCommand extends Command {
    private int targetIndex;

    /**
     * Constructor for the MarkCommand class.
     * @param targetIndex The 0-based index of the task to mark in the task list.
     */
    public MarkCommand(int targetIndex) {
        this.targetIndex = targetIndex;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws ZaneException {
        Task task = tasks.getTask(targetIndex);
        task.setIsDone();
        storage.save(tasks);
        return "Nice! I've marked this task as done:\n  " + task.toString();
    }
}
