package View.Student;

import java.awt.*;
import javax.swing.*;

import Model.Database;
import Model.Project;

public class ViewProject extends JFrame {
    
// =====================================================================================
//                                    Attributes
// =====================================================================================

    private Project project;
    private Database database;

    JPanel viewProjectPanel;

// =====================================================================================
//                                    Constructors
// =====================================================================================

    public ViewProject(Project project) {
        this.project = project;
        this.database = new Database();
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
        
        setTitle("View Project");
        setSize(1000, 500);
        setResizable(true);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        
        // ========================== Panels ==========================
        
        viewProjectPanel = new JPanel();

        // ========================== Labels ==========================

        JLabel title = new JLabel(project.getTitle());
        title.setFont(new Font("Arial", Font.BOLD, 30));

        JLabel specialization = new JLabel(
            "Specialization:" + project.getSpecialization()
        );
        specialization.setFont(new Font("Monospaced", Font.BOLD, 20));
        
        JTextArea descriptionArea = new JTextArea(project.getDescription());
        descriptionArea.setFont(new Font("Arial", Font.PLAIN, 20));
        
        JLabel creator = new JLabel(
            "Created by: " + database.getUserById(project.getCreatorId()).getName()
        );
        creator.setFont(new Font("Monospaced", Font.BOLD, 20));
        
        // ========================== Insets ==========================
        
        Insets labelsInset = new Insets(20, 0, 0, 0);

        // ========================== Layout ==========================
        
        viewProjectPanel.setLayout(new GridBagLayout());
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = labelsInset;
        gbc.fill = GridBagConstraints.NONE;

        // ======================== Name =========================

        gbc.gridx = 0; gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        viewProjectPanel.add(title, gbc);

        // ======================== Specialization =========================

        gbc.gridx = 0; gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.WEST;
        viewProjectPanel.add(specialization, gbc);

        // ======================== Supervisor =========================

        gbc.gridx = 0; gbc.gridy = 4;
        gbc.anchor = GridBagConstraints.WEST;
        viewProjectPanel.add(new JScrollPane(descriptionArea), gbc);

        // ======================== Creator =========================

        gbc.gridx = 0; gbc.gridy = 6;
        gbc.anchor = GridBagConstraints.WEST;
        viewProjectPanel.add(creator, gbc);

        // ========================== Pack =========================

        getContentPane().add(viewProjectPanel);
        
        setMinimumSize(getSize());
        pack();
        setMinimumSize(null);

        setVisible(true);
    }
}
