package Controller;

import Model.User;
import Controller.Admin.AdminDashboardController;
import Controller.Lecturer.LecturerDashboardController;
import Controller.Student.StudentDashboardController;
import Model.Admin;
import Model.Database;
import Model.Lecturer;
import Model.Student;
import View.LoginView;
import View.Admin.AdminDashboardView;
import View.Lecturer.LecturerDashboardView;
import View.Student.StudentDashboardView;


public class LoginController {
    
// =====================================================================================
//                                    Attributes
// =====================================================================================

    private LoginView loginView;
    private Database database;

// =====================================================================================
//                                    Constructors
// =====================================================================================

    public LoginController(LoginView loginView) {
        this.loginView = loginView;
        loginView.setVisible(true);

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
        loginView.login(e -> {
            login();
        });
    }

    public void login() {
        this.database = new Database();

        String username = loginView.getUsername().trim();
        String password = loginView.getPassword().trim();

        if (username.isEmpty()) {
            loginView.showError("Username is required.");
            return;
        }
        else if (password.isEmpty()) {
            loginView.showError("Password is required.");
            return;
        }
        else if (!(database.userExists(username))) {
            loginView.showError("Username doesn't exist.");
            return;
        }

        if (!database.userExists(username)) {   
            loginView.showError("Username wasn't found.");
            return;
        }
        
        for (User user : this.database.userArrayList) {
            if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                switch (user.getType()) {
                    case 'a':
                        Admin admin = database.getAdminById(user.getId());
                        new AdminDashboardController(new AdminDashboardView(admin), admin);
                        loginView.dispose();
                        return;
                        
                    case 'l':
                        Lecturer lecturer = database.getLecturerById(user.getId());
                        new LecturerDashboardController(new LecturerDashboardView(lecturer), lecturer);
                        loginView.dispose();
                        return;
                        
                    case 's':
                        Student student = database.getStudentById(user.getId());
                        new StudentDashboardController(new StudentDashboardView(student), student);
                        loginView.dispose();
                        return;

                    default:
                        loginView.showError("Something went wrong.");
                        return;
                }    
            }
        }
        loginView.showError("Credentials do not match.");
        return;
    }

}
