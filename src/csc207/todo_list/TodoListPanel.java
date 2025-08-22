package csc207.todo_list;

import org.json.JSONArray;
import org.json.JSONObject;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class TodoListPanel extends JPanel implements ActionListener {
    public static final String DONE = " (done)";
    public static final String RESOURCES_TODO_LIST_JSON = "resources/todo_list.json";
    public static final String RESOURCES = "resources";
    private final JTextField textField;
    private final DefaultListModel<String> textModel;

    public TodoListPanel() {
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        textField = new JTextField(20);
        textField.addActionListener(this); // JTextFields fire an ActionEvent when the user types Enter

        textModel = new DefaultListModel<>();
        loadJsonFromFile();

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

        JButton save = new JButton("Save");
        save.addActionListener(e -> save());

        add(textField);
        add(scrollPane);
        add(save);
    }

    private void loadJsonFromFile() {
        ensureJsonExists();
        JSONArray jsonArray = readJsonFile();
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject jsonObject = jsonArray.getJSONObject(i);
            String task = jsonObject.getString("task");
            boolean completed = jsonObject.getBoolean("completed");
            if (completed) {
                task += DONE;
            }
            textModel.addElement(task);
        }
    }

    private static void ensureJsonExists() {
        if (!Files.exists(Paths.get(RESOURCES))) {
            try {
                Files.createDirectories(Paths.get(RESOURCES));
            } catch (IOException e) {
                throw new RuntimeException("Failed to create resources directory", e);
            }
        }

        if (!Files.exists(Paths.get(RESOURCES_TODO_LIST_JSON))) {
            try {
                Files.createFile(Paths.get(RESOURCES_TODO_LIST_JSON));
                Files.write(Paths.get(RESOURCES_TODO_LIST_JSON), "[]".getBytes());
            } catch (IOException e) {
                throw new RuntimeException("Failed to create todo_list.json file", e);
            }
        }
    }

    private JSONArray readJsonFile() {
        String jsonString = null;
        try {
            jsonString = Files.readString(Paths.get(RESOURCES_TODO_LIST_JSON));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return new JSONArray(jsonString);
    }

    private void save() {
        System.out.println("Saving items:");
        JSONArray jsonArray = new JSONArray();

        for (int i = 0; i < textModel.size(); i++) {
            JSONObject jsonObject = new JSONObject();
            String item = textModel.getElementAt(i);
            jsonObject.put("task", item.replace(DONE, "").trim());
            jsonObject.put("completed", item.endsWith(DONE));
            jsonArray.put(jsonObject);
        }

        // Here you would typically write jsonArray to a file or database
        try {
            FileWriter fileWriter = new FileWriter(RESOURCES_TODO_LIST_JSON);
            String json = jsonArray.toString();
            fileWriter.write(json);
            fileWriter.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void toggleDone(JList<String> textList) {
        int selectedIndex = textList.getSelectedIndex();
        if (selectedIndex != -1) {
            String selectedText = textModel.getElementAt(selectedIndex);
            if (selectedText.endsWith(DONE)) {
                selectedText = selectedText.substring(0, selectedText.length() - DONE.length());
            } else {
                selectedText = selectedText + DONE;
            }
            textModel.setElementAt(selectedText, selectedIndex);
        }
    }

    private void deleteItem(JList<String> textList) {
        int selectedIndex = textList.getSelectedIndex();
        if (selectedIndex != -1) {
            textModel.remove(selectedIndex);
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
        textModel.addElement(text);
        textField.selectAll();
    }

}
