package zane.task;

/**
 * Represents a Todo task.
 * A Todo task is a task that needs to be done.
 */
public class Todo extends Task {
    /**
     * Constructor for the Todo class.
     * @param description The description of the Todo task.
     */
    public Todo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "[T]" + "[" + getStatusIcon() + "] " + description;
    }
}
