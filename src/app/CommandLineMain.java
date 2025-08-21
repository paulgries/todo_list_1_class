package app;

import todo_list.TodoList;
import todo_list.TodoItem;

import java.util.Scanner;

public class CommandLineMain {
    public static void main(String[] args) {
        TodoList todoList = new TodoList();
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.print("Enter command (add, remove, toggle, list, exit): ");
            String command = scanner.nextLine().trim().toLowerCase();
            if (command.equals("exit")) {
                break;
            }

            handleCommand(command, scanner, todoList);
        }
    }

    private static void handleCommand(String command, Scanner scanner, TodoList todoList) {
        switch (command) {
            case "add" -> {
                System.out.print("Enter todo item: ");
                String todoItem = scanner.nextLine();
                addTodo(todoItem, todoList);
            }
            case "remove" -> {
                System.out.print("Enter index to remove: ");
                int index = Integer.parseInt(scanner.nextLine());
                removeTodo(index, todoList);
            }
            case "toggle" -> {
                System.out.print("Enter index to toggle: ");
                int index = Integer.parseInt(scanner.nextLine());
                toggleTodo(index, todoList);
            }
            case "list" -> listTodos(todoList);
            default -> System.out.println("Unknown command.");
        }
    }

    private static void listTodos(TodoList todoList) {
        TodoItem[] todos = todoList.getTodos();
        for (int i = 0; i < todos.length; i++) {
            System.out.println(i + ": " + todos[i] + (todos[i].getStatus() ? TodoItem.DONE : ""));
        }
    }

    private static void toggleTodo(int index, TodoList todoList) {
        if (index >= 0 && index < todoList.size()) {
            todoList.toggleTodo(index);
        } else {
            System.out.println("Invalid index.");
        }
    }

    private static void removeTodo(int index, TodoList todoList) {
        if (index >= 0 && index < todoList.size()) {
            todoList.removeTodo(index);
        } else {
            System.out.println("Invalid index.");
        }
    }

    private static void addTodo(String todoItem, TodoList todoList) {
        todoList.addTodo(todoItem);
    }
}
