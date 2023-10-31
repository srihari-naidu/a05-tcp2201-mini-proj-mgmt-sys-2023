package Model;

public class User {
// =====================================================================================
//                                    Attributes
// =====================================================================================

    private int id;
    private char type;
    private String firstname;
    private String lastname;
    private String username;
    private String password;

// =====================================================================================
//                                    Constructors
// =====================================================================================

    public User() {}

    public User(int id, char type, String firstname, String lastname, String username, String password) {
        this.id = id;
        this.type = type;
        this.firstname = firstname;
        this.lastname = lastname;
        this.username = username;
        this.password = password;
    }

// =====================================================================================
//                                    Getters
// =====================================================================================

    public int getId() {
        return id;
    }

    public char getType() {
        return type;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getName() {
        return firstname + " " + lastname;
    }

// =====================================================================================
//                                    Setters
// =====================================================================================

    public void setId(int id) {
        this.id = id;
    }

    public void setType(char type) {
        this.type = type;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

// =====================================================================================
//                                    Methods
// =====================================================================================

    @Override
    public String toString() {
        return this.type + " " + this.firstname + " " + this.lastname;
    }

    public boolean isAdmin() {
        return (this.type == 'a');
    }

    public boolean isLecturer() {
        return (this.type == 'l');
    }

    public boolean isStudent() {
        return (this.type == 's');
    }
}
