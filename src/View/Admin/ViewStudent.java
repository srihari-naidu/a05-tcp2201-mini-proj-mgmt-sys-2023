package View.Admin;

import java.awt.*;
import javax.swing.*;

import Model.Student;
import Model.Database;

public class ViewStudent extends JFrame {
    
// =====================================================================================
//                                    Attributes
// =====================================================================================

    private Student student;
    private Database database;
    
    JPanel viewStudentPanel;

// =====================================================================================
//                                    Constructors
// =====================================================================================

    public ViewStudent(Student student) {
        this.student = student;
        this.database = new Database();
        initComponent();
    }

// =====================================================================================
//                                    Methods
// =====================================================================================

    private void initComponent() {

        // ========================== Frame ==========================
        
        setTitle("View Student");
        setSize(1000, 500);
        setResizable(true);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        
        // ========================== Panels ==========================
        
        viewStudentPanel = new JPanel();

        // ========================== Labels ==========================

        JLabel typeLabel = new JLabel("Student");
        typeLabel.setFont(new Font("Arial", Font.BOLD, 30));

        JLabel nameLabel = new JLabel("Name");
        JLabel name = new JLabel(student.getName());
        name.setFont(new Font("Arial", Font.BOLD, 20));

        JLabel specializationLabel = new JLabel("Specialization");
        JLabel specialization = new JLabel(student.getSpecialization());
        specialization.setFont(new Font("Arial", Font.BOLD, 20));
        
        JLabel supervisorLabel = new JLabel("Supervisor");
        JLabel supervisor = new JLabel(database.getLecturerById(student.getSupervisorId()).getName());
        supervisor.setFont(new Font("Arial", Font.BOLD, 20));
        
        JLabel projectsLabel = new JLabel("Project");
        JLabel project = new JLabel();
        project.setFont(new Font("Arial", Font.BOLD, 20));
        if (student.getProjectId() != 0) {
            project.setText(database.getProjectById(student.getProjectId()).getTitle());
        }

        // ========================== Insets ==========================
        
        Insets labelsInset = new Insets(20, 0, 0, 0);
        Insets contentInset = new Insets(0, 0, 0, 0);

        // ========================== Layout ==========================
        
        viewStudentPanel.setLayout(new GridBagLayout());
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.NONE;

        // ======================== Type =========================

        gbc.gridx = 0; gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.CENTER;
        viewStudentPanel.add(typeLabel, gbc);

        // ======================== Name =========================

        gbc.gridx = 0; gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = labelsInset;
        viewStudentPanel.add(nameLabel, gbc);
        
        gbc.gridx = 0; gbc.gridy = 3;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = contentInset;
        viewStudentPanel.add(name, gbc);

        // ======================== Specialization =========================

        gbc.gridx = 0; gbc.gridy = 5;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = labelsInset;
        viewStudentPanel.add(specializationLabel, gbc);

        gbc.gridx = 0; gbc.gridy = 6;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = contentInset;
        viewStudentPanel.add(specialization, gbc);

        // ======================== Supervisor =========================

        gbc.gridx = 0; gbc.gridy = 8;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = labelsInset;
        viewStudentPanel.add(supervisorLabel, gbc);

        gbc.gridx = 0; gbc.gridy = 9;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = contentInset;
        viewStudentPanel.add(supervisor, gbc);

        // ======================== Projects =========================

        gbc.gridx = 0; gbc.gridy = 11;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = labelsInset;
        viewStudentPanel.add(projectsLabel, gbc);

        gbc.gridx = 0; gbc.gridy = 12;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = contentInset;
        viewStudentPanel.add(project, gbc);

        // ========================== Pack =========================

        getContentPane().add(viewStudentPanel);
        
        setMinimumSize(getSize());
        pack();
        setMinimumSize(null);

        setVisible(true);
    }
}
