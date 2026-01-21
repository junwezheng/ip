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
            }
            else {
                Task task = new Task(userInput);
                list.add(task);
                printMessage("added: " + userInput);
            }
        }
    }

    public static void printMessage(String message) {
        System.out.println(LINE);
        System.out.println(message);
        System.out.println(LINE);
    }

    public static void printList(List<Task> list) {
        int count = 1;
        System.out.println(LINE);
        System.out.println("Here are the tasks in your list:");
        for (Task task : list) {
            System.out.println((count++) + ". [" + task.getStatusIcon() + "]" + " " + task.getDescription());
        }
        System.out.println(LINE);
    }
}
