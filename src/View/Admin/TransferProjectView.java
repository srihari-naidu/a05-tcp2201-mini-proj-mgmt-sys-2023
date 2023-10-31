package View.Admin;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class TransferProjectView extends JFrame {
    
// =====================================================================================
//                                    Attributes
// =====================================================================================

    JPanel transferProjectPanel;
    
    private JComboBox<String> lecturerCbo;

    private JButton transferButton;
    private JButton backButton;

// =====================================================================================
//                                    Constructors
// =====================================================================================

    public TransferProjectView() {
        initComponent();
    }

// =====================================================================================
//                                    Getters
// =====================================================================================

    public JComboBox<String> getLecturerCbo() {
        return lecturerCbo;
    }

// =====================================================================================
//                                    Methods
// =====================================================================================

    public void initComponent() {

        // ========================== Frame ==========================

        setTitle("Transfer Project");
        setSize(1000, 500);
        setResizable(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        // ========================== Panels ==========================
        
        transferProjectPanel = new JPanel();
        
        // ========================== Labels ==========================

        JLabel lecturerLabel = new JLabel("Lecturer: ");

        // ========================== Fields ==========================

        lecturerCbo = new JComboBox<>();

        // ========================== Buttons ==========================

        transferButton = new JButton("Transfer");
        backButton = new JButton("Back");

        // ========================== Insets ==========================
        
        Insets fieldsInset = new Insets(0, 0, 10, 0);
        Insets buttonInset = new Insets(20, 0, 0, 0);

        // ========================== Layout ==========================

        transferProjectPanel.setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = fieldsInset;
        gbc.fill = GridBagConstraints.NONE;
        
        // ======================== Lecturer =========================

        gbc.gridx = 0; gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        transferProjectPanel.add(lecturerLabel, gbc);

        gbc.gridx = 0; gbc.gridy = 1;
        gbc.ipadx = 110;
        transferProjectPanel.add(lecturerCbo, gbc);

        // ================== Transfer Button ==================

        gbc.gridx = 0; gbc.gridy = 3;
        gbc.insets = buttonInset;
        transferProjectPanel.add(transferButton, gbc);

        // ====================== Back Button ======================

        gbc.gridx = 0; gbc.gridy = 4;
        gbc.insets = buttonInset;
        transferProjectPanel.add(backButton, gbc);
        
        // ========================== Pack =========================

        getContentPane().add(transferProjectPanel);
        
        setMinimumSize(getSize());
        pack();
        setMinimumSize(null);
    }
    
    public void showError(String error) {
        JOptionPane.showMessageDialog(
            transferProjectPanel, 
            error, 
            "Error", 
            JOptionPane.ERROR_MESSAGE
        );
    }

    public void showMessage(String message) {
        JOptionPane.showMessageDialog(
            transferProjectPanel, 
            message
        );
    }

// =====================================================================================
//                                 Button Functions
// =====================================================================================

    public void transferProject(ActionListener actionListener) {
        transferButton.addActionListener(actionListener);
    }

    public void back(ActionListener actionListener) {
        backButton.addActionListener(actionListener);
    }
}
