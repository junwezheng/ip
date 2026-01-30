package zane.commands;

import zane.task.TaskList;
import zane.ui.Storage;
import zane.ui.Ui;

/**
 * Represents a list command.
 * A list command is a command that lists all the tasks in the task list.
 * Provides methods to execute the list command.
 */
public class ListCommand extends Command {
    
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.printList(tasks);
    }
}
