package zane.commands;

import zane.task.TaskList;
import zane.ui.Storage;
import zane.ui.Ui;

/**
 * Represents an exit command.
 * An exit command is a command that exits the Zane application.
 * Provides methods to execute the exit command.
 */
public class ExitCommand extends Command {

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return ui.getGoodbyeMessage();
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
