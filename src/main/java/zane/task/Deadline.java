package zane.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a Deadline task.
 * A Deadline task is a task that has a due date.
 * Provides methods to get the due date of the deadline.
 */
public class Deadline extends Task {
    protected LocalDateTime by;
    private static final DateTimeFormatter INPUT_FORMAT = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
    private static final DateTimeFormatter OUTPUT_FORMAT = DateTimeFormatter.ofPattern("MMM dd yyyy, h:mma");
    private static final DateTimeFormatter SAVE_FORMAT = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");

    /**
     * Constructor for the Deadline class.
     * @param description The description of the Deadline task.
     * @param by The due date of the Deadline task.
     */
    public Deadline(String description, LocalDateTime by) {
        super(description);
        this.by = by;
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
        return "[D]" + "[" + getStatusIcon() + "] " + description + " (by: " + by.format(OUTPUT_FORMAT) + ")";
    }

    public String getByForSave() {
        return by.format(SAVE_FORMAT);
    }
}
