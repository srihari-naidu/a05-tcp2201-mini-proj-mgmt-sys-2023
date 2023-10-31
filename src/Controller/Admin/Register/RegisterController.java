package Controller.Admin.Register;

import java.util.ArrayList;

import Controller.Admin.UserManagerController;
import Model.Admin;
import Model.Database;
import Model.Lecturer;
import Model.Student;
import Model.User;
import View.Admin.UserManagerView;
import View.Admin.Register.RegisterView;

public class RegisterController {

// =====================================================================================
//                                    Attributes
// =====================================================================================

    private Admin admin;
    private Database database;
    private RegisterView view;
    private char usertype;

// =====================================================================================
//                                    Constructors
// =====================================================================================

    private RegisterController(RegBuilder regBuilder) {
        this.admin = regBuilder.admin;
        this.database = new Database();

        this.view = regBuilder.view;
        this.usertype = regBuilder.usertype;
        view.setVisible(true);

        initButton();
    }
// =====================================================================================
//                                    Builder
// =====================================================================================

    public static class RegBuilder{
        private Admin admin;
        private RegisterView view;
        private char usertype;

        public RegBuilder admin(Admin a){admin=a;return this;}
        public RegBuilder viewcont(RegisterView v){view=v;return this;}
        public RegBuilder usertype(char u){usertype=u;return this;}

        public RegisterController build(){
            return new RegisterController(this);
        }
    }

// =====================================================================================
//                                    Methods
// =====================================================================================

    public void initButton(){
        view.register(e -> {
            register();
        });

        view.back(e-> {
            back();
        });

        if(usertype=='s'){
            for (Lecturer lecturer : database.lecturerArrayList) {
                view.getSupervisorCbo().addItem(
                    lecturer.getId() + " : " + lecturer.getName());
            }
        }
    };

    public void register(){
        String firstname = this.view.getFirstname().trim();
        String lastname = this.view.getLastname().trim();
        String username = this.view.getUsername().trim();
        String password = this.view.getPassword().trim();
        String specialization="";
        int supervisorId=0;

        if (usertype=='s'){
            specialization = this.view.getSpecialization().trim();
            supervisorId = Integer.parseInt(view.getSupervisorCbo().getSelectedItem().toString().split(" : ")[0]);
        }

        if (firstname.isEmpty()) {
            view.showError("First name is required.");
            return;
        }
        else if (lastname.isEmpty()) {
            view.showError("Last name is required.");
            return;
        }
        else if (username.isEmpty()) {
            view.showError("Username is required.");
            return;
        }
        else if (password.isEmpty()) {
            view.showError("Password is required.");
            return;
        }
        else if (database.userExists(username)) {
            view.showError("Username already taken.");
            return;
        }
        else if (usertype=='s' && specialization.isEmpty()) {
            view.showError("Specialization is required.");
            return;
        }

        User user = new User(
            database.generateUserId(),
            usertype,
            firstname,
            lastname,
            username,
            password
        );

        if(usertype=='a'){
            Admin admin = new Admin(
                user.getId(),
                user.getType(),
                user.getFirstname(),
                user.getLastname(),
                user.getUsername(),
                user.getPassword(),
                new ArrayList<>(),
                new ArrayList<>()
            );
            database.addAdmin(admin);
            view.showMessage("Admin registered successfully!");
        }
        else if(usertype=='l'){
            Lecturer lecturer = new Lecturer(
                user.getId(),
                user.getType(),
                user.getFirstname(),
                user.getLastname(),
                user.getUsername(),
                user.getPassword(),
                new ArrayList<>(),
                new ArrayList<>()
            );
            database.addLecturer(lecturer);
            view.showMessage("Lecturer registered successfully!");
        }
        else if(usertype=='s'){
            Student student = new Student(
                user.getId(),
                user.getType(),
                user.getFirstname(),
                user.getLastname(),
                user.getUsername(),
                user.getPassword(),
                specialization,
                supervisorId
            );
            student.setProjectId(0);

            database.addStudent(student);
            database.saveStudents();
            view.showMessage("Student registered successfully!");
        }

        database.addUser(user);
        database.saveUsers();

        back();
    };

    public void back() {
        view.dispose();
        new UserManagerController(new UserManagerView(), admin);
    }
}
