package Model;

import java.util.*;

/**
 * Admin data model.
 */
public class Admin extends User {

// =====================================================================================
//                                    Attributes
// =====================================================================================

    private ArrayList<Project> projects;
    private ArrayList<Comment> comments;

// =====================================================================================
//                                    Constructors
// =====================================================================================

    public Admin() {}

    public Admin(
        int id, char type, String firstname, String lastname, String username, String password,
        ArrayList<Project> projects, ArrayList<Comment> comments) 
    {
        super(id, type, firstname, lastname, username, password);
        this.projects = projects;
        this.comments = comments;
    }

// =====================================================================================
//                                    Getters
// =====================================================================================

    public ArrayList<Project> getProjects() {
        return this.projects;
    }
    
    public ArrayList<Comment> getComments() {
        return this.comments;
    }

// =====================================================================================
//                                    Setters
// =====================================================================================

    public void setProjects(ArrayList<Project> projects) {
        this.projects = projects;
    }

    public void setComments(ArrayList<Comment> comments) {
        this.comments = comments;
    }

// =====================================================================================
//                                    Methods
// ===================================================================================== 
    
    @Override
    public String toString() {
        return (getId() + ": " + getFirstname() + " " + getLastname());
    }

}
