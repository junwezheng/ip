package zane.ui;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Pattern;

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
    private static final String FILE_DELIMITER = " | ";

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

    /**
     * Loads tasks from the data file.
     * @return An ArrayList of tasks loaded from the file.
     * @throws ZaneException If the file is not found or cannot be read.
     */
    public ArrayList<Task> loadTasksFromFile() throws ZaneException {
        ArrayList<Task> tasks = new ArrayList<Task>();
        File file = new File(filePath);

        try {
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] parts = line.split(Pattern.quote(FILE_DELIMITER));

                String typeField = parts[0];
                boolean isDone = parts[1].equals("1");
                String description = parts[2];
                int priority = 3;
                String type;

                if (typeField.contains("|")) {
                    String[] typeParts = typeField.split("\\|");
                    priority = Integer.parseInt(typeParts[0].substring(1));
                    type = typeParts[1];
                } else {
                    type = typeField;
                }

                Task task = null;

                if (type.equals("T")) {
                    task = new Todo(description, priority);
                } else if (type.equals("D")) {
                    LocalDateTime by = Deadline.parseDate(parts[3]);
                    task = new Deadline(description, by, priority);
                } else if (type.equals("E")) {
                    String[] timeParts = parts[3].split("-");
                    String startTime = timeParts[0];
                    String endTime = timeParts.length > 1 ? timeParts[1] : "";
                    task = new Event(description, startTime, endTime, priority);
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
                writer.write(task.toFileString() + System.lineSeparator());
            }
            writer.close();
        } catch (IOException e) {
            System.out.println("Error saving data: " + e.getMessage());
        }
    }
}
