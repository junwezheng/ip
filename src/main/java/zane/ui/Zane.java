package zane.ui;

import java.util.Scanner;

import zane.commands.Command;
import zane.task.TaskList;

/**
 * Main class for the Zane application.
 * Handles initialisation and the main run loop for user interaction.
 */
public class Zane {
    private Ui ui;
    private Storage storage;
    private TaskList tasks;

    /**
     * Constructor for the Zane class.
     * Initialises the UI, storage, and loads the tasks from the data file.
     * @param filePath The path to the data file.
     */
    public Zane(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.loadTasksFromFile());
        } catch (ZaneException e) {
            System.out.println(ui.getLoadingErrorMessage());
            tasks = new TaskList();
        }
    }

    /**
     * Generates a response for the user's chat message.
     * Used by the GUI to get a response string from the bot.
     * @param input The user input to process.
     * @return The response string from executing the command.
     */
    public String getResponse(String input) {
        assert input != null : "User input cannot be null";
        try {
            Command command = Parser.parse(input);
            return command.execute(tasks, ui, storage);
        } catch (ZaneException e) {
            return e.getMessage();
        }
    }

    /**
     * Main run loop for user interaction (CLI mode).
     * Reads user input, parses it, executes the command, and prints the response.
     */
    public void run() {
        Scanner scanner = new Scanner(System.in);
        boolean isExit = false;

        while (!isExit) {
            String userInput = scanner.nextLine().trim();
            assert userInput != null : "User input cannot be null";
            try {
                Command command = Parser.parse(userInput);
                String response = command.execute(tasks, ui, storage);
                System.out.println(response);
                isExit = command.isExit();
            } catch (ZaneException e) {
                System.out.println(e.getMessage());
            }
        }

        scanner.close();
    }

    public static void main(String[] args) {
        new Zane("data/zane.txt").run();
    }
}
