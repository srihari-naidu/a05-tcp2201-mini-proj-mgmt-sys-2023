package Controller.Admin;

import Controller.Admin.Register.RegisterChoiceController;
import Model.Admin;
import Model.Database;
import Model.User;
import View.Admin.AdminDashboardView;
import View.Admin.EditUserView;
import View.Admin.UserManagerView;
import View.Admin.ViewAdmin;
import View.Admin.ViewLecturer;
import View.Admin.ViewStudent;
import View.Admin.Register.RegisterChoiceView;

/**
 * Controller for Admin - Manage Users functionality.
 */
public class UserManagerController {
    
// =====================================================================================
//                                    Attributes
// =====================================================================================
    
    private Admin admin;
    private Database database;
    private UserManagerView userManagerView;

// =====================================================================================
//                                    Constructors
// =====================================================================================

    /**
     * Initialization of admin session, database, view, button and table.
     */
    public UserManagerController(UserManagerView userManagerView, Admin admin) {
        this.admin = admin;
        this.database = new Database();
        this.userManagerView = userManagerView;
        userManagerView.setVisible(true);

        initButton();
        initTable();
    }

// =====================================================================================
//                                    Methods
// =====================================================================================

    /**
     * Initialization of table with user data.
     */
    private void initTable() {
        Object rowData[] = new Object[4];
        for (int i = 0; i < database.userArrayList.size(); i++)
        {
            int id = database.userArrayList.get(i).getId();
            char type = database.userArrayList.get(i).getType();
            String userType = "";
            if (type == 'a') {
                userType = "Admin";
            }
            else if (type == 'l') {
                userType = "Lecturer";
            }
            else if (type == 's') {
                userType = "Student";
            }
            String firstname = database.userArrayList.get(i).getFirstname();
            String lastname = database.userArrayList.get(i).getLastname();

            rowData[0] = id;
            rowData[1] = userType;
            rowData[2] = firstname;
            rowData[3] = lastname;

            userManagerView.getUsersTableModel().addRow(rowData);
        }
    }

    private void initButton() {
        userManagerView.viewUser(e -> {
            if(userManagerView.getUsersTable().getSelectedRow() == -1){
                userManagerView.showError("Please Select a User to be managed.");
                return;
            }
            // Get selected row data.
            int row = userManagerView.getUsersTable().getSelectedRow();
            int id = Integer.parseInt(userManagerView.getUsersTableModel().getValueAt(row, 0).toString());

            // Get user to be view from database.
            User userToView = database.getUserById(id);
            
            // Close current window and redirect to the View User window based on user type.
            if (userToView.getType() == 'a') {
                new ViewAdmin(database.getAdminById(userToView.getId()));
            }
            else if (userToView.getType() == 'l') {
                new ViewLecturer(database.getLecturerById(userToView.getId()));
            }
            else if (userToView.getType() == 's') {
                new ViewStudent(database.getStudentById(userToView.getId()));
            }
        });

        userManagerView.editUser(e -> {
            if(userManagerView.getUsersTable().getSelectedRow() == -1){
                userManagerView.showError("Please Select a User to be modified.");
                return;
            }
            // Get selected row data.
            int row = userManagerView.getUsersTable().getSelectedRow();
            int id = Integer.parseInt(userManagerView.getUsersTableModel().getValueAt(row, 0).toString());
            
            // Get user to be edited from database.
            User userToEdit = database.getUserById(id); 

            // Close current window and redirect to the Edit User window.
            userManagerView.dispose();
            new EditUserController(new EditUserView(userToEdit), admin, userToEdit);
        });

        userManagerView.deleteUser(e -> {
            if(userManagerView.getUsersTable().getSelectedRow() == -1){
                userManagerView.showError("Please Select a User to be deleted.");
                return;
            }
            // Get selected row data.
            int row = userManagerView.getUsersTable().getSelectedRow();
            int id = Integer.parseInt(userManagerView.getUsersTableModel().getValueAt(row, 0).toString());
            
            // Get user to be deleted from database.            
            User userToDelete = database.getUserById(id);

            // If user selects to delete themselves.
            if (userToDelete.getId() == admin.getId()) {
                userManagerView.showError("You can't delete yourself!");
            }
            else {
                database.deleteUser(userToDelete);
                userManagerView.showMessage("User has been deleted");
                
                // Close current window and redirect to the User Manager window.
                userManagerView.dispose();
                new UserManagerController(new UserManagerView(), admin);
            }
        });

        // Close current window and redirect to the Register User sequence window.
        userManagerView.registerNewUser(e -> {
            userManagerView.dispose();
            new RegisterChoiceController(new RegisterChoiceView(admin), admin);
        });                

        // Close current window and redirect to the Admin Dashboard window.
        userManagerView.back(e -> {
            userManagerView.dispose();
            new AdminDashboardController(new AdminDashboardView(admin), admin);
        });
    }
}
