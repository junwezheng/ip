package zane.ui;

import zane.task.Task;
import zane.task.TaskList;

/**
 * Handles the UI for the Zane application.
 * Formats and returns response messages to the user.
 */
public class Ui {
    public static final String LINE = "____________________________________________________________";

    /**
     * Returns the goodbye message.
     *
     * @return The goodbye message string.
     */
    public String getGoodbyeMessage() {
        return "Bye. Hope to see you again soon!";
    }

    /**
     * Returns the loading error message.
     *
     * @return The loading error message string.
     */
    public String getLoadingErrorMessage() {
        return "Error loading tasks from file. Starting with empty list.";
    }

    /**
     * Returns the message for a successfully added task.
     *
     * @param task The task that was added.
     * @param size The total number of tasks after adding.
     * @return The formatted message string.
     */
    public String getAddedTaskMessage(Task task, int size) {
        return "Got it. I've added this task:\n  " + task.toString()
                + "\nNow you have " + size + " tasks in the list.";
    }

    /**
     * Returns the message for a successfully removed task.
     *
     * @param task The task that was removed.
     * @param size The total number of tasks after removal.
     * @return The formatted message string.
     */
    public String getRemovedTaskMessage(Task task, int size) {
        return "Noted. I've removed this task:\n  " + task.toString()
                + "\nNow you have " + size + " tasks in the list.";
    }

    /**
     * Returns the formatted task list.
     *
     * @param tasks The task list to format.
     * @return The formatted task list string.
     */
    public String getTaskListMessage(TaskList tasks) {
        StringBuilder sb = new StringBuilder("Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            sb.append("\n").append(i + 1).append(". ").append(tasks.getTask(i).toString());
        }
        return sb.toString();
    }
}
