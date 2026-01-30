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
            tasks = new TaskList(storage.load());
        } catch (ZaneException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    /**
     * Main run loop for user interaction.
     * Reads user input, parses it, executes the command, and updates the UI.
     */
    public void run() {
        Scanner scanner = new Scanner(System.in);
        boolean isExit = false;
        
        while (!isExit) {
            String userInput = scanner.nextLine().trim();
            try {
                Command command = Parser.parse(userInput);
                command.execute(tasks, ui, storage);
                isExit = command.isExit();
            } catch (ZaneException e) {
                ui.printMessage(e.getMessage());
            }
        }

        scanner.close();
    }

    public static void main(String[] args) {
        new Zane("data/zane.txt").run();
    }
}
