package zane.task;

/**
 * Represents an Event task.
 * An Event task is a task that has a start and end time.
 * Provides methods to get the start and end time of the event.
 */
public class Event extends Task {
    protected String from;
    protected String to;

    /**
     * Constructor for the Event class.
     * @param description The description of the Event task.
     * @param from The start time of the Event task.
     * @param to The end time of the Event task.
     */
    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    @Override
    public String toString() {
        return "[E]" + "[" + getStatusIcon() + "] " + description + " (from: " + from + " to: " + to + ")";
    }

    public String getFrom() {
        return from;
    }

    public String getTo() {
        return to;
    }
}
