package zane.ui;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;
import zane.commands.*;

public class ParserTest {
    @Test
    public void testParse() throws ZaneException {
        assertTrue(Parser.parse("bye") instanceof ExitCommand);
        assertTrue(Parser.parse("list") instanceof ListCommand);
        assertTrue(Parser.parse("mark 1") instanceof MarkCommand);
        assertTrue(Parser.parse("unmark 1") instanceof UnmarkCommand);
        assertTrue(Parser.parse("todo book airplane ticket") instanceof AddTodoCommand);
        assertTrue(Parser.parse("deadline book airplane ticket /by 2026-01-30 10:00") instanceof AddDeadlineCommand);
        assertTrue(Parser.parse("event project meeting /from 2026-01-30 10:00 /to 2026-01-30 11:00") instanceof AddEventCommand);
        assertTrue(Parser.parse("delete 1") instanceof DeleteCommand);
    }

    @Test
    public void testParseInvalidCommand() {
        assertThrows(ZaneException.class, () -> Parser.parse("invalid"));
    }

    @Test
    public void testParseEmptyTodo() {
        assertThrows(ZaneException.class, () -> Parser.parse("todo"));
    }
}
