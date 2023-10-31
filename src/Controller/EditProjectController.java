package Controller;

import Model.Database;
import Model.Project;
import Model.User;
import View.EditProjectView;
import View.ProjectManagerView;

public class EditProjectController {
    
// =====================================================================================
//                                    Attributes
// =====================================================================================

    private User user;
    private Database database;
    private EditProjectView editProjectView;

// =====================================================================================
//                                    Constructors
// =====================================================================================

    public EditProjectController(EditProjectView editProjectView, User user, Project projectToEdit) {
        this.user = user;
        this.database = new Database();
        this.editProjectView = editProjectView;
        editProjectView.setVisible(true);

        initButton(projectToEdit);
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
    public void initButton(Project projectToEdit) {
        editProjectView.update(e -> {
            update(projectToEdit);
        });

        editProjectView.back(e-> {
            editProjectView.dispose();
            new ProjectManagerController(new ProjectManagerView(user), user);
        });
    }

    public void update(Project projectToEdit) {
        String title = editProjectView.getTitle().trim();
        String specialization = editProjectView.getSpecialization().trim();
        String description = editProjectView.getDescription().trim();
        boolean activeStatus = editProjectView.getStatus();

        if (title.isEmpty()) {
            editProjectView.showError("Title is required.");
            return;
        }
        else if (specialization.isEmpty()) {
            editProjectView.showError("Specialization is required.");
            return;
        }
        else if (description.isEmpty()) {
            editProjectView.showError("Description is required.");
            return;
        }
        
        Project project = new Project(
            projectToEdit.getId(),
            title,
            description,
            specialization,
            activeStatus,
            projectToEdit.getAssigned(),
            projectToEdit.getCreatorId()
        );
        
        database.updateProject(project);
        database.saveProjects();
        
        editProjectView.showMessage("Project has been udpated!");

        editProjectView.dispose();
        new ProjectManagerController(new ProjectManagerView(user), user);
    }
}
