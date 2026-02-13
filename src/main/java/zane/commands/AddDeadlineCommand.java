package zane.commands;

import java.time.LocalDateTime;

import zane.task.Deadline;
import zane.task.TaskList;
import zane.ui.Storage;
import zane.ui.Ui;
import zane.ui.ZaneException;

/**
 * Represents an add deadline command.
 * An add deadline command is a command that adds a deadline task to the task list.
 * Provides methods to execute the add deadline command.
 */
public class AddDeadlineCommand extends Command {
    private String description;
    private String by;
    private int priority;

    /**
     * Constructor for the AddDeadlineCommand class.
     * @param description The description of the deadline task to add.
     * @param by The due date of the deadline task.
     * @param priority The priority of the deadline task to add.
     */
    public AddDeadlineCommand(String description, String by, int priority) {
        this.description = description;
        this.by = by;
        this.priority = priority;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws ZaneException {
        if (description == null || description.trim().isEmpty()) {
            throw new ZaneException("The description of a deadline cannot be empty.");
        }

        LocalDateTime date = Deadline.parseDate(by);
        Deadline deadline = new Deadline(description.trim(), date, priority);
        tasks.addTask(deadline);
        storage.save(tasks);
        return ui.getAddedTaskMessage(deadline, tasks.size());
    }
}
