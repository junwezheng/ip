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
        String arguments = inputParts.length > 1 ? inputParts[1] : "";

        switch (commandWord) {
        case "bye":
            return new ExitCommand();
        case "list":
            return new ListCommand();
        case "find":
            return parseFindCommand(arguments);
        case "mark":
            return parseMarkCommand(arguments);
        case "unmark":
            return parseUnmarkCommand(arguments);
        case "todo":
            return parseTodoCommand(arguments);
        case "deadline":
            return parseDeadlineCommand(arguments);
        case "event":
            return parseEventCommand(arguments);
        case "delete":
            return parseDeleteCommand(arguments);
        default:
            throw new ZaneException("I'm sorry, but I don't know what that means.");
        }
    }

    private static Command parseFindCommand(String arguments) throws ZaneException {
        if (arguments.isEmpty()) {
            throw new ZaneException("Please specify a keyword to find.");
        }
        return new FindCommand(arguments);
    }

    private static Command parseMarkCommand(String arguments) throws ZaneException {
        if (arguments.isEmpty()) {
            throw new ZaneException("Please specify which task to mark.");
        }
        int markIndex = Integer.parseInt(arguments) - 1;
        return new MarkCommand(markIndex);
    }

    private static Command parseUnmarkCommand(String arguments) throws ZaneException {
        if (arguments.isEmpty()) {
            throw new ZaneException("Please specify which task to unmark.");
        }
        int unmarkIndex = Integer.parseInt(arguments) - 1;
        return new UnmarkCommand(unmarkIndex);
    }

    private static Command parseTodoCommand(String arguments) throws ZaneException {
        if (arguments.trim().isEmpty()) {
            throw new ZaneException("The description of a todo cannot be empty.");
        }
        int priority = extractPriority(arguments);
        String description = stripPriority(arguments);
        return new AddTodoCommand(description, priority);
    }

    private static Command parseDeadlineCommand(String arguments) throws ZaneException {
        if (arguments.isEmpty()) {
            throw new ZaneException("The description of a deadline cannot be empty.");
        }
        int priority = extractPriority(arguments);
        String args = stripPriority(arguments);
        String[] parts = args.split(" /by ");
        if (parts.length < 2) {
            throw new ZaneException("Please use the format: deadline <description> /by <date>");
        }
        return new AddDeadlineCommand(parts[0], parts[1], priority);
    }

    private static Command parseEventCommand(String arguments) throws ZaneException {
        if (arguments.isEmpty()) {
            throw new ZaneException("The description of an event cannot be empty.");
        }
        int priority = extractPriority(arguments);
        String args = stripPriority(arguments);
        String[] eventParts = args.split(" /from ");
        if (eventParts.length < 2) {
            throw new ZaneException("Please use the format: event <description> /from <start> /to <end>");
        }
        String[] timeParts = eventParts[1].split(" /to ");
        if (timeParts.length < 2) {
            throw new ZaneException("Please use the format: event <description> /from <start> /to <end>");
        }
        return new AddEventCommand(eventParts[0], timeParts[0], timeParts[1], priority);
    }

    private static Command parseDeleteCommand(String arguments) throws ZaneException {
        if (arguments.isEmpty()) {
            throw new ZaneException("Please specify which task to delete.");
        }
        int deleteIndex = Integer.parseInt(arguments) - 1;
        return new DeleteCommand(deleteIndex);
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
