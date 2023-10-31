package View.Lecturer;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

import Model.Student;


public class AssignProjectView extends JFrame {
    
// =====================================================================================
//                                    Attributes
// =====================================================================================

    private Student student;

    JPanel assignProjectPanel;
    
    private JComboBox<String> projectCbo;

    private JButton assignButton;
    private JButton backButton;

// =====================================================================================
//                                    Constructors
// =====================================================================================

    public AssignProjectView(Student student) {
        this.student = student;
        initComponent();
    }

// =====================================================================================
//                                    Getters
// =====================================================================================

    public JComboBox<String> getProjectCbo() {
        return projectCbo;
    }

// =====================================================================================
//                                    Setters
// =====================================================================================



// =====================================================================================
//                                    Methods
// =====================================================================================

    public void initComponent() {

        // ========================== Frame ==========================

        setTitle("Assign Project");
        setSize(1000, 500);
        setResizable(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        // ========================== Panels ==========================
        
        assignProjectPanel = new JPanel();
        
        // ========================== Labels ==========================
        
        JLabel studentLabel = new JLabel("Student: ");
        JLabel studentName = new JLabel(student.getName());
        studentName.setFont(new Font("Arial", Font.BOLD, 20));

        JLabel projectLabel = new JLabel("Project: ");

        // ========================== Fields ==========================

        projectCbo = new JComboBox<>();

        // ========================== Buttons ==========================

        assignButton = new JButton("Assign");
        backButton = new JButton("Back");

        // ========================== Insets ==========================
        
        Insets fieldsInset = new Insets(0, 0, 10, 0);
        Insets buttonInset = new Insets(20, 0, 0, 0);

        // ========================== Layout ==========================

        assignProjectPanel.setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = fieldsInset;
        gbc.fill = GridBagConstraints.NONE;

        // ======================== Student =========================

        gbc.gridx = 0; gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        assignProjectPanel.add(studentLabel, gbc);

        gbc.gridx = 0; gbc.gridy = 1;        
        assignProjectPanel.add(studentName, gbc);
        
        // ======================== Project =========================

        gbc.gridx = 0; gbc.gridy = 3;
        gbc.anchor = GridBagConstraints.WEST;
        assignProjectPanel.add(projectLabel, gbc);

        gbc.gridx = 0; gbc.gridy = 4;
        gbc.ipadx = 110;
        assignProjectPanel.add(projectCbo, gbc);

        // ================== Assign Button ==================

        gbc.gridx = 0; gbc.gridy = 6;
        gbc.insets = buttonInset;
        assignProjectPanel.add(assignButton, gbc);

        // ====================== Back Button ======================

        gbc.gridx = 0; gbc.gridy = 7;
        gbc.insets = buttonInset;
        assignProjectPanel.add(backButton, gbc);
        
        // ========================== Pack =========================

        getContentPane().add(assignProjectPanel);
        
        setMinimumSize(getSize());
        pack();
        setMinimumSize(null);
    }
    
    public void showError(String error) {
        JOptionPane.showMessageDialog(
            assignProjectPanel, 
            error, 
            "Error", 
            JOptionPane.ERROR_MESSAGE
        );
    }

    public void showMessage(String message) {
        JOptionPane.showMessageDialog(
            assignProjectPanel, 
            message
        );
    }

// =====================================================================================
//                                 Button Functions
// =====================================================================================

    public void assignProject(ActionListener actionListener) {
        assignButton.addActionListener(actionListener);
    }

    public void back(ActionListener actionListener) {
        backButton.addActionListener(actionListener);
    }
}
