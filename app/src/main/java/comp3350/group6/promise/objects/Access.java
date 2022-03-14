package comp3350.group6.promise.objects;

import java.sql.Timestamp;

import lombok.Data;

@Data
public class Access {

    private int defaultId; // id of this access?
    private int projectId;
    private int userId;
    private String role;
    private Timestamp startTime;

    public Access(int defaultId, int projectId, int userId, String role, Timestamp startTime) {
        this.defaultId = defaultId;
        this.projectId = projectId;
        this.userId = userId;
        this.role = role;
        this.startTime = startTime;
    }

    public int getDefaultId() {
        return defaultId;
    }

    public void setDefaultId(int defaultId) {
        this.defaultId = defaultId;
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

}
