package Model;

public class Project {
// =====================================================================================
//                                    Attributes
// =====================================================================================

    private int id;
    private String title;
    private String description;
    private String specialization;
    private boolean active;
    private boolean assigned;
    private int creatorId;

// =====================================================================================
//                                    Constructors
// =====================================================================================

    public Project() {}

    public Project(
        int id, String title, String description, String specialization, 
        boolean active, boolean assigned, int creatorId
    ) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.specialization = specialization;
        this.active = active;
        this.assigned = assigned;
        this.creatorId = creatorId;
    }

// =====================================================================================
//                                    Getters
// =====================================================================================

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return this.description;
    }

    public String getSpecialization() {
        return specialization;
    }

    public boolean getActive() {
        return active;
    }

    public boolean getAssigned() {
        return assigned;
    }

    public int getCreatorId() {
        return creatorId;
    }

// =====================================================================================
//                                    Setters
// =====================================================================================

    public void setId(int id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public void setAssigned(boolean assigned) {
        this.assigned = assigned;
    }
    
    public void setCreatorId(int creatorId) {
        this.creatorId = creatorId;
    }

// =====================================================================================
//                                    Methods
// =====================================================================================

    @Override
    public String toString() {
        return (this.id + ": " + this.title);
    }

}


