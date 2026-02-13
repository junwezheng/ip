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
    private static final int DEFAULT_PRIORITY = 3; // default lowest priority

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
            int todoPriority = extractPriority(inputParts[1]);
            String todoDescription = stripPriority(inputParts[1]);
            return new AddTodoCommand(todoDescription, todoPriority);

        case "deadline":
            if (inputParts.length < 2) {
                throw new ZaneException("The description of a deadline cannot be empty.");
            }
            int deadlinePriority = extractPriority(inputParts[1]);
            String deadlineArgs = stripPriority(inputParts[1]);
            String[] deadlineParts = deadlineArgs.split(" /by ");
            if (deadlineParts.length < 2) {
                throw new ZaneException("Please use the format: deadline <description> /by <date>");
            }
            return new AddDeadlineCommand(deadlineParts[0], deadlineParts[1], deadlinePriority);

        case "event":
            if (inputParts.length < 2) {
                throw new ZaneException("The description of an event cannot be empty.");
            }
            int eventPriority = extractPriority(inputParts[1]);
            String eventArgs = stripPriority(inputParts[1]);
            String[] eventParts = eventArgs.split(" /from ");
            if (eventParts.length < 2) {
                throw new ZaneException("Please use the format: event <description> /from <start> /to <end>");
            }
            String description = eventParts[0];
            String[] timeParts = eventParts[1].split(" /to ");
            if (timeParts.length < 2) {
                throw new ZaneException("Please use the format: event <description> /from <start> /to <end>");
            }
            return new AddEventCommand(description, timeParts[0], timeParts[1], eventPriority);

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

    /**
     * Extracts the priority from the user input.
     * @param args The user input to extract the priority from.
     * @return The priority.
     * @throws ZaneException If the priority is invalid.
     */
    private static int extractPriority(String args) throws ZaneException {
        if (args.contains(" /p ")) {
            String[] parts = args.split(" /p ");
            String pValue = parts[parts.length - 1].trim();
            int priority = Integer.parseInt(pValue);
            if (priority < 1 || priority > 3) {
                throw new ZaneException("Priority must be 1, 2, or 3.");
            }

            return priority;
        }
        return DEFAULT_PRIORITY;
    }

    private static String stripPriority(String args) {
        if (args.contains(" /p ")) {
            return args.substring(0, args.lastIndexOf(" /p ")).trim();
        }
        return args;
    }
}
