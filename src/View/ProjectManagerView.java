package View;

import Model.User;

import java.awt.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.awt.event.*;


public class ProjectManagerView extends JFrame {
    
// =====================================================================================
//                                    Attributes
// =====================================================================================
    
    private User user;

    JPanel projectManagerPanel;
    
    private JTable projectsTable;
    private DefaultTableModel projectsTableModel;

    private JButton manageButton;
    private JButton editButton;
    private JButton deleteButton;
    private JButton transferProjectButton;
    private JButton createNewProjectButton;
    private JButton backButton;

// =====================================================================================
//                                    Constructors
// =====================================================================================

    public ProjectManagerView(User user) {
        this.user = user;
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

// =====================================================================================
//                                    Setters
// =====================================================================================


// =====================================================================================
//                                    Methods
// =====================================================================================

    public void initComponent() {

        // ========================== Frame ==========================

        setTitle("Project Manager");
        setSize(1000, 500);
        setResizable(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // ========================== Panels ==========================

        projectManagerPanel = getBasePane();
        getContentPane().add(projectManagerPanel);

        // ========================== Pack ==========================

        setMinimumSize(getSize());
        pack();
        setMinimumSize(null);
    }

    public void showError(String error) {
        JOptionPane.showMessageDialog(
            projectManagerPanel, 
            error, 
            "Error", 
            JOptionPane.ERROR_MESSAGE
        );
    }

    public void showMessage(String message) {
        JOptionPane.showMessageDialog(
            projectManagerPanel, 
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

        projectsTable = new JTable();
        if (user.isAdmin()) {
            projectsTableModel = new DefaultTableModel(
                new Object [][] {
    
                },
                new String [] {
                    "ID", "Title", "Specialization", "Active Status", "Assigned Status", "Creator"
                }
            );
        }
        else if (user.isLecturer()) {
            projectsTableModel = new DefaultTableModel(
                new Object [][] {
    
                },
                new String [] {
                    "ID", "Title", "Specialization", "Active Status", "Assigned Status"
                }
            );
        }

        projectsTable.setModel(projectsTableModel);
        projectsTable.setDefaultEditor(Object.class, null);
        tablePane.add(new JScrollPane(projectsTable), gbc);
        
        return tablePane;
    }

    private JPanel getButtonPane() {
        
        // ========================== Layout ==========================

        JPanel buttonPane = new JPanel(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(20, 20, 20, 20);

        // ======================= Manage Button =======================

        gbc.gridx = 0;   gbc.gridy = 0;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 0.5;
        manageButton = new JButton("Manage");
        buttonPane.add(manageButton, gbc);

        // ======================= Edit Button =======================

        gbc.gridx = 0;   gbc.gridy = 1;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 0.5;
        editButton = new JButton("Edit");
        buttonPane.add(editButton, gbc);

        // ====================== Delete Button ======================

        gbc.gridx = 0;   gbc.gridy = 2;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 0.5;
        deleteButton = new JButton("Delete");
        buttonPane.add(deleteButton, gbc);

        gbc.gridx = 0;   gbc.gridy = 3;
        gbc.anchor = GridBagConstraints.CENTER;
        buttonPane.add(new JLabel(""), gbc);

        // ================== Transfer Project Button ==================

        gbc.gridx = 0;   gbc.gridy = 4;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 0.5;
        transferProjectButton = new JButton("Transfer Project");
        buttonPane.add(transferProjectButton, gbc);

        // ================== Create Project Button ==================

        gbc.gridx = 0;   gbc.gridy = 5;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 0.5;
        createNewProjectButton = new JButton("Create New Project");
        buttonPane.add(createNewProjectButton, gbc);

        // ======================= Back Button =======================

        gbc.gridx = 0;   gbc.gridy = 6;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 0.5;
        backButton = new JButton("Back");
        buttonPane.add(backButton, gbc);

        return buttonPane;
    }
    
// =====================================================================================
//                                 Button Functions
// =====================================================================================

    public void manageProject(ActionListener actionListener) {
        manageButton.addActionListener(actionListener);
    }

    public void editProject(ActionListener actionListener) {
        editButton.addActionListener(actionListener);
    }

    public void deleteProject(ActionListener actionListener) {
        deleteButton.addActionListener(actionListener);
    }

    public void transferProject(ActionListener actionListener) {
        transferProjectButton.addActionListener(actionListener);
    }

    public void createNewProject(ActionListener actionListener) {
        createNewProjectButton.addActionListener(actionListener);
    }

    public void back(ActionListener actionListener) {
        backButton.addActionListener(actionListener);
    }
}
