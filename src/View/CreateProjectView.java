package View;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class CreateProjectView extends JFrame {
    
// =====================================================================================
//                                    Attributes
// =====================================================================================

    JPanel createProjectPanel;

    private JTextField titleField;
    private JTextField descriptionField;
    private JTextField specializationField;
    
    private ButtonGroup statusButtonGroup;
    private JRadioButton activateRbo;
    private JRadioButton inactiveRbo;
    
    private JButton createButton;
    private JButton backButton;

// =====================================================================================
//                                    Constructors
// =====================================================================================

    public CreateProjectView() {
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

        setTitle("Create Project");
        setSize(1000, 500);
        setResizable(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        // ========================== Panels ==========================
        
        createProjectPanel = new JPanel();
        
        // ========================== Labels ==========================

        JLabel specializationLabel = new JLabel("Specialization: ");
        JLabel titleLabel = new JLabel("Title: ");
        JLabel descriptionLabel = new JLabel("Description: ");
        JLabel statusLabel = new JLabel("Status: ");

        // ========================== Fields ==========================

        titleField = new JTextField(50);
        specializationField = new JTextField(50);
        descriptionField = new JTextField(50);

        // ========================== Toggle ==========================

        statusButtonGroup = new ButtonGroup();
        activateRbo = new JRadioButton("Activate");
        inactiveRbo = new JRadioButton("Inactive");
        statusButtonGroup.add(activateRbo);
        statusButtonGroup.add(inactiveRbo);

        // ========================== Buttons ==========================

        createButton = new JButton("Create Project");
        backButton = new JButton("Back");

        // ========================== Insets ==========================
        
        Insets fieldsInset = new Insets(0, 0, 10, 0);
        Insets buttonInset = new Insets(20, 0, 0, 0);

        // ========================== Layout ==========================

        createProjectPanel.setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = fieldsInset;
        gbc.fill = GridBagConstraints.NONE;

        // ======================== Title =========================

        gbc.gridx = 0; gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        createProjectPanel.add(titleLabel, gbc);

        gbc.gridx = 0; gbc.gridy = 1;
        createProjectPanel.add(titleField, gbc);

        // ======================== Specialization =========================

        gbc.gridx = 0; gbc.gridy = 3;
        gbc.anchor = GridBagConstraints.WEST;
        createProjectPanel.add(specializationLabel, gbc);

        gbc.gridx = 0; gbc.gridy = 4;
        createProjectPanel.add(specializationField, gbc);

        // ======================== Description =========================

        gbc.gridx = 0; gbc.gridy = 6;
        gbc.anchor = GridBagConstraints.WEST;
        createProjectPanel.add(descriptionLabel, gbc);

        gbc.gridx = 0; gbc.gridy = 7;
        createProjectPanel.add(descriptionField, gbc);
        
        // ======================== Status =========================

        gbc.gridx = 0; gbc.gridy = 9;
        gbc.anchor = GridBagConstraints.WEST;
        createProjectPanel.add(statusLabel, gbc);

        gbc.gridx = 0; gbc.gridy = 10;
        gbc.insets = buttonInset;
        createProjectPanel.add(activateRbo, gbc);

        gbc.gridx = 1; gbc.gridy = 10;
        gbc.insets = buttonInset;
        createProjectPanel.add(inactiveRbo, gbc);

        // ================== Create Project Button ==================

        gbc.gridx = 0; gbc.gridy = 12;
        gbc.insets = buttonInset;
        createProjectPanel.add(createButton, gbc);

        // ====================== Back Button ======================

        gbc.gridx = 0; gbc.gridy = 13;
        gbc.insets = buttonInset;
        createProjectPanel.add(backButton, gbc);
        
        // ========================== Pack =========================

        getContentPane().add(createProjectPanel);
        
        setMinimumSize(getSize());
        pack();
        setMinimumSize(null);
    }
    
    public void showError(String error) {
        JOptionPane.showMessageDialog(
            createProjectPanel, 
            error, 
            "Error", 
            JOptionPane.ERROR_MESSAGE
        );
    }

    public void showMessage(String message) {
        JOptionPane.showMessageDialog(
            createProjectPanel, 
            message
        );
    }

// =====================================================================================
//                                 Button Functions
// =====================================================================================

    public void create(ActionListener actionListener) {
        createButton.addActionListener(actionListener);
    }

    public void back(ActionListener actionListener) {
        backButton.addActionListener(actionListener);
    }

    public void clearFields() {
        titleField.setText("");
        specializationField.setText("");
        descriptionField.setText("");
        activateRbo.setSelected(false);
        inactiveRbo.setSelected(false);
    }
}
