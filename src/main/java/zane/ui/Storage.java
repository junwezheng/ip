package zane.ui;

import java.io.File;
import java.io.FileWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Scanner;

import zane.task.Deadline;
import zane.task.Event;
import zane.task.Task;
import zane.task.TaskList;
import zane.task.Todo;

/**
 * Handles the storage of the tasks in the data file.
 * Loads the tasks from the data file and saves the tasks to the data file.
 */
public class Storage {
    private String filePath;

    /**
     * Constructor for the Storage class.
     * Creates the data file if it doesn't exist.
     * @param filePath The path to the data file.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
        File file = new File(filePath);

        try {
            if (file.getParentFile() != null && !file.getParentFile().exists()) {
                file.getParentFile().mkdirs();
            }

            if (!file.exists()) {
                file.createNewFile();
                System.out.println("No save file found. Created new file: " + filePath);
            }
        } catch (IOException e) {
            System.out.println("An error occurred creating the file: " + e.getMessage());
        }
    }

    public ArrayList<Task> load() throws ZaneException {
        ArrayList<Task> tasks = new ArrayList<Task>();
        File file = new File(filePath);
        
        try {
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] parts = line.split(" \\| ");

                String type = parts[0];
                boolean isDone = parts[1].equals("1");
                String description = parts[2];

                Task task = null;

                if (type.equals("T")) {
                    task = new Todo(description);
                } else if (type.equals("D")) {
                    LocalDateTime by = Deadline.parseDate(parts[3]);
                    task = new Deadline(description, by);
                } else if (type.equals("E")) {
                    String[] timeParts = parts[3].split("-");
                    String from = timeParts[0];
                    String to = timeParts.length > 1 ? timeParts[1] : "";
                    task = new Event(description, from, to);
                }

                if (task != null) {
                    if (isDone) {
                        task.setDone();
                    }
                    tasks.add(task);
                }
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            throw new ZaneException("File not found: " + filePath);
        }
        return tasks;
    }

    public void save(TaskList tasks) {
        try {
            FileWriter writer = new FileWriter(filePath);
            for (int i = 0; i < tasks.size(); i++) {
                Task task = tasks.getTask(i);
                String type = "";
                String status = task.isDone() ? "1" : "0";
                String details = "";

                if (task instanceof Todo) {
                    type = "T";
                    details = task.getDescription();
                } else if (task instanceof Deadline) {
                    Deadline deadline = (Deadline) task;
                    type = "D";
                    details = deadline.getDescription() + " | " + deadline.getByForSave();
                } else if (task instanceof Event) {
                    Event e = (Event) task;
                    type = "E";
                    details = e.getDescription() + " | " + e.getFrom() + "-" + e.getTo();
                }

                writer.write(type + " | " + status + " | " + details + System.lineSeparator());
            }
            writer.close();
        } catch (IOException e) {
            System.out.println("Error saving data: " + e.getMessage());
        }
    }
}
