package comp3350.group6.promise.objects;

import java.sql.Timestamp;

import lombok.Data;

@Data
public class Account {

    private String email;
    private String password;
    private int userId;
}
