package zane.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    protected LocalDateTime by;
    private static final DateTimeFormatter INPUT_FORMAT = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
    private static final DateTimeFormatter OUTPUT_FORMAT = DateTimeFormatter.ofPattern("MMM dd yyyy, h:mma");
    private static final DateTimeFormatter SAVE_FORMAT = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");

    public Deadline(String description, LocalDateTime by) {
        super(description);
        this.by = by;
    }

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
