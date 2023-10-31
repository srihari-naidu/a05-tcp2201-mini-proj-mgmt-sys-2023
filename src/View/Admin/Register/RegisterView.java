package View.Admin.Register;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public abstract class RegisterView extends JFrame {
    
// =====================================================================================
//                                    Attributes
// =====================================================================================

    protected JPanel registerPanel;

    protected JTextField firstnameField;
    protected JTextField lastnameField;
    protected JTextField usernameField;
    protected JTextField passwordField;

    protected JTextField specializationField;
    protected JComboBox<String> supervisorCbo;

    protected Insets fieldsInset;
    protected Insets buttonInset;

    protected JButton registerButton;
    protected JButton backButton;

// =====================================================================================
//                                    Constructors
// =====================================================================================

    public RegisterView() {
        initComponent();
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

    public String getSpecialization() {
        return specializationField.getText();
    }

    public JComboBox<String> getSupervisorCbo() {
        return supervisorCbo;
    }

// =====================================================================================
//                                    Methods
// =====================================================================================

    public abstract void initComponent();
    
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

    public void register(ActionListener actionListener) {
        registerButton.addActionListener(actionListener);
    }

    public void back(ActionListener actionListener) {
        backButton.addActionListener(actionListener);
    }

    public abstract void clearFields();
}
