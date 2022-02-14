package comp3350.group6.promise.objects;

import java.sql.Timestamp;
import java.util.ArrayList;

import lombok.Data;

@Data
public class Task {

    private int taskId;
    private String title;
    private String description;
    private int priority;
    private int statusNum;
    private int projectId;
    private Timestamp createdTime;
    private Timestamp deadline;
    private Timestamp estimatedStartTime;
    private Timestamp estimatedEndTime;

}
