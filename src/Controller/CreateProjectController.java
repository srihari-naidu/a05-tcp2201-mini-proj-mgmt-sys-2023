package Controller;

import Model.Database;
import Model.Project;
import Model.User;
import View.CreateProjectView;
import View.ProjectManagerView;

public class CreateProjectController {
    
// =====================================================================================
//                                    Attributes
// =====================================================================================

    private User user;
    private Database database;
    private CreateProjectView createProjectView;

// =====================================================================================
//                                    Constructors
// =====================================================================================

    public CreateProjectController(CreateProjectView createProjectView, User user) {
        this.user = user;
        this.database = new Database();
        this.createProjectView = createProjectView;
        createProjectView.setVisible(true);

        initButton();
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
    public void initButton() {
        createProjectView.create(e -> {
            create(user);
        });

        createProjectView.back(e-> {
            back();
        });
    }

    public void create(User user) {
        int id = database.generateProjectId();
        String title = createProjectView.getTitle().trim();
        String specialization = createProjectView.getSpecialization().trim();
        String description = createProjectView.getDescription().trim();
        boolean activeStatus = createProjectView.getStatus();
        boolean assignedStatus = false;
        int creatorId = user.getId();

        if (title.isEmpty()) {
            createProjectView.showError("Title is required.");
            return;
        }
        else if (specialization.isEmpty()) {
            createProjectView.showError("Specialization is required.");
            return;
        }
        else if (description.isEmpty()) {
            createProjectView.showError("Description is required.");
            return;
        }
        
        Project project = new Project(
            id,
            title,
            description,
            specialization,
            activeStatus,
            assignedStatus,
            creatorId
        );
        
        database.addProject(project);
        database.saveProjects();

        createProjectView.showMessage("Project has been created!");
        createProjectView.clearFields();

        back();
    }

    public void back() {
        createProjectView.dispose();
        new ProjectManagerController(new ProjectManagerView(user), user);
    }
}
