package comp3350.group6.promise.objects;

import java.sql.Timestamp;

import lombok.Data;

@Data
public class Project {

    private int projectId;
    private String ProjectName;
    private String statement;
    private int statusNum;
    private Timestamp createdTime;
    private Timestamp estimatedEndTime;
}
