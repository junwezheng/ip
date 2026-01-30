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
    private int index;
    
    /**
     * Constructor for the DeleteCommand class.
     * @param index The index of the task to delete.
     */
    public DeleteCommand(int index) {
        this.index = index;
    }
    
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws ZaneException {
        Task task = tasks.getTask(index);
        tasks.removeTask(index);
        storage.save(tasks);
        ui.printRemoveTask(task, tasks.size());
    }
}
