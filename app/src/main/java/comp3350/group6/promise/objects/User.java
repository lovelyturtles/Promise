package comp3350.group6.promise.objects;


import java.sql.Timestamp;

import lombok.Data;

@Data
public class User {
    private int UserId;
    private String name;
    private String introduction;
    private Timestamp birthday;
}
