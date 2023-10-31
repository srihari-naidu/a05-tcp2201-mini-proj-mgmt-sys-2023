package View.Student;

import Model.Student;

import java.awt.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.awt.event.*;


public class StudentDashboardView extends JFrame {
    
// =====================================================================================
//                                    Attributes
// =====================================================================================
    
    private Student student;

    private JPanel studentDashboardPanel;
    
    private JTable projectsTable;
    private DefaultTableModel projectsTableModel;

    private JLabel greetingLabel;
    private JButton viewButton;
    private JButton logoutButton;

// =====================================================================================
//                                    Constructors
// =====================================================================================

    /**
     * Initialization of student session and components.
     */
    public StudentDashboardView(Student student) {
        this.student = student;
        initComponent();
    }

// =====================================================================================
//                                    Getters
// =====================================================================================

    public JTable getProjectsTable() {
        return projectsTable;
    }

    public DefaultTableModel getProjectsTableModel() {
        return projectsTableModel;
    }

    public JLabel getGreetingLabel() {
        return greetingLabel;
    }
// =====================================================================================
//                                    Setters
// =====================================================================================


// =====================================================================================
//                                    Methods
// =====================================================================================

    public void initComponent() {

        // ========================== Frame ==========================

        setTitle("Student Dashboard");
        setSize(1000, 500);
        setResizable(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // ========================== Panels ==========================

        studentDashboardPanel = getBasePane();
        getContentPane().add(studentDashboardPanel);

        // ========================== Pack ==========================

        setMinimumSize(getSize());
        pack();
        setMinimumSize(null);
    }

    public void showError(String error) {
        JOptionPane.showMessageDialog(
            studentDashboardPanel, 
            error, 
            "Error", 
            JOptionPane.ERROR_MESSAGE
        );
    }

    public void showMessage(String message) {
        JOptionPane.showMessageDialog(
            studentDashboardPanel, 
            message
        );
    }

    private JPanel getBasePane() {

        // ========================== Layout ==========================

        JPanel basePane = new JPanel();
        basePane.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        
        // ========================== Greeting ==========================

        gbc.gridx = 0;   gbc.gridy = 0;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1.0;
        gbc.weighty = 0.10;
        basePane.add(getGreetingPane(), gbc);
        
        // ========================== Table ==========================

        gbc.gridx = 0;   gbc.gridy = 1;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1.0;
        gbc.weighty = 0.80;
        basePane.add(getTablePane(), gbc);

        // ========================== Buttons ==========================

        gbc.gridx = 0;   gbc.gridy = 2;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1.0;
        gbc.weighty = 0.10;
        basePane.add(getButtonPane(), gbc);

        return basePane;
    }

    private JPanel getGreetingPane() {
        
        // ========================== Layout ==========================

        JPanel greetingPane = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        // ====================== Delete Button ======================

        gbc.gridx = 0;   gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 0.5;
        String greeting = (
            "Hello, " + student.getName() + "! " +
            "Here are the project(s) being offered."
        );
        greetingLabel = new JLabel(greeting);
        greetingPane.add(greetingLabel, gbc);

        // ======================= Logout Button =======================

        gbc.gridx = 1;   gbc.gridy = 0;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 0.5;
        logoutButton = new JButton("Logout");
        greetingPane.add(logoutButton, gbc);

        return greetingPane;
    }

    private JPanel getTablePane() {

        // ========================== Layout ==========================

        JPanel tablePane = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 0.60;
        gbc.weighty = 0.60;

        projectsTable = new JTable();
        projectsTableModel = new DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Specialization", "Title"
            }
        );
        projectsTable.setModel(projectsTableModel);
        projectsTable.setDefaultEditor(Object.class, null);
        tablePane.add(new JScrollPane(projectsTable), gbc);
        
        return tablePane;
    }

    private JPanel getButtonPane() {
        
        // ========================== Layout ==========================

        JPanel buttonPane = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        // ======================= Manage Button =======================

        gbc.gridx = 0;   gbc.gridy = 0;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 0.5;
        viewButton = new JButton("View");
        buttonPane.add(viewButton, gbc);
        
        return buttonPane;
    }
    
// =====================================================================================
//                                 Button Functions
// =====================================================================================

    public void view(ActionListener actionListener) {
        viewButton.addActionListener(actionListener);
    }

    public void logout(ActionListener actionListener) {
        logoutButton.addActionListener(actionListener);
    }

}
