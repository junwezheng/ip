package zane.commands;

import zane.task.TaskList;
import zane.task.Todo;
import zane.ui.Storage;
import zane.ui.Ui;
import zane.ui.ZaneException;

/**
 * Represents an add todo command.
 * An add todo command is a command that adds a todo task to the task list.
 * Provides methods to execute the add todo command.
 */
public class AddTodoCommand extends Command {
    private String description;
    private int priority;

    /**
     * Constructor for the AddTodoCommand class.
     * @param description The description of the todo task to add.
     * @param priority The priority of the todo task to add.
     */
    public AddTodoCommand(String description, int priority) {
        this.description = description;
        this.priority = priority;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws ZaneException {
        if (description == null || description.trim().isEmpty()) {
            throw new ZaneException("The description of a todo cannot be empty.");
        }
        
        Todo todo = new Todo(description.trim(), priority);
        tasks.addTask(todo);
        storage.save(tasks);
        return ui.getAddedTaskMessage(todo, tasks.size());
    }
}
