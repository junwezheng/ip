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
    protected boolean done;

    /**
     * Constructor for the Task class.
     * Initialises the Task with the description and sets the task as not done.
     */
    public Task(String description) {
        this.description = description;
        this.done = false;
    }

    public String getStatusIcon() {
        return (done ? "X" : " ");
    }

    public void setDone() {
        done = true;
    }

    public void unsetDone() {
        done = false;
    }

    public boolean isDone() {
        return done;
    }

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
