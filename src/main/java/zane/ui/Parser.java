package zane.ui;

import zane.commands.AddDeadlineCommand;
import zane.commands.AddEventCommand;
import zane.commands.AddTodoCommand;
import zane.commands.Command;
import zane.commands.DeleteCommand;
import zane.commands.ExitCommand;
import zane.commands.ListCommand;
import zane.commands.MarkCommand;
import zane.commands.UnmarkCommand;
import zane.commands.FindCommand;

public class Parser {

    public static Command parse(String userInput) throws ZaneException {
        String[] parts = userInput.split(" ", 2);
        String commandWord = parts[0];
        
        switch (commandWord) {
        case "bye":
            return new ExitCommand();
            
        case "list":
            return new ListCommand();

        case "find":
            if (parts.length < 2) {
                throw new ZaneException("Please specify a keyword to find.");
            }
            return new FindCommand(parts[1]);
            
        case "mark":
            if (parts.length < 2) {
                throw new ZaneException("Please specify which task to mark.");
            }
            int markIndex = Integer.parseInt(parts[1]) - 1;
            return new MarkCommand(markIndex);
            
        case "unmark":
            if (parts.length < 2) {
                throw new ZaneException("Please specify which task to unmark.");
            }
            int unmarkIndex = Integer.parseInt(parts[1]) - 1;
            return new UnmarkCommand(unmarkIndex);
            
        case "todo":
            if (parts.length < 2 || parts[1].trim().isEmpty()) {
                throw new ZaneException("The description of a todo cannot be empty.");
            }
            return new AddTodoCommand(parts[1]);
            
        case "deadline":
            if (parts.length < 2) {
                throw new ZaneException("The description of a deadline cannot be empty.");
            }
            String[] deadlineParts = parts[1].split(" /by ");
            if (deadlineParts.length < 2) {
                throw new ZaneException("Please use the format: deadline <description> /by <date>");
            }
            return new AddDeadlineCommand(deadlineParts[0], deadlineParts[1]);
            
        case "event":
            if (parts.length < 2) {
                throw new ZaneException("The description of an event cannot be empty.");
            }
            String[] eventParts = parts[1].split(" /from ");
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
            if (parts.length < 2) {
                throw new ZaneException("Please specify which task to delete.");
            }
            int deleteIndex = Integer.parseInt(parts[1]) - 1;
            return new DeleteCommand(deleteIndex);
            
        default:
            throw new ZaneException("I'm sorry, but I don't know what that means.");
        }
    }
}
