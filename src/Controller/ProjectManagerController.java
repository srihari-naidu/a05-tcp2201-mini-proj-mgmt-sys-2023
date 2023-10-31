package Controller;

import java.util.ArrayList;

import Controller.Admin.AdminDashboardController;
import Controller.Admin.TransferProjectController;
import Controller.Lecturer.LecturerDashboardController;
import Model.Admin;
import Model.Database;
import Model.Lecturer;
import Model.Project;
import Model.User;
import View.Admin.AdminDashboardView;
import View.Admin.TransferProjectView;
import View.CreateProjectView;
import View.EditProjectView;
import View.ManageProjectView;
import View.ProjectManagerView;
import View.Lecturer.LecturerDashboardView;

public class ProjectManagerController {
    
// =====================================================================================
//                                    Attributes
// =====================================================================================
    private User user;
    private Database database;
    private ProjectManagerView projectManagerView;

// =====================================================================================
//                                    Constructors
// =====================================================================================

    public ProjectManagerController(ProjectManagerView projectManagerView, User user) {
        this.user = user;
        this.database = new Database();
        this.projectManagerView = projectManagerView;
        projectManagerView.setVisible(true);

        initButton();
        initTable();
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

    private void initTable() {
        if (user.isAdmin()) {
            Object rowData[] = new Object[6];
            for (int i = 0; i < database.projectArrayList.size(); i++)
            {
                int id = database.projectArrayList.get(i).getId();
                String title = database.projectArrayList.get(i).getTitle();
                String specialization = database.projectArrayList.get(i).getSpecialization();
                String activeStatus = (database.projectArrayList.get(i).getActive() == true) ? "Active" : "Inactive";
                String assignedStatus = (database.projectArrayList.get(i).getAssigned() == true) ? "Assigned" : "Unassigned";
                User creator = database.getUserById(database.projectArrayList.get(i).getCreatorId());
                
                String creatorName = creator.getName();
                if (creator.getId() == user.getId()) {
                    creatorName = "You";
                }

                rowData[0] = id;
                rowData[1] = title;
                rowData[2] = specialization;
                rowData[3] = activeStatus;
                rowData[4] = assignedStatus;
                rowData[5] = creatorName;
    
                projectManagerView.getProjectsTableModel().addRow(rowData);
            }
        }
        else if (user.isLecturer()) {  
            Object rowData[] = new Object[5];
            ArrayList <Project> projects = database.getLecturerById(user.getId()).getProjects();
            for (int i = 0; i < projects.size(); i++)
            {
                int id = projects.get(i).getId();
                String title = projects.get(i).getTitle();
                String specialization = projects.get(i).getSpecialization();
                String activeStatus = (projects.get(i).getActive() == true) ? "Active" : "Inactive";
                String assignedStatus = (projects.get(i).getAssigned() == true) ? "Assigned" : "Unassigned";
    
                rowData[0] = id;
                rowData[1] = title;
                rowData[2] = specialization;
                rowData[3] = activeStatus;
                rowData[4] = assignedStatus;
    
                projectManagerView.getProjectsTableModel().addRow(rowData);
            }
        }

    }

    private void initButton() {
        projectManagerView.manageProject(e -> {
            if(projectManagerView.getProjectsTable().getSelectedRow() == -1){
                projectManagerView.showError("Please Select a Project to be managed.");
                return;
            }
            projectManagerView.dispose();

            int row = projectManagerView.getProjectsTable().getSelectedRow();
            int id = Integer.parseInt(projectManagerView.getProjectsTableModel().getValueAt(row, 0).toString());

            Project project = database.getProjectById(id);
            new ManageProjectController(new ManageProjectView(project), user, project);
        });

        projectManagerView.editProject(e -> {
            if(projectManagerView.getProjectsTable().getSelectedRow() == -1){
                projectManagerView.showError("Please Select a Project to be edited.");
                return;
            }
            int row = projectManagerView.getProjectsTable().getSelectedRow();
            int id = Integer.parseInt(projectManagerView.getProjectsTableModel().getValueAt(row, 0).toString());
            
            Project projectToEdit = database.getProjectById(id); 
            
            if (projectToEdit.getAssigned() == true) {
                projectManagerView.showError("Project has been assigned to a student already.\nUnassign first");
            }
            else {
                projectManagerView.dispose();
                new EditProjectController(new EditProjectView(projectToEdit), user, projectToEdit);
            }
        });

        projectManagerView.deleteProject(e -> {       
            if(projectManagerView.getProjectsTable().getSelectedRow() == -1){
                projectManagerView.showError("Please Select a Project for Deletion.");
                return;
            }     
            int row = projectManagerView.getProjectsTable().getSelectedRow();
            int id = Integer.parseInt(projectManagerView.getProjectsTableModel().getValueAt(row, 0).toString());
            
            Project projectToDelete = database.getProjectById(id);

            if (projectToDelete.getAssigned() == true) {
                projectManagerView.showError("Project is assigned to a student already!");
            }
            else {
                database.deleteProject(projectToDelete);
                projectManagerView.showMessage("Project has been deleted");
                
                projectManagerView.dispose();
                new ProjectManagerController(new ProjectManagerView(user), user);
            }
        });

        projectManagerView.transferProject(e -> {
            if(projectManagerView.getProjectsTable().getSelectedRow() == -1){
                projectManagerView.showError("Please Select a Project to be transfered.");
                return;
            }
            int row = projectManagerView.getProjectsTable().getSelectedRow();
            int id = Integer.parseInt(projectManagerView.getProjectsTableModel().getValueAt(row, 0).toString());
            
            Project projectToTransfer = database.getProjectById(id); 
            
            if (projectToTransfer.getAssigned() == true) {
                projectManagerView.showError("Project is assigned to a student already!");
            }
            else if (user.isLecturer()) {
                projectManagerView.showError("Sorry, you can't do that. Contact the admin to transfer your project.");
            }
            else if (user.isAdmin()) {
                projectManagerView.dispose();
                Admin admin = database.getAdminById(user.getId());
                new TransferProjectController(new TransferProjectView(), admin, projectToTransfer);
            }
        });

        projectManagerView.createNewProject(e -> {
            projectManagerView.dispose();
            new CreateProjectController(new CreateProjectView(), user);
        });

        projectManagerView.back(e -> {
            projectManagerView.dispose();
            if (user.isAdmin()) {
                Admin admin = database.getAdminById(user.getId());
                new AdminDashboardController(new AdminDashboardView(admin), admin);
            }
            else if (user.isLecturer()) {
                Lecturer lecturer = database.getLecturerById(user.getId());
                new LecturerDashboardController(new LecturerDashboardView(lecturer), lecturer);
            }
        });
    }
}
