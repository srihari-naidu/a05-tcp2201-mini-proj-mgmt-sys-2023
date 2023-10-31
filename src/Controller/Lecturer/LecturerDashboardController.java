package Controller.Lecturer;

import Controller.LoginController;
import Controller.ProjectManagerController;
import Model.Lecturer;
import View.LoginView;
import View.ProjectManagerView;
import View.Lecturer.StudentManagerView;
import View.Lecturer.LecturerDashboardView;


public class LecturerDashboardController {
    
// =====================================================================================
//                                    Attributes
// =====================================================================================

    private Lecturer lecturer;
    private LecturerDashboardView lecturerDashboardView;

// =====================================================================================
//                                    Constructors
// =====================================================================================

    /**
     * Initialization of lecturer session, dashboard view and button.
     */
    public LecturerDashboardController(LecturerDashboardView lecturerDashboardView, Lecturer lecturer) {
        this.lecturer = lecturer;
        this.lecturerDashboardView = lecturerDashboardView;
        lecturerDashboardView.setVisible(true);

        initButton();
    }
    
// =====================================================================================
//                                    Methods
// =====================================================================================

    private void initButton() {
        lecturerDashboardView.logout(e -> {
            lecturerDashboardView.dispose();
            new LoginController(new LoginView());
        });

        lecturerDashboardView.studentManager(e -> {
            lecturerDashboardView.dispose();
            new StudentManagerController(new StudentManagerView(), lecturer);
        });

        lecturerDashboardView.projectManager(e -> {
            lecturerDashboardView.dispose();
            new ProjectManagerController(new ProjectManagerView(lecturer), lecturer);
        });
    }

}
