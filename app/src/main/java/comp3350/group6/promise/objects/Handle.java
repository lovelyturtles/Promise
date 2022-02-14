package comp3350.group6.promise.objects;

import java.sql.Timestamp;

import lombok.Data;

@Data
public class Handle {

    private int defaultId;
    private int taskId;
    private int userId;
    private Timestamp since;
}
