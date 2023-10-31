package Model;

import java.io.*;
import java.util.ArrayList;

/**
 * Datanase.
 */
public class Database {
// =====================================================================================
//                                    Attributes
// =====================================================================================

    private String userDatabaseFile = "data/userDatabase.csv";
    private String studentDatabaseFile = "data/studentDatabase.csv";

    private String commentDatabaseFile = "data/commentDatabase.csv";
    private String projectDatabaseFile = "data/projectDatabase.csv";

    public ArrayList<User> userArrayList;
    public ArrayList<Admin> adminArrayList;
    public ArrayList<Lecturer> lecturerArrayList;
    public ArrayList<Student> studentArrayList;

    public ArrayList<Comment> commentArrayList;
    public ArrayList<Project> projectArrayList;

// =====================================================================================
//                                    Constructors
// =====================================================================================

    public Database() {
        // Initialize local database arraylist.
        this.userArrayList = loadUsers(new File(userDatabaseFile));
        this.projectArrayList = loadProjects(new File(projectDatabaseFile));
        this.commentArrayList = loadComments(new File(commentDatabaseFile));
        
        this.studentArrayList = loadStudents(new File(studentDatabaseFile));
        for (Student student : studentArrayList) {
            for (User user : userArrayList) {
                if (student.getId() == user.getId()) {
                    student.setType(user.getType());
                    student.setFirstname(user.getFirstname());
                    student.setLastname(user.getLastname());
                    student.setUsername(user.getUsername());
                    student.setPassword(user.getPassword());
                }
            }
        }
        
        this.adminArrayList = new ArrayList<>();
        this.lecturerArrayList = new ArrayList<>();
        for (User user : userArrayList) {
            ArrayList<Project> projects = new ArrayList<>();
            for (Project project : projectArrayList) {
                if (project.getCreatorId() == user.getId()) {
                    projects.add(project);
                }
            }

            if (user.isAdmin()) {
                ArrayList<Comment> comments = new ArrayList<>();
                for (Comment comment : commentArrayList) {
                    if (comment.getCommenterId() == user.getId()) {
                        comments.add(comment);
                    }
                }
                Admin admin = new Admin(
                    user.getId(),
                    user.getType(),
                    user.getFirstname(),
                    user.getLastname(),
                    user.getUsername(),
                    user.getPassword(),
                    projects,
                    comments
                );
                this.adminArrayList.add(admin);
            }
            else if (user.isLecturer()) {
                ArrayList<Student> students = new ArrayList<>();
                for (Student student : studentArrayList) {
                    if (student.getSupervisorId() == user.getId()) {
                        students.add(student);
                    }
                }
                Lecturer lecturer = new Lecturer(
                    user.getId(),
                    user.getType(),
                    user.getFirstname(),
                    user.getLastname(),
                    user.getUsername(),
                    user.getPassword(),
                    projects,
                    students
                );
                this.lecturerArrayList.add(lecturer);
            }
        }


    }

// =====================================================================================
//                                    Methods
// =====================================================================================

// ================================== Loaders ==========================================

    public ArrayList<User> loadUsers(File file) {
        ArrayList<User> users = new ArrayList<>();
        
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
            String line;
            
            bufferedReader.readLine();
            while ((line = bufferedReader.readLine()) != null) {
                String[] userData;
                userData = line.split(",");
                
                users.add(new User(
                    Integer.parseInt(userData[0]), // id
                    userData[1].charAt(0),         // type
                    userData[2],                   // firstname
                    userData[3],                   // lastname
                    userData[4],                   // username
                    userData[5]                    // password
                ));
            }

            bufferedReader.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

        return users;
    }

    public ArrayList<Student> loadStudents(File file) {
        ArrayList<Student> students = new ArrayList<>();
        
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
            String line;
            
            bufferedReader.readLine();
            while ((line = bufferedReader.readLine()) != null) {
                String[] studentData;
                studentData = line.split(",");
                
                Student student = new Student();
                student.setId(Integer.parseInt(studentData[0]));
                student.setSpecialization(studentData[1]);
                student.setProjectId(Integer.parseInt(studentData[2]));
                student.setSupervisorId(Integer.parseInt(studentData[3]));

                students.add(student);
            }

            bufferedReader.close();
            
        } catch (IOException e) {
            e.printStackTrace();
        }

