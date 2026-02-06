package zane.commands;

import zane.task.Task;
import zane.task.TaskList;
import zane.ui.Storage;
import zane.ui.Ui;
import zane.ui.ZaneException;

/**
 * Represents a find command.
 * A find command is a command that finds matching tasks in the task list.
 * Provides methods to execute the find command.
 */
public class FindCommand extends Command {
    private String keyword;

    /**
     * Constructor for the FindCommand class.
     * @param keyword The keyword to find in the task list.
     */
    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    /**
     * Executes the find command.
     * @param tasks The task list to execute the command on.
     * @param ui The UI to display the output.
     * @param storage The storage to save the tasks to.
     * @return The response message listing matching tasks.
     * @throws ZaneException If an error occurs while executing the command.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws ZaneException {
        StringBuilder sb = new StringBuilder("Here are the matching tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            Task task = tasks.getTask(i);
            if (task.getDescription().contains(keyword)) {
                sb.append(String.format("\n%d. %s", i + 1, task.toString()));
            }
        }
        return sb.toString();
    }
}
