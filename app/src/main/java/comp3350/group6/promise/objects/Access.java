package comp3350.group6.promise.objects;

import java.sql.Time;
import java.sql.Timestamp;

import lombok.Data;

/**
 * @author Dani
 */
@Data
public class Access {

    private int projectId;
    private int userId;
    private String role;
    private Timestamp startTime;

    public Access(int projectId, int userId) {
        this.projectId = projectId;
        this.userId = userId;
        this.role = "Member";
        this.startTime = new Timestamp(System.currentTimeMillis());
    }

    public Access(int projectId, int userId, String role, Timestamp startTime) {
        this.projectId = projectId;
        this.userId = userId;
        this.role = role;
        this.startTime = startTime;
    }

    public int getProjectId() {
        return projectId;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Timestamp getStartTime() {
        return startTime;
    }

    public void setStartTime(Timestamp time) { this.startTime = time; }

}
