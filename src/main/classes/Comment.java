package main.classes;

import main.models.UserDataModel;

import java.util.Date;

public class Comment {
    private String commentId;
    private String posterId;
    private String details;
    private Date createdAt;

    public Comment(String commentId, String posterId, String details, Date createdAt) {
        this.commentId = commentId;
        this.posterId = posterId;
        this.details = details;
        this.createdAt = createdAt;
    }

    public String getCommentId() {
        return commentId;
    }

    public void setCommentId(String commentId) {
        this.commentId = commentId;
    }

    public String getPosterId() {
        return posterId;
    }

    public void setPosterId(String posterId) {
        this.posterId = posterId;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public String toString()
    {
        String posterName = CurrentSession.userDataModel.getUserById(getPosterId()).getUsername();

        return "(" + posterName + ") : " + getDetails();
    }
}
