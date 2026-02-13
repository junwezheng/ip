package zane.commands;

import zane.task.Event;
import zane.task.TaskList;
import zane.ui.Storage;
import zane.ui.Ui;
import zane.ui.ZaneException;

/**
 * Represents an add event command.
 * An add event command is a command that adds an event task to the task list.
 * Provides methods to execute the add event command.
 */
public class AddEventCommand extends Command {
    private String description;
    private String from;
    private String to;
    private int priority;

    /**
     * Constructor for the AddEventCommand class.
     * @param description The description of the event task to add.
     * @param from The start time of the event task.
     * @param to The end time of the event task.
     * @param priority The priority of the event task to add.
     */
    public AddEventCommand(String description, String from, String to, int priority) {
        this.description = description;
        this.from = from;
        this.to = to;
        this.priority = priority;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws ZaneException {
        if (description == null || description.trim().isEmpty()) {
            throw new ZaneException("The description of an event cannot be empty.");
        }
        
        Event event = new Event(description.trim(), from, to, priority);
        tasks.addTask(event);
        storage.save(tasks);
        return ui.getAddedTaskMessage(event, tasks.size());
    }
}
