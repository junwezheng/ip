package zane.commands;

import zane.task.TaskList;
import zane.ui.Storage;
import zane.ui.Ui;

public class ListCommand extends Command {
    
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.printList(tasks);
    }
}