        return students;
    }

    public ArrayList<Project> loadProjects(File file) {
        ArrayList<Project> projects = new ArrayList<>();
        
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
            String line;
            
            bufferedReader.readLine();
            while ((line = bufferedReader.readLine()) != null) {
                String[] projectData;
                projectData = line.split(";");

                projects.add(new Project(
                    Integer.parseInt(projectData[0]),                  // id
                    projectData[1],                                    // title
                    projectData[2],                                    // description
                    projectData[3],                                    // specialization
                    projectData[4].equals("Active") ? true : false,    // active
                    projectData[5].equals("Assigned") ? true : false,  // assigned
                    Integer.parseInt(projectData[6])                   // creatorId
                ));
            }

            bufferedReader.close();
            
        } catch (IOException e) {
            e.printStackTrace();
        }

        return projects;
    }

    public ArrayList<Comment> loadComments(File file) {
        ArrayList<Comment> comments = new ArrayList<>();
        
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
            String line;
            
            bufferedReader.readLine();
            while ((line = bufferedReader.readLine()) != null) {
                String[] commentData;
                commentData = line.split(";");

                comments.add(new Comment(
                    Integer.parseInt(commentData[0]),              // commentId
                    commentData[1],                                // comment
                    Integer.parseInt(commentData[2])               // commenterId
                ));
            }

            bufferedReader.close();
            
        } catch (IOException e) {
            e.printStackTrace();
        }

        return comments;
    }

    public ArrayList<Project> loadUserProjects(User user) {
        ArrayList<Project> projects = new ArrayList<>();
        
        for (Project project : this.projectArrayList)
        {
            if (project.getCreatorId() == user.getId()) {
                projects.add(project);
            }
        }

        return projects;
    }

    public ArrayList<Comment> loadUserComments(User user) {
        ArrayList<Comment> comments = new ArrayList<>();
        
        for (Comment comment : this.commentArrayList)
        {
            if (comment.getCommenterId() == user.getId()) {
                comments.add(comment);
            }
        }

        return comments;
    }

