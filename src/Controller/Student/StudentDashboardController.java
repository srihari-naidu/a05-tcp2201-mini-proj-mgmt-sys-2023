package Controller.Student;

import java.util.ArrayList;

import Controller.LoginController;
import Model.Database;
import Model.Lecturer;
import Model.Project;
import Model.Student;
import View.LoginView;
import View.Student.StudentDashboardView;
import View.Student.ViewProject;

public class StudentDashboardController {
    
// =====================================================================================
//                                    Attributes
// =====================================================================================

    private Student student;
    private Database database;
    
    private StudentDashboardView studentDashboardView;

// =====================================================================================
//                                    Constructors
// =====================================================================================

    public StudentDashboardController(StudentDashboardView studentDashboardView, Student student) {
        this.student = student;
        this.studentDashboardView = studentDashboardView;
        this.database = new Database();
        studentDashboardView.setVisible(true);

        initButton();
        initTable();
    }
    
// =====================================================================================
//                                    Methods
// =====================================================================================

    private void initTable() {
        if (student.getProjectId() == 0) {
            Object rowData[] = new Object[3];
            Lecturer lecturer = database.getLecturerById(student.getSupervisorId());
            ArrayList<Project> projects = lecturer.getProjects();
            for (int i = 0; i < projects.size(); i++) {
                if (
                    projects.get(i).getActive() == true && 
                    projects.get(i).getAssigned() == false && 
                    projects.get(i).getSpecialization().equals(student.getSpecialization())
                    ) {

                        int id = projects.get(i).getId();
                        String specialization = projects.get(i).getSpecialization();
                        String title = projects.get(i).getTitle();
                    
                        rowData[0] = id;
                        rowData[1] = specialization;
                        rowData[2] = title;
                        
                    studentDashboardView.getProjectsTableModel().addRow(rowData);
                }
            }
        } 
        else if (student.getProjectId() > 0) {
            studentDashboardView.getGreetingLabel().setText("Here's your project.");

            Object rowData[] = new Object[3];
            Project project = database.getProjectById(student.getProjectId());

            int id = project.getId();
            String specialization = project.getSpecialization();
            String title = project.getTitle();
                    
            rowData[0] = id;
            rowData[1] = specialization;
            rowData[2] = title;
            
            studentDashboardView.getProjectsTableModel().addRow(rowData);
        }
    }

    private void initButton() {

        studentDashboardView.logout(e -> {
            studentDashboardView.dispose();
            new LoginController(new LoginView());
        });

        studentDashboardView.view(e -> {
            if(studentDashboardView.getProjectsTable().getSelectedRow() == -1){
                studentDashboardView.showError("Please Select a Project to view further details.");
                return;
            }
            int row = studentDashboardView.getProjectsTable().getSelectedRow();
            int id = Integer.parseInt(studentDashboardView.getProjectsTableModel().getValueAt(row, 0).toString());

            Project project = database.getProjectById(id);
            new ViewProject(project);
        });
    }
}
