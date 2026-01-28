import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.IOException;

public class Zane {
    public static final String LINE = "____________________________________________________________";
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Task> list = new ArrayList<>();

        File file = new File("data" + File.separator + "zane.txt");
        try {
            if (!file.getParentFile().exists()) {
                file.getParentFile().mkdirs();
            }

            if (!file.exists()) {
                file.createNewFile();
                System.out.println("No save file found. Created new file: data/zane.txt");
            } else {
                System.out.println("Loading data from existing file...");
                loadData(file, list);
            }
        } catch (IOException e) {
            System.out.println("An error occurred creating the file: " + e.getMessage());
        }

        System.out.println(LINE);
        System.out.println("Hello! I'm Zane");
        System.out.println("What can I do for you?");
        System.out.println(LINE);

        while (true) {
            String userInput = scanner.nextLine().trim();
            try {
                if (userInput.equals("bye")) {
                    System.out.println(LINE);
                    System.out.println("Bye. Hope to see you again soon!");
                    System.out.println(LINE);
                    break;
                }

                if (userInput.equals("list")) {
                    printList(list);
                } else if (userInput.startsWith("mark")) {
                    String[] parts = userInput.split(" ");
                    int index = Integer.parseInt(parts[1]) - 1;
                    Task task = list.get(index);
                    task.setDone();
                    saveData(list);
                    printMessage("Nice! I've marked this task as done:\n  " +
                            "[" + task.getStatusIcon() + "] " + task.getDescription());
                } else if (userInput.startsWith("unmark")) {
                    String[] parts = userInput.split(" ");
                    int index = Integer.parseInt(parts[1]) - 1;
                    Task task = list.get(index);
                    task.unsetDone();
                    saveData(list);
                    printMessage("OK, I've marked this task as not done yet:\n  " +
                            "[" + task.getStatusIcon() + "] " + task.getDescription());
                } else if (userInput.startsWith("deadline")) {
                    if (userInput.length() < 9) {
                        throw new ZaneException("This is the wrong format.");
                    }

                    String content = userInput.substring(9).trim();
                    String[] parts = content.split(" /by ");
                    String description = parts[0];
                    String by = parts[1];

                    Deadline deadline = new Deadline(description, by);
                    list.add(deadline);
                    saveData(list);
                    printAddedTask(deadline, list.size());
                } else if (userInput.startsWith("event")) {
                    if (userInput.length() < 6) {
                        throw new ZaneException("This is the wrong format.");
                    }

                    String content = userInput.substring(6).trim();
                    String[] parts = content.split(" /from ");
                    String description = parts[0];
                    String[] timeParts = parts[1].split(" /to ");
                    String from = timeParts[0];
                    String to = timeParts[1];

                    Event event = new Event(description, from, to);
                    list.add(event);
                    saveData(list);
                    printAddedTask(event, list.size());
                } else if (userInput.startsWith("todo")) {
                    if (userInput.length() < 5) {
                        throw new ZaneException("This is the wrong format. Todo is missing a task name.");
                    }

                    String description = userInput.substring(5).trim();

                    Task task = new Todo(description);
                    list.add(task);
                    saveData(list);
                    printAddedTask(task, list.size());
                } else if (userInput.startsWith("delete")) {
                    String[] parts = userInput.split(" ");
                    int index = Integer.parseInt(parts[1]) - 1;
                    Task task = list.get(index);
                    list.remove(index);
                    saveData(list);
                    printRemoveTask(task, list.size());
                }
                else {
                    throw new ZaneException("You are not speaking my language. Try again.");
                }
            } catch (ZaneException e) {
                printMessage(e.getMessage());
            }
        }
    }

    public static void printMessage(String message) {
        System.out.println(LINE);
        System.out.println(message);
        System.out.println(LINE);
    }

    public static void printAddedTask(Task task, int size) {
        System.out.println(LINE);
        System.out.println("Got it. I've added this task: \n " + task.toString());
        System.out.println("Now you have " + size + " tasks in the list.");
        System.out.println(LINE);
    }

    public static void printRemoveTask(Task task, int size) {
        System.out.println(LINE);
        System.out.println("Noted. I've removed this task: \n " + task.toString());
        System.out.println("Now you have " + size + " tasks in the list.");
        System.out.println(LINE);
    }

    public static void printList(ArrayList<Task> list) {
        int count = 1;
        System.out.println(LINE);
        System.out.println("Here are the tasks in your list:");
        for (Task task : list) {
            System.out.println((count++) + ". " + task.toString());
        }
        System.out.println(LINE);
    }

    public static void loadData(File file, ArrayList<Task> list) throws IOException {
        Scanner scanner = new Scanner(file);
        while (scanner.hasNext()) {
            String line = scanner.nextLine();
            String[] parts = line.split(" \\| ");

            String type = parts[0];
            boolean isDone = parts[1].equals("1");
            String description = parts[2];

            Task task = null;

            if (type.equals("T")) {
                task = new Todo(description);
            } else if (type.equals("D")) {
                String by = parts[3];
                task = new Deadline(description, by);
            } else if (type.equals("E")) {
                String[] timeParts = parts[3].split("-");
                String from = timeParts[0];
                String to = timeParts.length > 1 ? timeParts[1] : "";
                task = new Event(description, from, to);
            }

            if (task != null) {
                if (isDone) task.setDone();
                list.add(task);
            }
        }
    }

    public static void saveData ( ArrayList<Task> list) {
        try {
            FileWriter writer = new FileWriter("data/zane.txt");
            for (Task task : list) {
                String type = "";
                String status = task.isDone ? "1" : "0";
                String details = "";

                if (task instanceof Todo) {
                    type = "T";
                    details = task.getDescription();
                } else if (task instanceof Deadline deadline) {
                    type = "D";
                    details = deadline.getDescription() + " | " + deadline.getBy();
                } else if (task instanceof Event e) {
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
