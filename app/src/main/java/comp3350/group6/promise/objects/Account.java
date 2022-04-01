package comp3350.group6.promise.objects;

import lombok.Data;

@Data
public class Account {

    private String email;
    private String password;
    private int userId;

    public Account( String email, String password, int userId ){

        this.email = email;
        this.password = password;
        this.userId = userId;

    }

    public String getEmail(){
        return email;
    }

    public String getPassword(){
        return password;
    }

    public int getUserID(){
        return userId;
    }

    public void setPassword( String password ){

        this.password = password;

    }

}
