package View.Lecturer;

import java.awt.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.*;


public class StudentManagerView extends JFrame {
    
// ======================================================================\===============
//                                    Attributes
// =====================================================================================

    JPanel studentManagerPanel;
    
    private JTable studentsTable;
    private DefaultTableModel studentsTableModel;

    private JButton viewButton;
    private JButton assignProjectButton;
    private JButton unassignProjectButton;
    private JButton backButton;

// =====================================================================================
//                                    Constructors
// =====================================================================================

    public StudentManagerView() {
        initComponent();
    }

// =====================================================================================
//                                    Getters
// =====================================================================================

    public JTable getStudentsTable() {
        return studentsTable;
    }

    public DefaultTableModel getStudentsTableModel() {
        return studentsTableModel;
    }

// =====================================================================================
//                                    Setters
// =====================================================================================


// =====================================================================================
//                                    Methods
// =====================================================================================

    public void initComponent() {

        // ========================== Frame ==========================

        setTitle("Student Manager");
        setSize(1000, 500);
        setResizable(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // ========================== Panels ==========================

        studentManagerPanel = getBasePane();
        getContentPane().add(studentManagerPanel);

        // ========================== Pack ==========================

        setMinimumSize(getSize());
        pack();
        setMinimumSize(null);
    }

    public void showError(String error) {
        JOptionPane.showMessageDialog(
            studentManagerPanel, 
            error, 
            "Error", 
            JOptionPane.ERROR_MESSAGE
        );
    }

    public void showMessage(String message) {
        JOptionPane.showMessageDialog(
            studentManagerPanel, 
            message
        );
    }

    private JPanel getBasePane() {

        // ========================== Layout ==========================

        JPanel basePane = new JPanel();
        basePane.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        
        // ========================== Panels ==========================

        gbc.gridx = 0;   gbc.gridy = 0;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 0.85;
        gbc.weighty = 1.0;
        basePane.add(getTablePane(), gbc);

        // ========================== Buttons ==========================

        gbc.gridx = 1;   gbc.gridy = 0;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 0.15;
        gbc.weighty = 1.0;
        basePane.add(getButtonPane(), gbc);

        return basePane;
    }

    private JPanel getTablePane() {

        // ========================== Layout ==========================

        JPanel tablePane = new JPanel(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();

        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;

        studentsTable = new JTable();
        studentsTableModel = new DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Name", "Specialization", "Project"
            }
        );
        
        studentsTable.setModel(studentsTableModel);
        studentsTable.setDefaultEditor(Object.class, null);
        tablePane.add(new JScrollPane(studentsTable), gbc);
        
        return tablePane;
    }

    private JPanel getButtonPane() {
        
        // ========================== Layout ==========================

        JPanel buttonPane = new JPanel(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(20, 20, 20, 20);

        // ======================= View Button =======================

        gbc.gridx = 0;   gbc.gridy = 0;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 0.5;
        viewButton = new JButton("View");
        buttonPane.add(viewButton, gbc);

        gbc.gridx = 0;   gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.CENTER;
        buttonPane.add(new JLabel(""), gbc);

        // ====================== Assign Button ======================
        
        gbc.gridx = 0;   gbc.gridy = 2;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 0.5;
        assignProjectButton = new JButton("Assign Project");
        buttonPane.add(assignProjectButton, gbc);

        // ====================== Unassign Button ======================

        gbc.gridx = 0;   gbc.gridy = 3;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 0.5;
        unassignProjectButton = new JButton("Unassign Project");
        buttonPane.add(unassignProjectButton, gbc);
        
        gbc.gridx = 0;   gbc.gridy = 4;
        gbc.anchor = GridBagConstraints.CENTER;
        buttonPane.add(new JLabel(""), gbc);

        // ======================= Back Button =======================

        gbc.gridx = 0;   gbc.gridy = 5;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 0.5;
        backButton = new JButton("Back");
        buttonPane.add(backButton, gbc);

        return buttonPane;
    }
    
// =====================================================================================
//                                 Button Functions
// =====================================================================================

    public void viewStudent(ActionListener actionListener) {
        viewButton.addActionListener(actionListener);
    }

    public void assignProject(ActionListener actionListener) {
        assignProjectButton.addActionListener(actionListener);
    }

    public void unassignProject(ActionListener actionListener) {
        unassignProjectButton.addActionListener(actionListener);
    }

    public void back(ActionListener actionListener) {
        backButton.addActionListener(actionListener);
    }

}
