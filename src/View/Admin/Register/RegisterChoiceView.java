package View.Admin.Register;

import Model.Admin;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;


public class RegisterChoiceView extends JFrame {
    
// =====================================================================================
//                                    Attributes
// =====================================================================================

    JPanel registerPanel;
    
    private JButton cancelButton;
    private JButton registerAdminButton;
    private JButton registerLecturerButton;
    private JButton registerStudentButton;

// =====================================================================================
//                                    Constructors
// =====================================================================================

    public RegisterChoiceView(Admin admin) {
        initComponent(admin);
    }

// =====================================================================================
//                                    Getters
// =====================================================================================


// =====================================================================================
//                                    Setters
// =====================================================================================


// =====================================================================================
//                                    Methods
// =====================================================================================

    public void initComponent(Admin admin) {

        // ========================== Frame ==========================
        
        setTitle("Register");
        setSize(1000, 500);
        setResizable(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        // ========================== Panels ==========================
        
        registerPanel = new JPanel();
        
        // ========================== Labels ==========================

        JLabel titleLabel = new JLabel(
            "Who are you creating the account for?"
        );

        // ========================== Buttons ==========================
        
        cancelButton = new JButton("Cancel");

        registerAdminButton = new JButton("Admin");
        registerLecturerButton = new JButton("Lecturer");
        registerStudentButton = new JButton("Student");

        // ========================== Insets ==========================
        
        Insets fieldsInset = new Insets(0, 0, 10, 0);
        Insets buttonInset = new Insets(20, 10, 0, 10);

        // ========================== Layout ==========================
        
        registerPanel.setLayout(new GridBagLayout());
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = fieldsInset;
        gbc.fill = GridBagConstraints.NONE;

        // ======================== Title =========================

        gbc.gridx = 0; gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.LINE_START;

        registerPanel.add(titleLabel, gbc);

        // ====================== Manage Users ======================

        gbc.gridx = 1; gbc.gridy = 1;
        gbc.insets = buttonInset;

        registerPanel.add(registerAdminButton, gbc);

        // ====================== Manage Projects ======================

        gbc.gridx = 1; gbc.gridy = 2;
        gbc.insets = buttonInset;

        registerPanel.add(registerLecturerButton, gbc);

        // // ======================  Reports ======================

        gbc.gridx = 1; gbc.gridy = 3;
        gbc.insets = buttonInset;
        registerPanel.add(registerStudentButton, gbc);

        // ========================= Logout ========================

        gbc.gridx = 2; gbc.gridy = 0;
        gbc.insets = buttonInset;

        registerPanel.add(cancelButton, gbc);
        
        // ========================== Pack =========================

        getContentPane().add(registerPanel);
        
        setMinimumSize(getSize());
        pack();
        setMinimumSize(null);
    }


// =====================================================================================
//                                 Button Functions
// =====================================================================================

    public void cancel(ActionListener actionListener) {
        cancelButton.addActionListener(actionListener);
    }

    public void registerAdmin(ActionListener actionListener) {
        registerAdminButton.addActionListener(actionListener);
    }

    public void registerLecturer(ActionListener actionListener) {
        registerLecturerButton.addActionListener(actionListener);
    }

    public void registerStudent(ActionListener actionListener) {
        registerStudentButton.addActionListener(actionListener);
    }

}