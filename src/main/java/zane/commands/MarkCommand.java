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
    private int index;
    
    /**
     * Constructor for the MarkCommand class.
     * @param index The index of the task to mark.
     */
    public MarkCommand(int index) {
        this.index = index;
    }
    
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws ZaneException {
        Task task = tasks.getTask(index);
        task.setDone();
        storage.save(tasks);
        ui.printMessage("Nice! I've marked this task as done:\n  " + task.toString());
    }
}
