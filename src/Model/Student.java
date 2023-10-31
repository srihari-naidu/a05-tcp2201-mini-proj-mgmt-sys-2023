package Model;

public class Student extends User {
// =====================================================================================
//                                    Attributes
// =====================================================================================

    private String specialization;
    private int projectId;
    private int supervisorId;

// =====================================================================================
//                                    Constructors
// =====================================================================================

    public Student() {}

    public Student(
        int id, char type, String firstname, String lastname, String username, String password,
        String specialization, int supervisorId
    ) {
        super(id, type, firstname, lastname, username, password);
        this.specialization = specialization;
        this.supervisorId = supervisorId;
    }

    // constructor to create student without project (need or no need?) - shawn

// =====================================================================================
//                                    Getters
// =====================================================================================

    public String getSpecialization() {
        return specialization;
    }

    public int getProjectId() {
        return projectId;
    }

    public int getSupervisorId() {
        return supervisorId;
    }

// =====================================================================================
//                                    Setters
// =====================================================================================

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }

    public void setSupervisorId(int supervisorId) {
        this.supervisorId = supervisorId;
    }

// =====================================================================================
//                                    Methods
// =====================================================================================
    
    @Override
    public String toString() {
        return (getId() + ": " + getFirstname() + " " + getLastname());
    }
}
