package View;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class LoginView extends JFrame {
    
// =====================================================================================
//                                    Attributes
// =====================================================================================

    JPanel loginPanel;

    private JTextField usernameField;
    private JTextField passwordField;

    private JButton loginButton;

// =====================================================================================
//                                    Constructors
// =====================================================================================

    public LoginView() {
        initComponent();
    }
    
// =====================================================================================
//                                    Getters
// =====================================================================================

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

    private void initComponent() {

        // ========================== Frame ==========================
        
        setTitle("Login");
        setSize(1000, 500);
        setResizable(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        // ========================== Panels ==========================
        
        JPanel loginPanel = new JPanel();

        // ========================== Labels ==========================

        JLabel usernameLabel = new JLabel("Username: ");
        JLabel passwordLabel = new JLabel("Password: ");

        // ========================== Fields ==========================

        usernameField = new JTextField(25);
        passwordField = new JTextField(25);

        // ========================== Buttons ==========================

        loginButton = new JButton("Login");
        
        // ========================== Insets ==========================
        
        Insets fieldsInset = new Insets(0, 0, 10, 0);
        Insets buttonInset = new Insets(20, 0, 0, 0);

        // ========================== Layout ==========================
        
        loginPanel.setLayout(new GridBagLayout());
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = fieldsInset;
        gbc.fill = GridBagConstraints.NONE;

        // ======================== Username =========================

        gbc.gridx = 0; gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        loginPanel.add(usernameLabel, gbc);

        gbc.gridx = 0; gbc.gridy = 1;
        loginPanel.add(usernameField, gbc);

        // ======================== Password =========================

        gbc.gridx = 0; gbc.gridy = 3;
        gbc.anchor = GridBagConstraints.WEST;
        loginPanel.add(passwordLabel, gbc);

        gbc.gridx = 0; gbc.gridy = 4;
        loginPanel.add(passwordField, gbc);

        // ======================== Login =========================

        gbc.gridx = 0; gbc.gridy = 5;
        gbc.insets = buttonInset;
        loginPanel.add(loginButton, gbc);

        // ========================== Pack =========================

        getContentPane().add(loginPanel);
        
        setMinimumSize(getSize());
        pack();
        setMinimumSize(null);
    }
    

    public void showError(String error) {
        JOptionPane.showMessageDialog(
            loginPanel, 
            error, 
            "Error", 
            JOptionPane.ERROR_MESSAGE
        );
    }

// =====================================================================================
//                                 Button Functions
// =====================================================================================

    public void login(ActionListener actionListener) {
        loginButton.addActionListener(actionListener);
    }

}
