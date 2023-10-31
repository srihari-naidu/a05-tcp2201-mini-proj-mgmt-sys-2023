package Model;

/**
 * Comment data model.
 */
public class Comment {
    
// =====================================================================================
//                                    Attributes
// =====================================================================================

    private int projectId;
    private String comment;
    private int commenterId;

// =====================================================================================
//                                    Constructors
// =====================================================================================

    public Comment() {}

    public Comment(int projectId, String comment, int commenterId) {
        this.projectId = projectId;
        this.comment = comment;
        this.commenterId = commenterId;
    }

// =====================================================================================
//                                    Getters
// =====================================================================================

    public int getProjectId() {
        return projectId;
    }

    public String getComment() {
        return comment;
    }

    public int getCommenterId() {
        return commenterId;
    }

// =====================================================================================
//                                    Setters
// =====================================================================================

    public void setProjectId(int commenterId) {
        this.commenterId = commenterId;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public void setCommenterId(int commenterId) {
        this.commenterId = commenterId;
    }

// =====================================================================================
//                                    Methods
// =====================================================================================

    @Override
    public String toString() {
        return this.projectId + " " + this.comment;
    }
}
