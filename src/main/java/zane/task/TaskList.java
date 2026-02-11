package zane.task;

import java.util.ArrayList;

/**
 * Represents a list of tasks.
 * A TaskList is a list of Task objects.
 * Provides methods to add, remove, and get tasks from the list.
 */
public class TaskList {
    private ArrayList<Task> tasks;

    /**
     * Constructor for the TaskList class.
     * Creates a new empty TaskList.
     */
    public TaskList() {
        this.tasks = new ArrayList<Task>();
    }

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public void addTask(Task task) {
        tasks.add(task);
    }

    public void removeTask(int index) {
        tasks.remove(index);
    }

    public Task getTask(int index) {
        return tasks.get(index);
    }

    public int size() {
        return tasks.size();
    }

    /**
     * Returns all tasks in the list.
     * @return An ArrayList containing all tasks.
     */
    public ArrayList<Task> getAllTasks() {
        return tasks;
    }
}
