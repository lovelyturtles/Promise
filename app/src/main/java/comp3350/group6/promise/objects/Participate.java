package comp3350.group6.promise.objects;

import java.sql.Timestamp;

import lombok.Data;

@Data
public class Participate {

    private int defaultId;
    private int projectId;
    private int userId;
    private String role;
    private Timestamp startTime;

}
