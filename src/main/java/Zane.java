import java.util.Scanner;

public class Zane {
    private Ui ui;
    private Storage storage;
    private TaskList tasks;

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
