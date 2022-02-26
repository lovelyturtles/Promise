package comp3350.group6.promise.objects;

import java.sql.Timestamp;
import java.util.UUID;

import lombok.Data;

@Data
public class Task {
    private static int count;
    private int taskId;
    private String title;
    private String description;
    private int priority;
    private int statusNum;
    private int projectId;
    private Timestamp createdTime;
    private Timestamp estimatedEndTime;
    private Timestamp deadline;


    public Task() {
    }

    public Task(String title, String description, int priority, int statusNum, int projectId, Timestamp estimatedEndTime, Timestamp deadline) {
        this.taskId = count++;
        this.title = title;
        this.description = description;
        this.priority = priority;
        this.statusNum = statusNum;
        this.projectId = projectId;
        this.createdTime = new Timestamp(System.currentTimeMillis());
        this.estimatedEndTime = estimatedEndTime;
        this.deadline = deadline;
    }

    public Task(int taskId, String title, String description, int priority, int statusNum, int projectId, Timestamp createdTime, Timestamp estimatedEndTime, Timestamp deadline) {
        this.taskId = taskId;
        this.title = title;
        this.description = description;
        this.priority = priority;
        this.statusNum = statusNum;
        this.projectId = projectId;
        this.createdTime = createdTime;
        this.estimatedEndTime = estimatedEndTime;
        this.deadline = deadline;
    } // for DB calling

    public int getTaskId() {
        return taskId;
    }

    public void setTaskId(int taskId) {
        this.taskId = taskId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public int getStatusNum() {
        return statusNum;
    }

    public void setStatusNum(int statusNum) {
        this.statusNum = statusNum;
    }

    public int getProjectId() {
        return projectId;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }

    public Timestamp getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Timestamp createdTime) {
        this.createdTime = createdTime;
    }

    public Timestamp getDeadline() {
        return deadline;
    }

    public void setDeadline(Timestamp deadline) {
        this.deadline = deadline;
    }

    public Timestamp getEstimatedEndTime() {
        return estimatedEndTime;
    }

    public void setEstimatedEndTime(Timestamp estimatedEndTime) {
        this.estimatedEndTime = estimatedEndTime;
    }
}