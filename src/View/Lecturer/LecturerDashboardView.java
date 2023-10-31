package View.Lecturer;

import Model.Lecturer;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;


public class LecturerDashboardView extends JFrame {

// =====================================================================================
//                                    Attributes
// =====================================================================================

    Lecturer lecturer;

    JPanel lecturerDashboardPanel;
    
    private JButton logoutButton;
    private JButton projectManagerButton;
    private JButton studentManagerButton;

// =====================================================================================
//                                    Constructors
// =====================================================================================

    public LecturerDashboardView(Lecturer lecturer) {
        this.lecturer = lecturer;
        initComponent();
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

    public void initComponent() {

        // ========================== Frame ==========================
        
        setTitle("Lecturer Dashboard");
        setSize(1000, 500);
        setResizable(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        // ========================== Panels ==========================
        
        lecturerDashboardPanel = new JPanel();
        
        // ========================== Labels ==========================

        JLabel titleLabel = new JLabel(
            "Hello, " + 
            lecturer.getFirstname() +
            "!"
        );

        // ========================== Buttons ==========================
        
        logoutButton = new JButton("Logout");

        projectManagerButton = new JButton("Project Manager");
        studentManagerButton = new JButton("Student Manager");

        // ========================== Insets ==========================
        
        Insets fieldsInset = new Insets(0, 0, 10, 0);
        Insets buttonInset = new Insets(20, 10, 0, 10);

        // ========================== Layout ==========================
        
        lecturerDashboardPanel.setLayout(new GridBagLayout());
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = fieldsInset;
        gbc.fill = GridBagConstraints.NONE;

        // ======================== Title =========================

        gbc.gridx = 0; gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.LINE_START;

        lecturerDashboardPanel.add(titleLabel, gbc);

        // ====================== Manage Users ======================

        gbc.gridx = 1; gbc.gridy = 1;
        gbc.insets = buttonInset;

        lecturerDashboardPanel.add(projectManagerButton, gbc);

        // ====================== Manage Projects ======================

        gbc.gridx = 1; gbc.gridy = 2;
        gbc.insets = buttonInset;

        lecturerDashboardPanel.add(studentManagerButton, gbc);

        // ========================= Logout ========================

        gbc.gridx = 2; gbc.gridy = 0;
        gbc.insets = buttonInset;

        lecturerDashboardPanel.add(logoutButton, gbc);
        
        // ========================== Pack =========================

        getContentPane().add(lecturerDashboardPanel);
        
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

    public void projectManager(ActionListener actionListener) {
        projectManagerButton.addActionListener(actionListener);
    }

    public void studentManager(ActionListener actionListener) {
        studentManagerButton.addActionListener(actionListener);
    }

}