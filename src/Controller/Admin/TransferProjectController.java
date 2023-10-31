package Controller.Admin;

import Model.Database;
import Model.Lecturer;
import Controller.ProjectManagerController;
import Model.Admin;
import Model.Project;
import View.Admin.TransferProjectView;
import View.ProjectManagerView;

/**
 * Controller for Admin - Transfer Project functionality.
 */
public class TransferProjectController {
    
// =====================================================================================
//                                    Attributes
// =====================================================================================

    Admin admin;
    Project project;
    private Database database;
    private TransferProjectView transferProjectView;

// =====================================================================================
//                                    Constructors
// =====================================================================================

    public TransferProjectController(TransferProjectView transferProjectView, Admin admin, Project project) {
        /**
         * Initialization of admin session, database, view and button functionalities.
         */
        this.admin = admin;
        this.project = project;
        this.database = new Database();
        this.transferProjectView = transferProjectView;
        transferProjectView.setVisible(true);

        initButton();
    }

// =====================================================================================
//                                    Methods
// =====================================================================================

    public void initButton() {
        transferProjectView.transferProject(e -> {
            transfer();
        });

        transferProjectView.back(e-> {
            back();
        });

        // Populate choie/combobox with lecturer ID : Names
        for (Lecturer lecturer : database.lecturerArrayList) {
            transferProjectView.getLecturerCbo().addItem(
                lecturer.getId() + " : " + lecturer.getName()
            );
        }
    }

    public void transfer() {
        // Get selected lecturer ID
        int lecturerId = Integer.parseInt(transferProjectView.getLecturerCbo().getSelectedItem().toString().split(" : ")[0]);

        database.transferProject(project, database.getLecturerById(lecturerId));
        transferProjectView.showMessage("Project has been transferred!");
        
        admin = database.getAdminById(admin.getId());
        back();
    }

    public void back() {
        transferProjectView.dispose();
        new ProjectManagerController(new ProjectManagerView(admin), admin);
    }
}
