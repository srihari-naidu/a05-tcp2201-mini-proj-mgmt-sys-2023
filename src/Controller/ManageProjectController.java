package Controller;

import Model.Database;
import Model.Project;
import Model.User;
import Model.Comment;
import View.ManageProjectView;
import View.ProjectManagerView;

public class ManageProjectController {
    
// =====================================================================================
//                                    Attributes
// =====================================================================================
    
    private User user;
    private Project project;
    private Database database;
    private ManageProjectView manageProjectView;

// =====================================================================================
//                                    Constructors
// =====================================================================================

    public ManageProjectController(ManageProjectView manageProjectView, User user, Project project) {
        this.user = user;
        this.project = project;
        this.database = new Database();
        this.manageProjectView = manageProjectView;
        manageProjectView.setVisible(true);

        initButton();
    }

// =====================================================================================
//                                    Methods
// =====================================================================================

    
    private void initButton() {
        manageProjectView.comment(e -> {
            comment();
        });

        manageProjectView.back(e -> {
            back();
        });
    }

    private void comment() {
        int projectId = project.getId();
        String commentString = manageProjectView.getComment().trim();
        int commenterId = user.getId();

        Comment comment = new Comment(
            projectId,
            commentString,
            commenterId
        );

        database.addComment(comment);
        database.saveComments();

        manageProjectView.dispose();

        user = database.getUserById(user.getId());
        project = database.getProjectById(project.getId());
        new ManageProjectController(new ManageProjectView(project), user, project);
    }

    public void back() {
        manageProjectView.dispose();
        user = database.getUserById(user.getId());
        new ProjectManagerController(new ProjectManagerView(user), user);
    }
}
