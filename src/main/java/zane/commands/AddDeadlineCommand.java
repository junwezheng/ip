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
    
    /**
     * Constructor for the AddDeadlineCommand class.
     * @param description The description of the deadline task to add.
     * @param by The due date of the deadline task.
     */
    public AddDeadlineCommand(String description, String by) {
        this.description = description;
        this.by = by;
    }
    
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws ZaneException {
        if (description == null || description.trim().isEmpty()) {
            throw new ZaneException("The description of a deadline cannot be empty.");
        }

        LocalDateTime date = Deadline.parseDate(by);
        Deadline deadline = new Deadline(description.trim(), date);
        tasks.addTask(deadline);
        storage.save(tasks);
        ui.printAddedTask(deadline, tasks.size());
    }
}
