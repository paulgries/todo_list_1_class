package todo_list;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.Arrays;

public class TodoListPanel extends JPanel implements ActionListener {
    private final JTextField textField;
    private final DefaultListModel<String> textModel;

    private TodoList todoList = new TodoList();

    public TodoListPanel() {
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        textField = new JTextField(20);
        textField.addActionListener(this); // JTextFields fire an ActionEvent when the user types Enter

        textModel = new DefaultListModel<>();
        JList<String> textList = new JList<>(textModel);
        JScrollPane scrollPane = new JScrollPane(textList);

        ListSelectionModel listSelectionModel = textList.getSelectionModel();
        listSelectionModel.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        listSelectionModel.addListSelectionListener(e -> selectItem(textList));

        textList.addKeyListener(new java.awt.event.KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent evt) {
                if (evt.getKeyCode() == KeyEvent.VK_DELETE || evt.getKeyCode() == KeyEvent.VK_BACK_SPACE) {
                    deleteItem(textList);
                } else if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
                    toggleDone(textList);
                }
            }
        });

        add(textField);
        add(scrollPane);
    }

    private void toggleDone(JList<String> textList) {
        int selectedIndex = textList.getSelectedIndex();
        if (selectedIndex != -1) {
            todoList.toggleTodo(selectedIndex);
            updateTodoModel();
            ListSelectionModel listSelectionModel = textList.getSelectionModel();
            listSelectionModel.setSelectionInterval(selectedIndex, selectedIndex);
        }
    }

    private void deleteItem(JList<String> textList) {
        int selectedIndex = textList.getSelectedIndex();
        if (selectedIndex != -1) {
            todoList.removeTodo(selectedIndex);
            updateTodoModel();
        }
    }

    private void selectItem(JList<String> textList) {
        int selectedIndex = textList.getSelectedIndex();
        if (selectedIndex != -1) {
            String selectedText = textModel.getElementAt(selectedIndex);
            textField.setText(selectedText);
        }
    }

    public void actionPerformed(ActionEvent evt) {
        String text = textField.getText();
        todoList.addTodo(text);
        updateTodoModel();
        textField.selectAll();
    }

    private void updateTodoModel() {
        textModel.clear();
        textModel.addAll(Arrays.stream(todoList.getTodos())
                .map(todo -> todo.toString() + (todo.getStatus() ? TodoItem.DONE : ""))
                .toList());
    }

}
