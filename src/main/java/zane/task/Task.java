package zane.task;

/**
 * Represents a task.
 * A Task is a task that can be added to a TaskList.
 * Provides methods to get the status of the task, set the task as done, and unset the task as done.
 */
public abstract class Task {
    /** Delimiter used when saving tasks to the data file. */
    public static final String FILE_DELIMITER = " | ";

    protected String description;
    protected boolean isDone;

    /**
     * Constructor for the Task class.
     * Initialises the Task with the description and sets the task as not done.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    public void setIsDone() {
        isDone = true;
    }

    public void unsetDone() {
        isDone = false;
    }

    public boolean isDone() {
        return isDone;
    }

    /**
     * Returns the priority of this task.
     * @return The priority level (1 = highest, 3 = lowest).
     */
    public abstract int getPriority();

    /**
     * Returns the string representation of this task for saving to the data file.
     * @return The formatted string to be written to the file.
     */
    public abstract String toFileString();

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }
}
