package Controller.Admin;

import Model.Database;
import Model.User;
import Model.Admin;
import View.Admin.EditUserView;
import View.Admin.UserManagerView;

/**
 * Controller for Admin - Edit User functionality.
 */
public class EditUserController {
    
// =====================================================================================
//                                    Attributes
// =====================================================================================

    private Admin admin;
    private Database database;
    private EditUserView editAdminView;

// =====================================================================================
//                                    Constructors
// =====================================================================================

    public EditUserController(EditUserView editAdminView, Admin admin, User userToEdit) {
        /**
         * Initialization of admin session, database, view and button functionalities.
         */
        this.admin = admin;
        this.database = new Database();
        this.editAdminView = editAdminView;
        editAdminView.setVisible(true);

        initButton(userToEdit);
    }

// =====================================================================================
//                                    Methods
// =====================================================================================

    public void initButton(User userToEdit) {
        editAdminView.update(e -> {
            update(userToEdit);
        });

        editAdminView.back(e-> {
            back();
        });
    }

    /**
     * Update the information of the user to be edited.
     */
    public void update(User userToEdit) {
        // Record user inputs.
        String firstname = this.editAdminView.getFirstname().trim();
        String lastname = this.editAdminView.getLastname().trim();
        String username = this.editAdminView.getUsername().trim();
        String password = this.editAdminView.getPassword().trim();

        // Validate empty fields.
        if (firstname.isEmpty()) {
            editAdminView.showError("First name is required.");
            return;
        }
        else if (lastname.isEmpty()) {
            editAdminView.showError("Last name is required.");
            return;
        }
        else if (username.isEmpty()) {
            editAdminView.showError("Username is required.");
            return;
        }
        else if (password.isEmpty()) {
            editAdminView.showError("Password is required.");
            return;
        }
        // Validate existing usernames.
        else if (
            (!userToEdit.getUsername().equals(username)) && 
            (database.userExists(username))
        ) {
            editAdminView.showError("Username already taken.");
            return;
        }
        
        // Create new user objects with updated data.
        User user = new User(
            userToEdit.getId(),
            userToEdit.getType(),
            firstname,
            lastname,
            username,
            password
        );

        // Update database.
        database.updateUser(user);
        database.saveUsers();

        editAdminView.showMessage("User has been updated!");
        
        back();
    }

    /**
     * Close current window, and open the Admin - User Manager window.
     */
    public void back() {
        editAdminView.dispose();
        new UserManagerController(new UserManagerView(), admin);
    }
    
}
