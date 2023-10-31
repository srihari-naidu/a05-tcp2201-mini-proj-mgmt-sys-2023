package Controller.Admin;

import Controller.LoginController;
import Controller.ProjectManagerController;
import Model.Admin;
import View.LoginView;
import View.ProjectManagerView;
import View.Admin.UserManagerView;
import View.Admin.AdminDashboardView;
import View.Admin.ReportGeneratorView;

/**
 * Controller for Admin - Dashboard functionality.
 */
public class AdminDashboardController {
    
// =====================================================================================
//                                    Attributes
// =====================================================================================

    private Admin admin;
    private AdminDashboardView adminDashboardView;

// =====================================================================================
//                                    Constructors
// =====================================================================================

    public AdminDashboardController(AdminDashboardView adminDashboardView, Admin admin) {
        /**
         * Initialization of admin session, view and button functionalities.
         */
        this.admin = admin;
        this.adminDashboardView = adminDashboardView;
        adminDashboardView.setVisible(true);

        initButton();
    }
    
// =====================================================================================
//                                    Methods
// =====================================================================================

    private void initButton() {
        /**
         * Close current window, and open the login window.
         */
        adminDashboardView.logout(e -> {
            adminDashboardView.dispose();
            new LoginController(new LoginView());
        });

        /**
         * Close current window, and open the Admin - User Manager window.
         */
        adminDashboardView.userManager(e -> {
            adminDashboardView.dispose();
            new UserManagerController(new UserManagerView(), admin);
        });

        /**
         * Close current window, and open the Admin - Project Manager window.
         */
        adminDashboardView.projectManager(e -> {
            adminDashboardView.dispose();
            new ProjectManagerController(new ProjectManagerView(admin), admin);
        });

        /**
         * Close current window, and open the Admin - Report Manager window.
         */
        adminDashboardView.reportGenerator(e -> {
            adminDashboardView.dispose();
            new ReportGeneratorController(new ReportGeneratorView(), admin);
        });
    }

}
