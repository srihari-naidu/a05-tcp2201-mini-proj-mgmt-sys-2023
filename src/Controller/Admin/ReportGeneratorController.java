package Controller.Admin;

import java.util.ArrayList;
import java.util.HashSet;

import javax.swing.RowFilter;
import java.util.regex.PatternSyntaxException;

import Model.Admin;
import Model.Comment;
import Model.Database;
import Model.Lecturer;
import Model.Student;
import View.Admin.AdminDashboardView;
import View.Admin.ReportGeneratorView;

/**
 * Controller for Admin - View Report functionality.
 */
public class ReportGeneratorController {
    
// =====================================================================================
//                                    Attributes
// =====================================================================================
    
    private Admin admin;
    private Database database;
    private ReportGeneratorView reportGeneratorView;

// =====================================================================================
//                                    Constructors
// =====================================================================================

    public ReportGeneratorController(ReportGeneratorView reportGeneratorView, Admin admin) {
        /**
         * Initialization of admin session, database, view and button and table.
         */
        this.admin = admin;
        this.database = new Database();
        this.reportGeneratorView = reportGeneratorView;
        reportGeneratorView.setVisible(true);

        initButton();
        initTable();
    }

// =====================================================================================
//                                    Methods
// =====================================================================================

    /**
     * Initialization of table with project data.
     */
    private void initTable() {
        Object rowData[] = new Object[7];
        for (int i = 0; i < database.projectArrayList.size(); i++)
        {
            int id = database.projectArrayList.get(i).getId();
            String title = database.projectArrayList.get(i).getTitle();
            String specialization = database.projectArrayList.get(i).getSpecialization();
            boolean active = database.projectArrayList.get(i).getActive();
            boolean assigned = database.projectArrayList.get(i).getAssigned();
            int creatorId = database.projectArrayList.get(i).getCreatorId();
            String creator = database.getUserById(creatorId).getName();
            String commented = "";

            for (Comment comment : database.commentArrayList) {
                if (comment.getProjectId() == id) {
                    commented = "Commented";
                }
            }

            String state = "";
            if (active == true) {
                state = "Active";
            }
            else if (active == false) {
                state = "Inactive";
            }
            
            String status = "";
            if (assigned == true) {
                status = "Assigned";
            }
            else if (assigned == false) {
                status = "Unassigned";
            }

            rowData[0] = id;
            rowData[1] = title;
            rowData[2] = specialization;
            rowData[3] = state;
            rowData[4] = status;
            rowData[5] = creator;
            rowData[6] = commented;

            reportGeneratorView.getProjectsTableModel().addRow(rowData);
        }
    }

    private void initButton() {
        reportGeneratorView.filter(e -> {
            // Get query from choice box.
            String query = reportGeneratorView.getQueryCbo().getSelectedItem().toString();

            // Set no filter if query is all projects.
            if (query.equals("All")) {
                reportGeneratorView.getProjectsTableSorter().setRowFilter(null);
            } 
            else {
                // Set filter based on the selected query.
                try {
                    reportGeneratorView.getProjectsTableSorter().setRowFilter(RowFilter.regexFilter(query));
                } 
                catch (PatternSyntaxException ex) {
                    System.out.println("Bad Regex.");
                }
             }
        });

        reportGeneratorView.back(e -> {
            reportGeneratorView.dispose();
            new AdminDashboardController(new AdminDashboardView(admin), admin);
        });

        // Initialize combobox with different set of queries.
        ArrayList<String> queriesList = new ArrayList<>();
        queriesList.add("All");

        queriesList.add("");
        queriesList.add("Specialization(s)");
        
        HashSet<String> specializations = new HashSet<String>();
        for (Student student : database.studentArrayList) {
            specializations.add(student.getSpecialization());
        }
        queriesList.addAll(specializations);
        
        queriesList.add("");
        queriesList.add("Lecturer(s)");
        
        HashSet<String> lecturers = new HashSet<String>();
        for (Lecturer lecturer : database.lecturerArrayList) {
            lecturers.add(lecturer.getName());
        }
        queriesList.addAll(lecturers);
        
        queriesList.add(""); 
        queriesList.add("Admin(s)");

        HashSet<String> admins = new HashSet<String>();
        for (Admin admin : database.adminArrayList) {
            admins.add(admin.getName());
        }
        queriesList.addAll(admins);

        queriesList.add("");

        queriesList.add("Active");
        queriesList.add("Inactive");
        queriesList.add("Assigned");
        queriesList.add("Unassigned");
        queriesList.add("Commented");
        
        for (String query : queriesList) {
            reportGeneratorView.getQueryCbo().addItem(query);
        }
    }
}