// ================================== Savers ==========================================

    public void saveUsers() {   
        try {
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(userDatabaseFile));
            String data = "";
            
            bufferedWriter.write("| ID | Type | Firstname | Lastname | Username | Password |" + "\n");
            for (User user : this.userArrayList) {
                data += (
                    user.getId() + "," +
                    user.getType() + "," +
                    user.getFirstname() + "," +
                    user.getLastname() + "," +
                    user.getUsername() + "," +
                    user.getPassword() + "\n"
                );
            }
            bufferedWriter.write(data);
            bufferedWriter.close();
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public void saveStudents() {
        try {
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(studentDatabaseFile));
            String data = "";
            
            bufferedWriter.write("| Student ID | Specialization | Project ID | Supervisor (Lecturer) ID |" + "\n");
            for (Student student : this.studentArrayList) {
                data += (
                    student.getId() + "," +
                    student.getSpecialization() + "," +
                    student.getProjectId() + "," +
                    student.getSupervisorId() + "\n"
                );
            }
            bufferedWriter.write(data);
            bufferedWriter.close();
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void saveProjects() {   
        try {
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(projectDatabaseFile));
            String data = "";
            
            bufferedWriter.write("| ID | Title | Description | Specialization | Active | Assigned | Creator ID |" + "\n");
            for (Project project : this.projectArrayList) {
                data += (
                    project.getId() + ";" +
                    project.getTitle() + ";" +
                    project.getDescription() + ";" +
                    project.getSpecialization() + ";"
                );

                if (project.getActive() == true) {
                    data += "Active" + ";";
                }
                else {
                    data += "Inactive" + ";";
                }

                if (project.getAssigned() == true) {
                    data += "Assigned" + ";";
                }
                else {
                    data += "Unassigned" + ";";
                }

                data += project.getCreatorId() + "\n";
            }
            bufferedWriter.write(data);
            bufferedWriter.close();
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void saveComments() {   
        try {
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(commentDatabaseFile));
            String data = "";
            
            bufferedWriter.write("| Project ID | Comment | Commenter ID |" + "\n");
            for (Comment comment : this.commentArrayList) {
                data += (
                    comment.getProjectId() + ";" +
                    comment.getComment() + ";" +
                    comment.getCommenterId() + "\n"
                );
            }
            bufferedWriter.write(data);
            bufferedWriter.close();
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

// ================================== Adders ==========================================

    public void addUser(User user) {
        this.userArrayList.add(user);
    }

    public void addAdmin(Admin admin) {
        this.adminArrayList.add(admin);
    }

    public void addLecturer(Lecturer lecturer) {
        this.lecturerArrayList.add(lecturer);
    }

    public void addStudent(Student student) {
        this.studentArrayList.add(student);
    }

    public void addProject(Project project) {
        this.projectArrayList.add(project);
    }

    public void addComment(Comment comment) {
        this.commentArrayList.add(comment);
    }


// ================================= Specials =========================================

    public int generateUserId() {
        return (this.userArrayList.get(this.userArrayList.size() - 1)).getId() + 1;
    }
    
    public int generateProjectId() {
        return (this.projectArrayList.get(this.projectArrayList.size() - 1)).getId() + 1;
    }

    public boolean userExists(String username) {
        for (User user : this.userArrayList) {
            if (username.equals(user.getUsername())) {
                return true;
            }
        }
        return false;
    }
    
// ================================= Special Getters =========================================
    
    public User getUserById(int id) {
        for (User user : userArrayList) {
            if (user.getId() == id) {
                return user;
            }
        }
        return null;
    }

    public Admin getAdminById(int id) {
        for (Admin admin : adminArrayList) {
            if (admin.getId() == id) {
                return admin;
            }
        }
        return null;
    }

    public Lecturer getLecturerById(int id) {
        for (Lecturer lecturer : lecturerArrayList) {
            if (lecturer.getId() == id) {
                return lecturer;
            }
        }
        return null;
    }

    public Student getStudentById(int id) {
        for (Student student : studentArrayList) {
            if (student.getId() == id) {
                return student;
            }
        }
        return null;
    }

    public Project getProjectById(int id) {
        for (Project project : projectArrayList) {
            if (project.getId() == id) {
                return project;
            }
        }
        return null;
    }

// ================================= Special Updaters =========================================

    public void updateUser(User user) {
        for (User u : userArrayList) {
            if (u.getId() == user.getId()) {
                u.setFirstname(user.getFirstname());
                u.setLastname(user.getLastname());
                u.setUsername(user.getUsername());
                u.setPassword(user.getPassword());
            }
        }
        saveUsers();
    }

    public void updateProject(Project project) {
        for (Project p : projectArrayList) {
            if (p.getId() == project.getId()) {
                p.setTitle(project.getTitle());
                p.setSpecialization(project.getSpecialization());
                p.setDescription(project.getDescription());
                p.setActive(project.getActive());
            }
        }
        saveProjects();
    }

    public void deleteUser(User user) {
        if (user.isAdmin()) {
            Admin admin = getAdminById(user.getId());
            adminArrayList.remove(admin);
        }
        else if (user.isLecturer()) {
            Lecturer lecturer = getLecturerById(user.getId());
            lecturerArrayList.remove(lecturer);
        }
        else if (user.isStudent()) {
            Student student = getStudentById(user.getId());
            studentArrayList.remove(student);
            if (student.getProjectId() != 0) {
                // Deactivate & unassign student's project if student is deleted.
                getProjectById(student.getProjectId()).setActive(false);
                getProjectById(student.getProjectId()).setAssigned(false);
            }
        }

        userArrayList.remove(user);
        unassignStudents(user);
        deactivateProjects(user);

        saveUsers();
        saveStudents();
        saveProjects();
    }

    public void deleteProject(Project project) {
        projectArrayList.remove(project);
        saveProjects();
    }

    /**
     * Deactivate projects if lecturer is deleted.
     */
    public void deactivateProjects(User user) {
        for (Project project : projectArrayList) {
            if (project.getCreatorId() == user.getId()) {
                project.setActive(false);
                project.setCreatorId(0);
            }
        }
        saveProjects();
    }

    /**
     * Unassign students from lecturer if lecturer is deleted.
     */
    public void unassignStudents(User lecturer) {
        for (Student s : studentArrayList) {
            if (s.getSupervisorId() == lecturer.getId()) {
                s.setSupervisorId(1);
            }
        }
        saveStudents();
    }

    public void assignProject(Student student, Project project) {
        for (Student s : studentArrayList) {
            if (s.getId() == student.getId()) {
                s.setProjectId(project.getId());
            }
        }
        for (Project p : projectArrayList) {
            if (p.getId() == project.getId()) {
                p.setAssigned(true);
            }
        }
        saveStudents();
        saveProjects();
    }

    public void unassignProject(Student student, Project project) {
        for (Student s : studentArrayList) {
            if (s.getId() == student.getId()) {
                s.setProjectId(0);
            }
        }
        for (Project p : projectArrayList) {
            if (p.getId() == project.getId()) {
                p.setAssigned(false);
            }
        }
        saveStudents();
        saveProjects();
        new Database();
    }
    
    public void transferProject(Project project, Lecturer lecturer) {
        for (Project p : projectArrayList) {
            if (p.getId() == project.getId()) {
                p.setCreatorId(lecturer.getId());
            }
        }
        saveProjects();
        saveUsers();
        new Database();
    }
}