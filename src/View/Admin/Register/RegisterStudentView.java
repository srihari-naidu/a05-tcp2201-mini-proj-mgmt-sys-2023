package View.Admin.Register;

import java.awt.*;
import javax.swing.*;

public class RegisterStudentView extends RegisterView {

// =====================================================================================
//                                    Constructors
// =====================================================================================

    public RegisterStudentView() {
        super();
    }

// =====================================================================================
//                                    Methods
// =====================================================================================

    public void initComponent() {

        // ========================== Frame ==========================

        setTitle("Register Student");
        setSize(1000, 500);
        setResizable(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        // ========================== Panels ==========================
        
        registerPanel = new JPanel();
        
        // ========================== Labels ==========================

        JLabel firstnameLabel = new JLabel("First Name: ");
        JLabel lastnameLabel = new JLabel("Last Name: ");
        JLabel usernameLabel = new JLabel("Username: ");
        JLabel passwordLabel = new JLabel("Password: ");
        JLabel specializationLabel = new JLabel("Specialization: ");
        JLabel supervisorLabel = new JLabel("Supervisor: ");

        // ========================== Fields ==========================

        firstnameField = new JTextField(25);
        lastnameField = new JTextField(25);
        usernameField = new JTextField(25);
        passwordField = new JTextField(25);
        specializationField = new JTextField(50);

        // ========================== Fields ==========================

        supervisorCbo = new JComboBox<>();

        // ========================== Buttons ==========================

        registerButton = new JButton("Register Student");
        backButton = new JButton("Back");

        // ========================== Insets ==========================
        
        Insets fieldsInset = new Insets(0, 0, 10, 0);
        Insets buttonInset = new Insets(20, 0, 0, 0);

        // ========================== Layout ==========================

        registerPanel.setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = fieldsInset;
        gbc.fill = GridBagConstraints.NONE;

        // ======================== Firstname =========================

        gbc.gridx = 0; gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        registerPanel.add(firstnameLabel, gbc);

        gbc.gridx = 0; gbc.gridy = 1;        
        registerPanel.add(firstnameField, gbc);

        // ======================== Lastname =========================

        gbc.gridx = 0; gbc.gridy = 3;
        gbc.anchor = GridBagConstraints.WEST;
        registerPanel.add(lastnameLabel, gbc);

        gbc.gridx = 0; gbc.gridy = 4;
        registerPanel.add(lastnameField, gbc);

        // ======================== Username =========================

        gbc.gridx = 0; gbc.gridy = 6;
        gbc.anchor = GridBagConstraints.WEST;
        registerPanel.add(usernameLabel, gbc);

        gbc.gridx = 0; gbc.gridy = 7;
        registerPanel.add(usernameField, gbc);

        // ======================== Password =========================

        gbc.gridx = 0; gbc.gridy = 9;
        gbc.anchor = GridBagConstraints.WEST;
        registerPanel.add(passwordLabel, gbc);

        gbc.gridx = 0; gbc.gridy = 10;
        registerPanel.add(passwordField, gbc);

        // ======================== Specialization =========================

        gbc.gridx = 0; gbc.gridy = 12;
        gbc.anchor = GridBagConstraints.WEST;
        registerPanel.add(specializationLabel, gbc);

        gbc.gridx = 0; gbc.gridy = 13;
        registerPanel.add(specializationField, gbc);
        
        // ======================== Supervisor =========================

        gbc.gridx = 0; gbc.gridy = 14;
        gbc.anchor = GridBagConstraints.WEST;
        registerPanel.add(supervisorLabel, gbc);

        gbc.gridx = 0; gbc.gridy = 15;
        gbc.ipadx = 110;
        registerPanel.add(supervisorCbo, gbc);

        // ================== Create Account Button ==================

        gbc.gridx = 0; gbc.gridy = 17;
        gbc.insets = buttonInset;
        registerPanel.add(registerButton, gbc);

        // ====================== Back Button ======================

        gbc.gridx = 0; gbc.gridy = 18;
        gbc.insets = buttonInset;
        registerPanel.add(backButton, gbc);
        
        // ========================== Pack =========================

        getContentPane().add(registerPanel);
        
        setMinimumSize(getSize());
        pack();
        setMinimumSize(null);
    }
    
    public void showError(String error) {
        JOptionPane.showMessageDialog(
            registerPanel, 
            error, 
            "Error", 
            JOptionPane.ERROR_MESSAGE
        );
    }

    public void showMessage(String message) {
        JOptionPane.showMessageDialog(
            registerPanel, 
            message
        );
    }

// =====================================================================================
//                                 Button Functions
// =====================================================================================

    public void clearFields() {
        firstnameField.setText("");
        lastnameField.setText("");
        usernameField.setText("");
        passwordField.setText("");
        specializationField.setText("");
    }
}