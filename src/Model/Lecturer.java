package Model;

import java.util.ArrayList;

public class Lecturer extends User {

// =====================================================================================
//                                    Attributes
// =====================================================================================

    private ArrayList<Project> projects;
    private ArrayList<Student> students;

// =====================================================================================
//                                    Constructors
// =====================================================================================

    public Lecturer() {}

    public Lecturer(
        int id, char type, String firstname, String lastname, String username, String password,
        ArrayList<Project> projects, ArrayList<Student> students) 
    {
        super(id, type, firstname, lastname, username, password);
        this.projects = projects;
        this.students = students;
    }

// =====================================================================================
//                                    Getters
// =====================================================================================

    public ArrayList<Project> getProjects() {
        return this.projects;
    }

    public ArrayList<Student> getStudents() {
        return students;
    }

// =====================================================================================
//                                    Setters
// =====================================================================================

    public void setProjects(ArrayList<Project> projects) {
        this.projects = projects;
    }

    public void setStudents(ArrayList<Student> students) {
        this.students = students;
    }

// =====================================================================================
//                                    Methods
// ===================================================================================== 

    @Override
    public String toString() {
        return (getId() + ": " + getFirstname() + " " + getLastname());
    }

}
