package todo_list;

import java.util.Objects;

public class TodoItem {
    private boolean completed;
    private String whatTodo;

    public static final String DONE = " (done)";

    public TodoItem(String todo) {
        whatTodo = todo;
        completed = false;
    }

    public boolean getStatus() {
        return completed;
    }

    public void toggleStatus() {
        completed = !completed;
    }

    public String toString() {
        return whatTodo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TodoItem todoItem = (TodoItem) o;
        return completed == todoItem.completed && Objects.equals(whatTodo, todoItem.whatTodo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(completed, whatTodo);
    }
}
