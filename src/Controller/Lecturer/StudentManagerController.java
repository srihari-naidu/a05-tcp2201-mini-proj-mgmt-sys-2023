package Controller.Lecturer;

import java.util.ArrayList;

import Model.Database;
import Model.Lecturer;
import Model.Project;
import Model.Student;
import View.Admin.ViewStudent;
import View.Lecturer.AssignProjectView;
import View.Lecturer.LecturerDashboardView;
import View.Lecturer.StudentManagerView;

public class StudentManagerController {
    
// =====================================================================================
//                                    Attributes
// =====================================================================================
    
    private Lecturer lecturer;
    private Database database;
    private StudentManagerView studentManagerView;

// =====================================================================================
//                                    Constructors
// =====================================================================================

    public StudentManagerController(StudentManagerView studentManagerView, Lecturer lecturer) {
        this.lecturer = lecturer;
        this.database = new Database();
        this.studentManagerView = studentManagerView;
        studentManagerView.setVisible(true);

        initButton();
        initTable();
    }

// =====================================================================================
//                                    Methods
// =====================================================================================

    private void initTable() {
        Object rowData[] = new Object[4];
        ArrayList<Student> students = lecturer.getStudents();
        for (int i = 0; i < students.size(); i++)
        {
            int id = students.get(i).getId();
            String name = students.get(i).getName();
            String specialization = students.get(i).getSpecialization();
            String project;

            if (students.get(i).getProjectId() > 0) {
                project = database.getProjectById(students.get(i).getProjectId()).getTitle();
            }
            else {
                project = "Unassigned";
            }

            rowData[0] = id;
            rowData[1] = name;
            rowData[2] = specialization;
            rowData[3] = project;

            studentManagerView.getStudentsTableModel().addRow(rowData);
        }
    }

    private void initButton() {
        studentManagerView.viewStudent(e -> {
            if(studentManagerView.getStudentsTable().getSelectedRow() == -1){
                studentManagerView.showError("Please Select a Student to be viewed.");
                return;
            }
            int row = studentManagerView.getStudentsTable().getSelectedRow();
            int id = Integer.parseInt(studentManagerView.getStudentsTableModel().getValueAt(row, 0).toString());

            Student student = database.getStudentById(id);
            new ViewStudent(student);
        });

        studentManagerView.assignProject(e -> {
            if(studentManagerView.getStudentsTable().getSelectedRow() == -1){
                studentManagerView.showError("Please Select a Student to assign a project.");
                return;
            }
            int row = studentManagerView.getStudentsTable().getSelectedRow();
            int id = Integer.parseInt(studentManagerView.getStudentsTableModel().getValueAt(row, 0).toString());
            
            Student student = database.getStudentById(id);
            
            // If student has a project assigned.
            if (student.getProjectId() > 0) {
                studentManagerView.showError("Student already has a project assigned.");
            }
            else {
                // Redirect lecturer to Assign Project window.
                studentManagerView.dispose();
                new AssignProjectController(new AssignProjectView(student), lecturer, student);
            }
        });

        studentManagerView.unassignProject(e -> {     
            if(studentManagerView.getStudentsTable().getSelectedRow() == -1){
                studentManagerView.showError("Please Select a Student to unassign Project.");
                return;
            }       
            int row = studentManagerView.getStudentsTable().getSelectedRow();
            int id = Integer.parseInt(studentManagerView.getStudentsTableModel().getValueAt(row, 0).toString());
            
            Student student = database.getStudentById(id);
            Project project = database.getProjectById(student.getProjectId());

            if (student.getProjectId() == 0) {
                studentManagerView.showError("Student does not have a project assigned.");
            }
            else {
                database.unassignProject(student, project);
                studentManagerView.showMessage("Student has been unassigned of their project.");
            }

            // Refresh lecturer database and redirect to Student Manager.
            lecturer = database.getLecturerById(lecturer.getId());
            studentManagerView.dispose();
            new StudentManagerController(new StudentManagerView(), lecturer);
        });

        /**
         * Refresh lecturer database and redirect to dashboard.
         */
        studentManagerView.back(e -> {
            studentManagerView.dispose();
            lecturer = database.getLecturerById(lecturer.getId());
            new LecturerDashboardController(new LecturerDashboardView(lecturer), lecturer);
        });
    }
}
