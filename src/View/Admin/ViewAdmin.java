package View.Admin;

import java.awt.*;
import javax.swing.*;

import Model.Admin;
import Model.Project;

public class ViewAdmin extends JFrame {
    
// =====================================================================================
//                                    Attributes
// =====================================================================================

    private Admin admin;
    
    JPanel viewAdminPanel;

// =====================================================================================
//                                    Constructors
// =====================================================================================

    public ViewAdmin(Admin admin) {
        this.admin = admin;
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

    private void initComponent() {

        // ========================== Frame ==========================
        
        setTitle("View Admin");
        setSize(1000, 500);
        setResizable(true);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        
        // ========================== Panels ==========================
        
        viewAdminPanel = new JPanel();

        // ========================== Labels ==========================

        JLabel typeLabel = new JLabel("Admin");
        typeLabel.setFont(new Font("Arial", Font.BOLD, 30));

        JLabel nameLabel = new JLabel("Name");
        JLabel name = new JLabel(admin.getName());
        name.setFont(new Font("Arial", Font.BOLD, 16));
        
        JLabel projectsLabel = new JLabel("Projects");
        DefaultListModel<String> projectListModel = new DefaultListModel<>();  
        
        for (Project project : admin.getProjects()) {
            projectListModel.addElement(project.getTitle());
        }

        JList<String> projectList = new JList<String>(projectListModel);
        projectList.setFont(new Font("Arial",Font.BOLD,16));

        // ========================== Insets ==========================

        Insets labelsInset = new Insets(20, 0, 0, 0);
        Insets contentInset = new Insets(0, 0, 0, 0);

        // ========================== Layout ==========================
        
        viewAdminPanel.setLayout(new GridBagLayout());
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(20, 0, 0, 0);
        gbc.fill = GridBagConstraints.NONE;

        // ======================== Type =========================

        gbc.gridx = 0; gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.CENTER;
        viewAdminPanel.add(typeLabel, gbc);

        // ======================== Name =========================

        gbc.gridx = 0; gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = labelsInset;
        viewAdminPanel.add(nameLabel, gbc);

        gbc.gridx = 0; gbc.gridy = 3;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = contentInset;
        viewAdminPanel.add(name, gbc);

        // ======================== Projects =========================

        gbc.gridx = 0; gbc.gridy = 5;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = labelsInset;
        viewAdminPanel.add(projectsLabel, gbc);

        gbc.gridx = 0; gbc.gridy = 6;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = contentInset;
        viewAdminPanel.add(new JScrollPane(projectList), gbc);

        // ========================== Pack =========================

        getContentPane().add(viewAdminPanel);
        
        setMinimumSize(getSize());
        pack();
        setMinimumSize(null);

        setVisible(true);
    }
}
