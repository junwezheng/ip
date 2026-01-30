package zane.ui;

import zane.task.Task;
import zane.task.TaskList;

/**
 * Handles the UI for the Zane application.
 * Displays messages to the user and formats the output.
 */
public class Ui {
    public static final String LINE = "____________________________________________________________";

    /**
     * Constructor for the Ui class.
     * Displays the welcome message.
     */
    public Ui() {
        showWelcome();
    }

    public void showWelcome() {
        System.out.println(LINE);
        System.out.println("Hello! I'm Zane");
        System.out.println("What can I do for you?");
        System.out.println(LINE);
    }

    public void showGoodbye() {
        System.out.println(LINE);
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(LINE);
    }

    public void showLoadingError() {
        System.out.println("Error loading tasks from file. Starting with empty list.");
    }

    public void printMessage(String message) {
        System.out.println(LINE);
        System.out.println(message);
        System.out.println(LINE);
    }

    public void printAddedTask(Task task, int size) {
        System.out.println(LINE);
        System.out.println("Got it. I've added this task:");
        System.out.println("  " + task.toString());
        System.out.println("Now you have " + size + " tasks in the list.");
        System.out.println(LINE);
    }

    public void printRemoveTask(Task task, int size) {
        System.out.println(LINE);
        System.out.println("Noted. I've removed this task:");
        System.out.println("  " + task.toString());
        System.out.println("Now you have " + size + " tasks in the list.");
        System.out.println(LINE);
    }

    public void printList(TaskList tasks) {
        System.out.println(LINE);
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + ". " + tasks.getTask(i).toString());
        }
        System.out.println(LINE);
    }
}
