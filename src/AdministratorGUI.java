import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.io.File;

/**
 * Represents a GUI version of the school simulation.
 * Has a button to create a new school.
 * Has a button that shows all of the graduates after the specific period.
 */
public class AdministratorGUI extends JFrame {
    private JPanel panel;
    private JToolBar toolbar;
    private JButton buttonNewSchool;
    private JTextArea textArea;
    private JButton buttonGraduates;
    private String result = null;


    public AdministratorGUI() {
        add(panel);
        setTitle("School Simulation");
        setSize(700, 700);

        //Triggers the method startForm()
        buttonNewSchool.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                startForm();
            }
        });
        buttonGraduates.addComponentListener(new ComponentAdapter() {
        });
        //Changes the text area to the graduates after the Graduate button has been pressed
        buttonGraduates.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                textArea.setText(result.substring(result.lastIndexOf("Graduates:")));
            }
        });
    }

    /**
     * Starts the form for the entering of days to run.
     * Firstly, checks if there is a file selected.
     */
    private void startForm() {
        File file = getFile();
        //Check if there is a file selected and creates a form for the insertion of the number of days
        if (file != null)
            new EnterNumberOfDays(file, this);
    }

    /**
     * Sets the text area of the form to a specific string.
     *
     * @param string takes the string that has to be put on the text area
     */
    public void setJTextArea(String string) {
        result = string;
        textArea.setText(string);
    }

    /**
     * Opens a file chooser in order the user to chose a file for the school data.
     */
    private File getFile() {
        JFileChooser chooser = new JFileChooser();
        int returnVal = chooser.showOpenDialog(null);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            return chooser.getSelectedFile();
        } else return null;
    }

    public static void main(String[] args) {
        AdministratorGUI schoolAdministratorGUI = new AdministratorGUI();
        schoolAdministratorGUI.setVisible(true);
    }
}
