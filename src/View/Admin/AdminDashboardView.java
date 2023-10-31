package View.Admin;

import Model.Admin;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;


public class AdminDashboardView extends JFrame {



// =====================================================================================
//                                    Attributes
// =====================================================================================

    JPanel adminDashboardPanel;
    
    private JButton logoutButton;
    private JButton userManagerButton;
    private JButton projectManagerButton;
    private JButton reportGeneratorButton;

// =====================================================================================
//                                    Constructors
// =====================================================================================

    public AdminDashboardView(Admin admin) {
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
        
        setTitle("Admin Dashboard");
        setSize(1000, 500);
        setResizable(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        // ========================== Panels ==========================
        
        adminDashboardPanel = new JPanel();
        
        // ========================== Labels ==========================

        JLabel titleLabel = new JLabel(
            "Hello, " + 
            admin.getFirstname() +
            "!"
        );

        // ========================== Buttons ==========================
        
        logoutButton = new JButton("Logout");

        userManagerButton = new JButton("User Manager");
        projectManagerButton = new JButton("Project Manager");
        reportGeneratorButton = new JButton("Report Generator");

        // ========================== Insets ==========================
        
        Insets fieldsInset = new Insets(0, 0, 10, 0);
        Insets buttonInset = new Insets(20, 10, 0, 10);

        // ========================== Layout ==========================
        
        adminDashboardPanel.setLayout(new GridBagLayout());
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = fieldsInset;
        gbc.fill = GridBagConstraints.NONE;

        // ======================== Title =========================

        gbc.gridx = 0; gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.LINE_START;

        adminDashboardPanel.add(titleLabel, gbc);

        // ====================== Manage Users ======================

        gbc.gridx = 1; gbc.gridy = 1;
        gbc.insets = buttonInset;

        adminDashboardPanel.add(userManagerButton, gbc);

        // ====================== Manage Projects ======================

        gbc.gridx = 1; gbc.gridy = 2;
        gbc.insets = buttonInset;

        adminDashboardPanel.add(projectManagerButton, gbc);

        // // ======================  Reports ======================

        gbc.gridx = 1; gbc.gridy = 3;
        gbc.insets = buttonInset;
        adminDashboardPanel.add(reportGeneratorButton, gbc);

        // ========================= Logout ========================

        gbc.gridx = 2; gbc.gridy = 0;
        gbc.insets = buttonInset;

        adminDashboardPanel.add(logoutButton, gbc);
        
        // ========================== Pack =========================

        getContentPane().add(adminDashboardPanel);
        
        setMinimumSize(getSize());
        pack();
        setMinimumSize(null);
    }


// =====================================================================================
//                                 Button Functions
// =====================================================================================

    public void logout(ActionListener actionListener) {
        logoutButton.addActionListener(actionListener);
    }

    public void userManager(ActionListener actionListener) {
        userManagerButton.addActionListener(actionListener);
    }

    public void projectManager(ActionListener actionListener) {
        projectManagerButton.addActionListener(actionListener);
    }

    public void reportGenerator(ActionListener actionListener) {
        reportGeneratorButton.addActionListener(actionListener);
    }

}