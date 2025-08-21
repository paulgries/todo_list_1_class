package todo_list;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class TodoList implements Serializable {
     private List<TodoItem> todoItems = new ArrayList<>();

     public TodoItem getTodo(int i) {
         return todoItems.get(i);
    }

    public void addTodo(String todo) {
        todoItems.add(new TodoItem(todo));
    }

    public void removeTodo(int i) {
        todoItems.remove(i);
    }

    public void toggleTodo(int i) {
        if (i >= 0 && i < todoItems.size()) {
            todoItems.get(i).toggleStatus();
        }
    }

    public TodoItem[] getTodos() {
        return todoItems.toArray(new TodoItem[0]);
    }

    public int size() {
         return todoItems.size();
    }

}
