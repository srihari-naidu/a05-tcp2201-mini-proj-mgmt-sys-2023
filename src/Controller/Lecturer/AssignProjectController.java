package Controller.Lecturer;

import Model.Database;
import Model.Lecturer;
import Model.Project;
import Model.Student;
import View.Lecturer.AssignProjectView;
import View.Lecturer.StudentManagerView;

public class AssignProjectController {
    
// =====================================================================================
//                                    Attributes
// =====================================================================================

    private Lecturer lecturer;
    private Student student;

    private Database database;
    private AssignProjectView assignProjectView;

// =====================================================================================
//                                    Constructors
// =====================================================================================

    /**
     * Initialization of user session, database, view and button.
     */
    public AssignProjectController(AssignProjectView assignProjectView, Lecturer lecturer, Student student) {
        this.lecturer = lecturer;
        this.student = student;
        this.database = new Database();

        this.assignProjectView = assignProjectView;
        assignProjectView.setVisible(true);

        initButton();
    }

// =====================================================================================
//                                    Methods
// =====================================================================================

    public void initButton() {
        assignProjectView.assignProject(e -> {
            assign();
        });

        assignProjectView.back(e-> {
            back();
        });

        // Populate combobox with Project ID : Name.
        for (Project project : lecturer.getProjects()) {
            if (project.getActive() == true &&
                project.getAssigned() == false && 
                project.getSpecialization().equals(student.getSpecialization())
            ) {
                assignProjectView.getProjectCbo().addItem(
                    project.getId() + " : " + project.getTitle()
                );
            }
        }
    }

    public void assign() {
        // Get Project Id from selection.
        int projectId = Integer.parseInt(assignProjectView.getProjectCbo().getSelectedItem().toString().split(" : ")[0]);

        database.assignProject(student, database.getProjectById(projectId));
        assignProjectView.showMessage("Project has been assigned!");
        
        // Refresh lecturer's database.
        lecturer = database.getLecturerById(lecturer.getId());
        
        assignProjectView.dispose();
        new StudentManagerController(new StudentManagerView(), lecturer);
    }

    public void back() {
        // Redirect back to Student Manager.
        assignProjectView.dispose();
        new StudentManagerController(new StudentManagerView(), lecturer);
    }
}
