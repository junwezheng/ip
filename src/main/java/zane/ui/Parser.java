package zane.ui;

import zane.commands.AddDeadlineCommand;
import zane.commands.AddEventCommand;
import zane.commands.AddTodoCommand;
import zane.commands.Command;
import zane.commands.DeleteCommand;
import zane.commands.ExitCommand;
import zane.commands.FindCommand;
import zane.commands.ListCommand;
import zane.commands.MarkCommand;
import zane.commands.UnmarkCommand;

/**
 * Handles the parsing of the user input.
 * Converts the user input into a Command object.
 */
public class Parser {

    /**
     * Parses the user input and returns a Command object.
     * @param userInput The user input to parse.
     * @return A Command object.
     * @throws ZaneException If the user input is invalid.
     */
    public static Command parse(String userInput) throws ZaneException {
        String[] inputParts = userInput.split(" ", 2);
        String commandWord = inputParts[0];
        assert commandWord != null : "Command word cannot be null";

        switch (commandWord) {
        case "bye":
            return new ExitCommand();

        case "list":
            return new ListCommand();

        case "find":
            if (inputParts.length < 2) {
                throw new ZaneException("Please specify a keyword to find.");
            }
            return new FindCommand(inputParts[1]);

        case "mark":
            if (inputParts.length < 2) {
                throw new ZaneException("Please specify which task to mark.");
            }
            int markIndex = Integer.parseInt(inputParts[1]) - 1;
            return new MarkCommand(markIndex);

        case "unmark":
            if (inputParts.length < 2) {
                throw new ZaneException("Please specify which task to unmark.");
            }
            int unmarkIndex = Integer.parseInt(inputParts[1]) - 1;
            return new UnmarkCommand(unmarkIndex);

        case "todo":
            if (inputParts.length < 2 || inputParts[1].trim().isEmpty()) {
                throw new ZaneException("The description of a todo cannot be empty.");
            }
            return new AddTodoCommand(inputParts[1]);

        case "deadline":
            if (inputParts.length < 2) {
                throw new ZaneException("The description of a deadline cannot be empty.");
            }
            String[] deadlineParts = inputParts[1].split(" /by ");
            if (deadlineParts.length < 2) {
                throw new ZaneException("Please use the format: deadline <description> /by <date>");
            }
            return new AddDeadlineCommand(deadlineParts[0], deadlineParts[1]);

        case "event":
            if (inputParts.length < 2) {
                throw new ZaneException("The description of an event cannot be empty.");
            }
            String[] eventParts = inputParts[1].split(" /from ");
            if (eventParts.length < 2) {
                throw new ZaneException("Please use the format: event <description> /from <start> /to <end>");
            }
            String description = eventParts[0];
            String[] timeParts = eventParts[1].split(" /to ");
            if (timeParts.length < 2) {
                throw new ZaneException("Please use the format: event <description> /from <start> /to <end>");
            }
            return new AddEventCommand(description, timeParts[0], timeParts[1]);

        case "delete":
            if (inputParts.length < 2) {
                throw new ZaneException("Please specify which task to delete.");
            }
            int deleteIndex = Integer.parseInt(inputParts[1]) - 1;
            return new DeleteCommand(deleteIndex);

        default:
            throw new ZaneException("I'm sorry, but I don't know what that means.");
        }
    }
}
