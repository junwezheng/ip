import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Zane {
    public static final String LINE = "____________________________________________________________";
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Task> list = new ArrayList<>();

        System.out.println(LINE);
        System.out.println("Hello! I'm Zane");
        System.out.println("What can I do for you?");
        System.out.println(LINE);

        while (true) {
            String userInput = scanner.nextLine().trim();
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
                printMessage("Nice! I've marked this task as done:\n  " +
                        "[" + task.getStatusIcon() + "] " + task.getDescription());
            } else if (userInput.startsWith("unmark")) {
                String[] parts = userInput.split(" ");
                int index = Integer.parseInt(parts[1]) - 1;
                Task task = list.get(index);
                task.unsetDone();
                printMessage("OK, I've marked this task as not done yet:\n  " +
                        "[" + task.getStatusIcon() + "] " + task.getDescription());
            } else if (userInput.startsWith("deadline")) {
                String content = userInput.substring(9).trim();
                String[] parts = content.split(" /by ");
                String description = parts[0];
                String by = parts[1];

                Deadline deadline = new Deadline(description, by);
                list.add(deadline);
                printAddedTask(deadline, list.size());
            } else if (userInput.startsWith("event")) {
                String content = userInput.substring(6).trim();
                String[] parts = content.split(" /from ");
                String description = parts[0];
                String[] timeParts = parts[1].split(" /to ");
                String from = timeParts[0];
                String to = timeParts[1];

                Event event = new Event(description, from, to);
                list.add(event);
                printAddedTask(event, list.size());
            } else if (userInput.startsWith("todo")) {
                String description = userInput.substring(5).trim();

                Task task = new Todo(description);
                list.add(task);

                printAddedTask(task, list.size());
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

    public static void printList(List<Task> list) {
        int count = 1;
        System.out.println(LINE);
        System.out.println("Here are the tasks in your list:");
        for (Task task : list) {
            System.out.println((count++) + ". " + task.toString());
        }
        System.out.println(LINE);
    }
}
