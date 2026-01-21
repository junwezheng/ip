import java.util.Scanner;

public class Zane {
    public static final String LINE = "____________________________________________________________";
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[] list = new String[100];
        int count = 0;

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
                printList(list, count);
            } else {
                list[count] = userInput;
                count++;
                printMessage( "added: " + userInput);
            }
        }
    }

    public static void printMessage(String message) {
        System.out.println(LINE);
        System.out.println(message);
        System.out.println(LINE);
    }

    public static void printList(String[] list, int count) {
        System.out.println(LINE);
        for (int i = 0; i < count; i++) {
            System.out.println((i + 1) + ". " + list[i]);
        }
        System.out.println(LINE);
    }
}
