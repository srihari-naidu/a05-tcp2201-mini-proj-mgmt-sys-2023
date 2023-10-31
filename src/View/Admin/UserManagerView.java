package View.Admin;

import java.awt.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.*;


public class UserManagerView extends JFrame {
    
// ======================================================================\===============
//                                    Attributes
// =====================================================================================

    JPanel userManagerPanel;
    
    private JTable usersTable;
    private DefaultTableModel usersTableModel;

    private JButton viewButton;
    private JButton editButton;
    private JButton deleteButton;
    private JButton registerUserButton;
    private JButton backButton;

// =====================================================================================
//                                    Constructors
// =====================================================================================

    public UserManagerView() {
        initComponent();
    }

// =====================================================================================
//                                    Getters
// =====================================================================================

    public JTable getUsersTable() {
        return usersTable;
    }

    public DefaultTableModel getUsersTableModel() {
        return usersTableModel;
    }

// =====================================================================================
//                                    Setters
// =====================================================================================


// =====================================================================================
//                                    Methods
// =====================================================================================

    public void initComponent() {

        // ========================== Frame ==========================

        setTitle("User Manager");
        setSize(1000, 500);
        setResizable(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // ========================== Panels ==========================

        userManagerPanel = getBasePane();
        getContentPane().add(userManagerPanel);

        // ========================== Pack ==========================

        setMinimumSize(getSize());
        pack();
        setMinimumSize(null);
    }

    public void showError(String error) {
        JOptionPane.showMessageDialog(
            userManagerPanel, 
            error, 
            "Error", 
            JOptionPane.ERROR_MESSAGE
        );
    }

    public void showMessage(String message) {
        JOptionPane.showMessageDialog(
            userManagerPanel, 
            message
        );
    }

    private JPanel getBasePane() {

        // ========================== Layout ==========================

        JPanel basePane = new JPanel();
        basePane.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        
        // ========================== Panels ==========================

        gbc.gridx = 0;   gbc.gridy = 0;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 0.85;
        gbc.weighty = 1.0;
        basePane.add(getTablePane(), gbc);

        // ========================== Buttons ==========================

        gbc.gridx = 1;   gbc.gridy = 0;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 0.15;
        gbc.weighty = 1.0;
        basePane.add(getButtonPane(), gbc);

        return basePane;
    }

    private JPanel getTablePane() {

        // ========================== Layout ==========================

        JPanel tablePane = new JPanel(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();

        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;

        usersTable = new JTable();
        usersTableModel = new DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Type", "Firstname", "Lastname"
            }
        );
        
        usersTable.setModel(usersTableModel);
        usersTable.setDefaultEditor(Object.class, null);
        tablePane.add(new JScrollPane(usersTable), gbc);
        
        return tablePane;
    }

    private JPanel getButtonPane() {
        
        // ========================== Layout ==========================

        JPanel buttonPane = new JPanel(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(20, 20, 20, 20);

        // ======================= View Button =======================

        gbc.gridx = 0;   gbc.gridy = 0;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 0.5;
        viewButton = new JButton("View");

        buttonPane.add(viewButton, gbc);

        // ======================= Edit Button =======================

        gbc.gridx = 0;   gbc.gridy = 1;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 0.5;
        editButton = new JButton("Edit");
        buttonPane.add(editButton, gbc);

        // ====================== Delete Button ======================

        gbc.gridx = 0;   gbc.gridy = 2;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 0.5;
        deleteButton = new JButton("Delete");
        buttonPane.add(deleteButton, gbc);

        gbc.gridx = 0;   gbc.gridy = 3;
        gbc.anchor = GridBagConstraints.CENTER;
        buttonPane.add(new JLabel(""), gbc);

        // ================== Register Users Button ==================

        gbc.gridx = 0;   gbc.gridy = 4;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 0.5;
        registerUserButton = new JButton("Register New User");
        buttonPane.add(registerUserButton, gbc);

        // ======================= Back Button =======================

        gbc.gridx = 0;   gbc.gridy = 5;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 0.5;
        backButton = new JButton("Back");
        buttonPane.add(backButton, gbc);

        return buttonPane;
    }
    
// =====================================================================================
//                                 Button Functions
// =====================================================================================

    public void viewUser(ActionListener actionListener) {
        viewButton.addActionListener(actionListener);
    }

    public void editUser(ActionListener actionListener) {
        editButton.addActionListener(actionListener);
    }

    public void deleteUser(ActionListener actionListener) {
        deleteButton.addActionListener(actionListener);
    }

    public void registerNewUser(ActionListener actionListener) {
        registerUserButton.addActionListener(actionListener);
    }

    public void back(ActionListener actionListener) {
        backButton.addActionListener(actionListener);
    }

}
