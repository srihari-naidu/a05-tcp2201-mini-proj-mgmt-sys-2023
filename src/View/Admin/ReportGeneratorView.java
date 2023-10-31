package View.Admin;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;


public class ReportGeneratorView extends JFrame {
    
// ======================================================================\===============
//                                    Attributes
// =====================================================================================

    private JPanel reportGeneratorPanel;
    
    private JComboBox<String> queryCbo;

    private JTable projectsTable;
    private DefaultTableModel projectsTableModel;
    private TableRowSorter<DefaultTableModel> projectsTableSorter;

    private JButton filterButton;
    private JButton backButton;

// =====================================================================================
//                                    Constructors
// =====================================================================================

    /**
     * Initialization of components.
     */
    public ReportGeneratorView() {
        initComponent();
    }

// =====================================================================================
//                                    Getters
// =====================================================================================

    public JTable getProjectsTable() {
        return projectsTable;
    }

    public DefaultTableModel getProjectsTableModel() {
        return projectsTableModel;
    }

    public TableRowSorter<DefaultTableModel> getProjectsTableSorter() {
        return projectsTableSorter;
    }

    public JComboBox<String> getQueryCbo() {
        return queryCbo;
    }

// =====================================================================================
//                                    Methods
// =====================================================================================

    public void initComponent() {

        // ========================== Frame ==========================

        setTitle("Report Generator");
        setSize(1000, 500);
        setResizable(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // ========================== Panels ==========================

        reportGeneratorPanel = getBasePane();
        getContentPane().add(reportGeneratorPanel);

        // ========================== Pack ==========================

        setMinimumSize(getSize());
        pack();
        setMinimumSize(null);
    }

    public void showError(String error) {
        JOptionPane.showMessageDialog(
            reportGeneratorPanel, 
            error, 
            "Error", 
            JOptionPane.ERROR_MESSAGE
        );
    }

    public void showMessage(String message) {
        JOptionPane.showMessageDialog(
            reportGeneratorPanel, 
            message
        );
    }

    private JPanel getBasePane() {

        // ========================== Layout ==========================

        JPanel basePane = new JPanel();
        basePane.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        
        // ========================== ComboBox ==========================

        gbc.gridx = 0;   gbc.gridy = 0;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1.0;
        gbc.weighty = 0.20;
        basePane.add(getComboBoxPane(), gbc);

        // ========================== Table ==========================

        gbc.gridx = 0;   gbc.gridy = 1;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1.0;
        gbc.weighty = 0.60;
        basePane.add(getTablePane(), gbc);

        // ========================== Button ==========================

        gbc.gridx = 0;   gbc.gridy = 2;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1.0;
        gbc.weighty = 0.20;
        basePane.add(getButtonPane(), gbc);

        return basePane;
    }

    private JPanel getComboBoxPane() {

        // ========================== Layout ==========================

        JPanel comboBoxPane = new JPanel(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0; gbc.gridy = 0;
        gbc.weightx = 0.80;
        gbc.weighty = 1.00;

        queryCbo = new JComboBox<String>();
        comboBoxPane.add(queryCbo, gbc);
        
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0; gbc.gridy = 1;
        gbc.weightx = 0.80;
        gbc.weighty = 1.00;

        filterButton = new JButton("Filter");
        comboBoxPane.add(filterButton, gbc);

        return comboBoxPane;
    }

    private JPanel getTablePane() {

        // ========================== Layout ==========================

        JPanel tablePane = new JPanel(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 0.80;
        gbc.weighty = 0.80;

        projectsTable = new JTable();
        projectsTableModel = new DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Project ID", "Title", "Specialization", "State", "Status", "Creator", "Commented"
            }
        );
        
        projectsTable.setModel(projectsTableModel);
        projectsTable.setDefaultEditor(Object.class, null);
        
        projectsTableSorter = new TableRowSorter<DefaultTableModel>(projectsTableModel);
        projectsTable.setRowSorter(projectsTableSorter);

        tablePane.add(new JScrollPane(projectsTable), gbc);
        
        return tablePane;
    }

    private JPanel getButtonPane() {

        // ========================== Layout ==========================

        JPanel tablePane = new JPanel(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();

        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 0.80;
        gbc.weighty = 0.80;

        backButton = new JButton("Back");
        tablePane.add(backButton, gbc);
        
        return tablePane;
    }
    
// =====================================================================================
//                                 Event Functions
// =====================================================================================

    public void filter(ActionListener actionListener) {
        filterButton.addActionListener(actionListener);
    }

    public void back(ActionListener actionListener) {
        backButton.addActionListener(actionListener);
    }
}