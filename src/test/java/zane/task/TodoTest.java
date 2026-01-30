package zane.task;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TodoTest {
    @Test
    public void testTodo() {
        Todo todo = new Todo("book airplane ticket");
        assertEquals("[T][ ] book airplane ticket", todo.toString());
    }

    @Test
    public void testTodoMarked() {
        Todo todo = new Todo("book airplane ticket");
        todo.setDone();
        assertEquals("[T][X] book airplane ticket", todo.toString());
    }
}
