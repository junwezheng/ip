package zane.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a Deadline task.
 * A Deadline task is a task that has a due date.
 * Provides methods to get the due date of the deadline.
 */
public class Deadline extends Task {
    /** The due date and time by which this deadline task must be completed. */
    protected LocalDateTime by;
    private int priority;
    private static final DateTimeFormatter INPUT_FORMAT = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
    private static final DateTimeFormatter OUTPUT_FORMAT = DateTimeFormatter.ofPattern("MMM dd yyyy, h:mma");
    private static final DateTimeFormatter SAVE_FORMAT = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");

    /**
     * Constructor for the Deadline class.
     * @param description The description of the Deadline task.
     * @param by The due date of the Deadline task.
     * @param priority The priority of the Deadline task.
     */
    public Deadline(String description, LocalDateTime by, int priority) {
        super(description);
        this.by = by;
        this.priority = priority;
    }

    /**
     * Parses a date string into a LocalDateTime object.
     * @param dateStr The date string to parse.
     * @return The LocalDateTime object.
     */
    public static LocalDateTime parseDate(String dateStr) {
        return LocalDateTime.parse(dateStr, INPUT_FORMAT);
    }

    @Override
    public String toString() {
        return "[P" + priority + "]" + "[D]" + "[" + getStatusIcon() + "] " + description + " (by: " + by.format(OUTPUT_FORMAT) + ")";
    }

    /**
     * Returns the due date formatted for saving to the data file.
     * @return The due date in the save format (d/M/yyyy HHmm).
     */
    public String getFormattedDateForSave() {
        return by.format(SAVE_FORMAT);
    }

    @Override
    public String toFileString() {
        return "P" + priority + "|D" + FILE_DELIMITER + (isDone() ? "1" : "0") + FILE_DELIMITER + description
                + FILE_DELIMITER + getFormattedDateForSave();
    }
}
