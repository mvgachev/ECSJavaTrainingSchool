import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

/**
 * Represents a form that asks the user to enter the number of days the school is to run.
 */
public class EnterNumberOfDays extends JFrame {
    private JTextField textField;
    private JPanel panel;
    private JButton submitButton;
    private String stringGUI = null;

    public EnterNumberOfDays(File file, AdministratorGUI administratorGUI) {
        add(panel);
        setSize(350, 250);
        setVisible(true);

        //When the button is pressed the text area of the schoolGUI is changed to the pretty string
        //The form is closed
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                startSchool(file, Integer.parseInt(textField.getText()));
                administratorGUI.setJTextArea(stringGUI);
                dispose();
            }
        });
    }

    /**
     * Starts the school with a specific file and number of days entered by the user.
     */
    private void startSchool(File file, int numberOfDays) {
        Starter starter = new Starter(file, numberOfDays);
        stringGUI = starter.getStringGUI();
    }
}
