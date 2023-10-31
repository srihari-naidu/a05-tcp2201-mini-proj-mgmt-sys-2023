package View.Admin;

import java.awt.*;
import javax.swing.*;

import Model.Lecturer;
import Model.Project;

public class ViewLecturer extends JFrame {
    
// =====================================================================================
//                                    Attributes
// =====================================================================================

    private Lecturer lecturer;
    
    JPanel viewLecturerPanel;

// =====================================================================================
//                                    Constructors
// =====================================================================================

    public ViewLecturer(Lecturer lecturer) {
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

    private void initComponent() {

        // ========================== Frame ==========================
        
        setTitle("View Lecturer");
        setSize(1000, 500);
        setResizable(true);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        
        // ========================== Panels ==========================
        
        viewLecturerPanel = new JPanel();

        // ========================== Labels ==========================

        JLabel typeLabel = new JLabel("Lecturer");
        typeLabel.setFont(new Font("Arial", Font.BOLD, 30));

        JLabel nameLabel = new JLabel("Name");
        JLabel name = new JLabel(lecturer.getName());
        name.setFont(new Font("Arial", Font.BOLD, 20));
        
        JLabel projectsLabel = new JLabel("Projects");
        DefaultListModel<String> projectListModel = new DefaultListModel<>();  
        
        for (Project project : lecturer.getProjects()) {
            projectListModel.addElement(project.getTitle());
        }

        JList<String> projectList = new JList<String>(projectListModel);
        projectList.setFont(new Font("Arial",Font.BOLD,20));

        // ========================== Insets ==========================

        Insets labelsInset = new Insets(20, 0, 0, 0);
        Insets contentInset = new Insets(0, 0, 0, 0);

        // ========================== Layout ==========================
        
        viewLecturerPanel.setLayout(new GridBagLayout());
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(20, 0, 0, 0);
        gbc.fill = GridBagConstraints.NONE;

        // ======================== Type =========================

        gbc.gridx = 0; gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.CENTER;
        viewLecturerPanel.add(typeLabel, gbc);

        // ======================== Name =========================

        gbc.gridx = 0; gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = labelsInset;
        viewLecturerPanel.add(nameLabel, gbc);
        
        gbc.gridx = 0; gbc.gridy = 3;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = contentInset;
        viewLecturerPanel.add(name, gbc);

        // ======================== Projects =========================

        gbc.gridx = 0; gbc.gridy = 5;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = labelsInset;
        viewLecturerPanel.add(projectsLabel, gbc);

        gbc.gridx = 0; gbc.gridy = 6;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = contentInset;
        viewLecturerPanel.add(new JScrollPane(projectList), gbc);

        // ========================== Pack =========================

        getContentPane().add(viewLecturerPanel);
        
        setMinimumSize(getSize());
        pack();
        setMinimumSize(null);

        setVisible(true);
    }
}
