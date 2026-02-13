package zane.task;

/**
 * Represents an Event task.
 * An Event task is a task that has a start and end time.
 * Provides methods to get the start and end time of the event.
 */
public class Event extends Task {
    protected String startTime;
    protected String endTime;
    private int priority;

    /**
     * Constructor for the Event class.
     * @param description The description of the Event task.
     * @param startTime The start time of the Event task.
     * @param endTime The end time of the Event task.
     * @param priority The priority of the Event task.
     */
    public Event(String description, String startTime, String endTime, int priority) {
        super(description);
        this.startTime = startTime;
        this.endTime = endTime;
        this.priority = priority;
    }

    @Override
    public String toString() {
        return "[P" + priority + "]" + "[E]" + "[" + getStatusIcon() + "] " + description + " (from: " + startTime + " to: " + endTime + ")";
    }

    /**
     * Returns the start time of the event.
     * @return The start time string.
     */
    public String getStartTime() {
        return startTime;
    }

    /**
     * Returns the end time of the event.
     * @return The end time string.
     */
    public String getEndTime() {
        return endTime;
    }

    @Override
    public String toFileString() {
        return "P" + priority + "|E" + FILE_DELIMITER + (isDone() ? "1" : "0") + FILE_DELIMITER + description
                + FILE_DELIMITER + startTime + "-" + endTime;
    }
}
