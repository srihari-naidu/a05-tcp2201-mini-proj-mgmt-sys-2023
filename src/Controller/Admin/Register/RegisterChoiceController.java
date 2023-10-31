package Controller.Admin.Register;

import Controller.Admin.UserManagerController;
import Model.Admin;
import View.Admin.UserManagerView;
import View.Admin.Register.RegisterAdminView;
import View.Admin.Register.RegisterLecturerView;
import View.Admin.Register.RegisterStudentView;
import View.Admin.Register.RegisterChoiceView;


public class RegisterChoiceController {
    
// =====================================================================================
//                                    Attributes
// =====================================================================================

    private Admin admin;
    private RegisterChoiceView registerView;

// =====================================================================================
//                                    Constructors
// =====================================================================================

    public RegisterChoiceController(RegisterChoiceView registerView, Admin admin) {
        this.admin = admin;
        this.registerView = registerView;
        registerView.setVisible(true);

        initButton();
    }


// =====================================================================================
//                                    Methods
// =====================================================================================

    private void initButton() {

        registerView.cancel(e -> {
            registerView.dispose();
            new UserManagerController(new UserManagerView(), admin);
        });

        registerView.registerAdmin(e -> {
            registerView.dispose();
            new RegisterController.RegBuilder().viewcont(new RegisterAdminView()).admin(admin).usertype('a').build();
        });

        registerView.registerLecturer(e -> {
            registerView.dispose();
            new RegisterController.RegBuilder().viewcont(new RegisterLecturerView()).admin(admin).usertype('l').build();
        });

        registerView.registerStudent(e -> {
            registerView.dispose();
            new RegisterController.RegBuilder().viewcont(new RegisterStudentView()).admin(admin).usertype('s').build();
        });
    }

}
