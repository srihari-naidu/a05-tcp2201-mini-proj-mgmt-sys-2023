package View.Admin;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

import Model.User;

public class EditUserView extends JFrame {
    
// =====================================================================================
//                                    Attributes
// =====================================================================================

    JPanel editUserPanel;

    private JTextField firstnameField;
    private JTextField lastnameField;
    private JTextField usernameField;
    private JTextField passwordField;

    private JButton updateButton;
    private JButton backButton;

// =====================================================================================
//                                    Constructors
// =====================================================================================

    public EditUserView(User userToEdit) {
        initComponent(userToEdit);
    }

// =====================================================================================
//                                    Getters
// =====================================================================================

    public String getFirstname() {
        return firstnameField.getText();
    }

    public String getLastname() {
        return lastnameField.getText();
    }

    public String getUsername() {
        return usernameField.getText();
    }

    public String getPassword() {
        return passwordField.getText();
    }

// =====================================================================================
//                                    Setters
// =====================================================================================



// =====================================================================================
//                                    Methods
// =====================================================================================

    public void initComponent(User userToEdit) {
        
        // ========================== Frame ==========================

        setTitle("Edit User");
        setSize(1000, 500);
        setResizable(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        // ========================== Panels ==========================
        
        editUserPanel = new JPanel();
        
        // ========================== Labels ==========================

        JLabel firstnameLabel = new JLabel("First Name: ");
        JLabel lastnameLabel = new JLabel("Last Name: ");
        JLabel usernameLabel = new JLabel("Username: ");
        JLabel passwordLabel = new JLabel("Password: ");

        // ========================== Fields ==========================

        firstnameField = new JTextField(25);
        firstnameField.setText(userToEdit.getFirstname());
        
        lastnameField = new JTextField(25);
        lastnameField.setText(userToEdit.getLastname());
        
        usernameField = new JTextField(25);
        usernameField.setText(userToEdit.getUsername());
        
        passwordField = new JTextField(25);
        passwordField.setText(userToEdit.getPassword());

        // ========================== Buttons ==========================

        updateButton = new JButton("Update");
        backButton = new JButton("Back");

        // ========================== Insets ==========================a
        
        Insets fieldsInset = new Insets(0, 0, 10, 0);
        Insets buttonInset = new Insets(20, 0, 0, 0);

        // ========================== Layout ==========================

        editUserPanel.setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = fieldsInset;
        gbc.fill = GridBagConstraints.NONE;

        // ======================== Firstname =========================

        gbc.gridx = 0; gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        editUserPanel.add(firstnameLabel, gbc);

        gbc.gridx = 0; gbc.gridy = 1;
        editUserPanel.add(firstnameField, gbc);

        // ======================== Lastname =========================

        gbc.gridx = 0; gbc.gridy = 3;
        gbc.anchor = GridBagConstraints.WEST;
        editUserPanel.add(lastnameLabel, gbc);

        gbc.gridx = 0; gbc.gridy = 4;
        editUserPanel.add(lastnameField, gbc);

        // ======================== Username =========================

        gbc.gridx = 0; gbc.gridy = 6;
        gbc.anchor = GridBagConstraints.WEST;
        editUserPanel.add(usernameLabel, gbc);

        gbc.gridx = 0; gbc.gridy = 7;
        editUserPanel.add(usernameField, gbc);

        // ======================== Password =========================

        gbc.gridx = 0; gbc.gridy = 9;
        gbc.anchor = GridBagConstraints.WEST;
        editUserPanel.add(passwordLabel, gbc);

        gbc.gridx = 0; gbc.gridy = 10;
        editUserPanel.add(passwordField, gbc);

        // ===================== Update Button =====================

        gbc.gridx = 0; gbc.gridy = 11;
        gbc.insets = buttonInset;
        editUserPanel.add(updateButton, gbc);

        // ====================== Back Button ======================

        gbc.gridx = 0; gbc.gridy = 13;
        gbc.insets = buttonInset;
        editUserPanel.add(backButton, gbc);
        
        // ========================== Pack =========================

        getContentPane().add(editUserPanel);
        
        setMinimumSize(getSize());
        pack();
        setMinimumSize(null);
    }

    public void showError(String error) {
        JOptionPane.showMessageDialog(
            editUserPanel, 
            error, 
            "Error", 
            JOptionPane.ERROR_MESSAGE
        );
    }

    public void showMessage(String message) {
        JOptionPane.showMessageDialog(
            editUserPanel, 
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
