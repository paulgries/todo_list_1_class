package todo_list;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TodoListTest {

    TodoList testFixture = new TodoList();

    @Test
    void addTodo() {
        String testTodo = "Test Todo";
        testFixture.addTodo(testTodo);
        assertEquals(1, testFixture.getTodos().length);
        assertEquals(testFixture.getTodos()[0].toString(), testTodo);
    }

    @Test
    void removeTodo() {
        String testTodo = "Test Todo";
        testFixture.addTodo(testTodo);
        assertEquals(1, testFixture.getTodos().length);

        testFixture.removeTodo(0);
        assertEquals(0, testFixture.getTodos().length);

        assertThrows(IndexOutOfBoundsException.class, () -> {
            testFixture.removeTodo(0);
        });
    }

    @Test
    void toggleTodo() {
        String testTodo = "Test Todo";
        testFixture.addTodo(testTodo);
        assertEquals(1, testFixture.size());
        assertFalse(testFixture.getTodo(0).getStatus());

        testFixture.toggleTodo(0);
        assertTrue(testFixture.getTodo(0).getStatus());

        // Toggling again should revert the status
        testFixture.toggleTodo(0);
        assertFalse(testFixture.getTodo(0).getStatus());
    }

    @Test
    void getTodos() {
        String testTodo1 = "Test Todo 1";
        String testTodo2 = "Test Todo 2";
        testFixture.addTodo(testTodo1);
        testFixture.addTodo(testTodo2);

        TodoItem expected1 = new TodoItem(testTodo1);
        TodoItem expected2 = new TodoItem(testTodo2);

        TodoItem[] todos = testFixture.getTodos();
        assertEquals(2, todos.length);
        assertEquals(expected1, todos[0]);
        assertEquals(expected2, todos[1]);

        // Toggle the first TodoItem and check again
        testFixture.toggleTodo(0);
        expected1.toggleStatus();
        todos = testFixture.getTodos();
        assertEquals(expected1, todos[0]);
    }
}