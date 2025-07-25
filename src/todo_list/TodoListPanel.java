package todo_list;

import java.awt.event.*;
import javax.swing.*;

public class TodoList extends JPanel implements ActionListener {
    private final JTextField textField;
    private final DefaultListModel<String> textModel;

    public TodoList() {
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        textField = new JTextField(20);
        textField.addActionListener(this); // JTextFields fire an ActionEvent when the user types Enter

        textModel = new DefaultListModel<>();
        JList<String> textList = new JList<>(textModel);
        JScrollPane scrollPane = new JScrollPane(textList);

        add(textField);
        add(scrollPane);
    }

    public void actionPerformed(ActionEvent evt) {
        String text = textField.getText();
        textModel.addElement(text);
        textField.selectAll();
    }

}
