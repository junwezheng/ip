package zane.commands;

import zane.task.Task;
import zane.task.TaskList;
import zane.ui.Storage;
import zane.ui.Ui;
import zane.ui.ZaneException;

public class UnmarkCommand extends Command {
    private int index;
    
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
