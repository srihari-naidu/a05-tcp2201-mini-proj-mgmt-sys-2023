package View;

import Model.Project;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class EditProjectView extends JFrame {
    
// =====================================================================================
//                                    Attributes
// =====================================================================================

    private Project project;

    JPanel editProjectPanel;

    private JTextField titleField;
    private JTextField descriptionField;
    private JTextField specializationField;
    
    private ButtonGroup statusButtonGroup;
    private JRadioButton activateRbo;
    private JRadioButton inactiveRbo;
    
    private JButton updateButton;
    private JButton backButton;

// =====================================================================================
//                                    Constructors
// =====================================================================================

    public EditProjectView(Project project) {
        this.project = project;
        initComponent();
    }

// =====================================================================================
//                                    Getters
// =====================================================================================

    public String getTitle() {
        return titleField.getText();
    }

    public String getDescription() {
        return descriptionField.getText();
    }
    
    public String getSpecialization() {
        return specializationField.getText();
    }

    public boolean getStatus() {
        if (statusButtonGroup.getSelection().equals(activateRbo.getModel())) {
            return true;
        }
        else if (statusButtonGroup.getSelection().equals(inactiveRbo.getModel())) {
            return false;
        }
        return false;
    }

// =====================================================================================
//                                    Setters
// =====================================================================================



// =====================================================================================
//                                    Methods
// =====================================================================================

    public void initComponent() {

        // ========================== Frame ==========================

        setTitle("Edit Project");
        setSize(1000, 500);
        setResizable(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        // ========================== Panels ==========================
        
        editProjectPanel = new JPanel();
        
        // ========================== Labels ==========================

        JLabel specializationLabel = new JLabel("Specialization: ");
        JLabel titleLabel = new JLabel("Title: ");
        JLabel descriptionLabel = new JLabel("Description: ");
        JLabel statusLabel = new JLabel("Status: ");

        // ========================== Fields ==========================

        titleField = new JTextField(50);
        titleField.setText(project.getTitle());
        
        specializationField = new JTextField(50);
        specializationField.setText(project.getSpecialization());
        
        descriptionField = new JTextField(50);
        descriptionField.setText(project.getDescription());

        // ========================== Toggle ==========================

        statusButtonGroup = new ButtonGroup();
        activateRbo = new JRadioButton("Activate");
        inactiveRbo = new JRadioButton("Inactive");
        statusButtonGroup.add(activateRbo);
        statusButtonGroup.add(inactiveRbo);

        if (project.getActive() == true) {
            activateRbo.setSelected(true);
        }
        else if (project.getActive() == false) {
            inactiveRbo.setSelected(true);
        }

        // ========================== Buttons ==========================

        updateButton = new JButton("Update");
        backButton = new JButton("Back");

        // ========================== Insets ==========================
        
        Insets fieldsInset = new Insets(0, 0, 10, 0);
        Insets buttonInset = new Insets(20, 0, 0, 0);

        // ========================== Layout ==========================

        editProjectPanel.setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = fieldsInset;
        gbc.fill = GridBagConstraints.NONE;

        // ======================== Title =========================

        gbc.gridx = 0; gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        editProjectPanel.add(titleLabel, gbc);

        gbc.gridx = 0; gbc.gridy = 1;
        editProjectPanel.add(titleField, gbc);

        // ======================== Specialization =========================

        gbc.gridx = 0; gbc.gridy = 3;
        gbc.anchor = GridBagConstraints.WEST;
        editProjectPanel.add(specializationLabel, gbc);

        gbc.gridx = 0; gbc.gridy = 4;
        editProjectPanel.add(specializationField, gbc);

        // ======================== Description =========================

        gbc.gridx = 0; gbc.gridy = 6;
        gbc.anchor = GridBagConstraints.WEST;
        editProjectPanel.add(descriptionLabel, gbc);

        gbc.gridx = 0; gbc.gridy = 7;
        editProjectPanel.add(descriptionField, gbc);
        
        // ======================== Status =========================

        gbc.gridx = 0; gbc.gridy = 9;
        gbc.anchor = GridBagConstraints.WEST;
        editProjectPanel.add(statusLabel, gbc);

        gbc.gridx = 0; gbc.gridy = 10;
        gbc.insets = buttonInset;
        editProjectPanel.add(activateRbo, gbc);

        gbc.gridx = 1; gbc.gridy = 10;
        gbc.insets = buttonInset;
        editProjectPanel.add(inactiveRbo, gbc);

        // ================== Update Project Button ==================

        gbc.gridx = 0; gbc.gridy = 12;
        gbc.insets = buttonInset;
        editProjectPanel.add(updateButton, gbc);

        // ====================== Back Button ======================

        gbc.gridx = 0; gbc.gridy = 13;
        gbc.insets = buttonInset;
        editProjectPanel.add(backButton, gbc);
        
        // ========================== Pack =========================

        getContentPane().add(editProjectPanel);
        
        setMinimumSize(getSize());
        pack();
        setMinimumSize(null);
    }
    
    public void showError(String error) {
        JOptionPane.showMessageDialog(
            editProjectPanel, 
            error, 
            "Error", 
            JOptionPane.ERROR_MESSAGE
        );
    }

    public void showMessage(String message) {
        JOptionPane.showMessageDialog(
            editProjectPanel, 
            message
        );
    }

// =====================================================================================
//                                 Button Functions
// =====================================================================================

    public void update(ActionListener actionListener) {
        updateButton.addActionListener(actionListener);
    }

    public void back(ActionListener actionListener) {
        backButton.addActionListener(actionListener);
    }
}
