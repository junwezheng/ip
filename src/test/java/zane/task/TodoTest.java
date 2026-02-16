package zane.task;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TodoTest {
    @Test
    public void testTodoDefaultPriority() {
        Todo todo = new Todo("book airplane ticket", 3);
        assertEquals("[P3][T][ ] book airplane ticket", todo.toString());
    }

    @Test
    public void testTodoHighPriority() {
        Todo todo = new Todo("book airplane ticket", 1);
        assertEquals("[P1][T][ ] book airplane ticket", todo.toString());
    }

    @Test
    public void testTodoMarked() {
        Todo todo = new Todo("book airplane ticket", 3);
        todo.setIsDone();
        assertEquals("[P3][T][X] book airplane ticket", todo.toString());
    }
}
