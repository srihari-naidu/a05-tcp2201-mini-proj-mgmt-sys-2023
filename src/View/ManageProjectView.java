package View;

import java.awt.*;
import javax.swing.*;
import Model.Database;
import Model.Project;
import Model.Comment;

import java.awt.event.*;


public class ManageProjectView extends JFrame {
    
// ======================================================================\===============
//                                    Attributes
// =====================================================================================

    private Project project;
    private Database database;
    
    private JPanel manageProjectPanel;

    private JTextField commentField;
    private JButton commentButton;
    private JButton backButton;

// =====================================================================================
//                                    Constructors
// =====================================================================================

    /**
     * Initialization of project session, database, and components.
     */
    public ManageProjectView(Project project) {
        this.project = project;
        this.database = new Database();
        initComponent();
    }

// =====================================================================================
//                                    Getters
// =====================================================================================

    public String getComment() {
        return commentField.getText();
    }

// =====================================================================================
//                                    Methods
// =====================================================================================

    public void initComponent() {

        // ========================== Frame ==========================

        setTitle("Manage Project");
        setSize(1000, 500);
        setResizable(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // ========================== Panels ==========================

        manageProjectPanel = getBasePane();
        getContentPane().add(manageProjectPanel);

        // ========================== Pack ==========================

        setMinimumSize(getSize());
        pack();
        setMinimumSize(null);
    }

    public void showError(String error) {
        JOptionPane.showMessageDialog(
            manageProjectPanel, 
            error, 
            "Error", 
            JOptionPane.ERROR_MESSAGE
        );
    }

    public void showMessage(String message) {
        JOptionPane.showMessageDialog(
            manageProjectPanel, 
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
        gbc.weightx = 0.70;
        gbc.weighty = 1.0;
        basePane.add(getProjectInfoPane(), gbc);

        gbc.gridx = 1;   gbc.gridy = 0;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 0.30;
        gbc.weighty = 0.70;
        basePane.add(getCommentsPane(), gbc);

        gbc.gridx = 1;   gbc.gridy = 1;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 0.30;
        gbc.weighty = 0.30;
        basePane.add(getCommentPane(), gbc);

        return basePane;
    }

    private JPanel getProjectInfoPane() {

        // ========================== Pane ==========================

        JPanel projectInfoPane = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        // ========================== Title ==========================
        
        JLabel title = new JLabel(project.getTitle());
        title.setFont(new Font("Arial", Font.BOLD, 30));
        
        // ========================== Specialization ==========================
        
        JLabel specialization = new JLabel(
            "Specialization: " + project.getSpecialization()
        );
        specialization.setFont(new Font("Monospaced", Font.BOLD, 20));
        
        // ========================== Description ==========================
        
        JTextArea descriptionArea = new JTextArea(project.getDescription());
        descriptionArea.setFont(new Font("Arial", Font.PLAIN, 20));

        // ========================== Creator ==========================
        
        JLabel creator = new JLabel(
            "Created by: " + database.getUserById(project.getCreatorId()).getName()
        );
        creator.setFont(new Font("Monospaced", Font.BOLD, 20));

        // ========================== Insets ==========================

        Insets labelsInset = new Insets(20, 0, 0, 0);

        // ========================== Layout ==========================
        
        projectInfoPane.setLayout(new GridBagLayout());
        
        gbc.insets = labelsInset;
        gbc.fill = GridBagConstraints.NONE;

        // ======================== Name =========================

        gbc.gridx = 0; gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        projectInfoPane.add(title, gbc);

        // ======================== Specialization =========================

        gbc.gridx = 0; gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.WEST;
        projectInfoPane.add(specialization, gbc);

        // ======================== Supervisor =========================

        gbc.gridx = 0; gbc.gridy = 4;
        gbc.anchor = GridBagConstraints.WEST;
        projectInfoPane.add(new JScrollPane(descriptionArea), gbc);

        // ======================== Creator =========================

        gbc.gridx = 0; gbc.gridy = 6;
        gbc.anchor = GridBagConstraints.WEST;
        projectInfoPane.add(creator, gbc);

        // ======================== Creator =========================

        gbc.gridx = 0; gbc.gridy = 8;
        gbc.anchor = GridBagConstraints.WEST;
        backButton = new JButton("Back");
        projectInfoPane.add(backButton, gbc);
        
        return projectInfoPane;

    }

    private JPanel getCommentsPane() {

        // ========================== Pane ==========================

        JPanel commentsPane = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        // ========================== Title ==========================
        
        gbc.gridx = 0; gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(10, 0, 0, 0);
        
        JLabel title = new JLabel("Comments");
        title.setFont(new Font("Arial", Font.BOLD, 20));

        commentsPane.add(title, gbc);

        // ========================== Comments ==========================

        gbc.gridx = 0; gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(10, 0, 0, 0);
        
        for (Comment comment : database.commentArrayList) {
            if (comment.getProjectId() == project.getId()) {
                JLabel commentLabel = new JLabel(
                    database.getUserById(comment.getCommenterId()).getName() + ": " + comment.getComment()
                );
                commentLabel.setFont(new Font("Monospaced", Font.PLAIN, 14));
                commentsPane.add(commentLabel, gbc);

                gbc.gridy++;
            }
        }

        return commentsPane;
    }



    private JPanel getCommentPane() {
        
        // ========================== Layout ==========================

        JPanel commentPane = new JPanel(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 0, 10, 0);

        // ======================= Comment Field =======================
        
        gbc.gridx = 0;   gbc.gridy = 1;
        gbc.fill = GridBagConstraints.BOTH;
        commentField = new JTextField(40);
        commentPane.add(commentField, gbc);
        
        // ======================= Comment Button =======================
        
        gbc.gridx = 0;   gbc.gridy = 2;
        gbc.fill = GridBagConstraints.BOTH;
        commentButton = new JButton("Comment");
        commentPane.add(commentButton, gbc);

        return commentPane;
    }
    
// =====================================================================================
//                                 Button Functions
// =====================================================================================

    public void comment(ActionListener actionListener) {
        commentButton.addActionListener(actionListener);
    }

    public void back(ActionListener actionListener) {
        backButton.addActionListener(actionListener);
    }
}
