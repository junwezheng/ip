package zane.task;

/**
 * Represents a Todo task.
 * A Todo task is a task that needs to be done.
 */
public class Todo extends Task {
    private int priority;

    /**
     * Constructor for the Todo class.
     * @param description The description of the Todo task.
     * @param priority The priority of the Todo task.
     */
    public Todo(String description, int priority) {
        super(description);
        this.priority = priority;
    }

    @Override
    public String toString() {
        return "[P" + priority + "]" + "[T]" + "[" + getStatusIcon() + "] " + description;
    }

    @Override
    public String toFileString() {
        return "P" + priority + "|T" + FILE_DELIMITER + (isDone() ? "1" : "0") + FILE_DELIMITER + description;
    }
}
