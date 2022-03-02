package comp3350.group6.promise.objects;


import lombok.Data;

@Data
public class User {
    private int UserId;
    private String name;
    private String introduction;

    public User( int userId, String name, String introduction ) {
        UserId = userId;
        this.name = name;
        this.introduction = introduction;
    }

    public int getUserID(){
        return UserId;
    }

    public String getName(){
        return name;
    }

    public String getIntro(){
        return introduction;
    }

    public void setName( String name ){
        this.name = name;
    }

    public void setIntro( String introduction ){
        this.introduction = introduction;
    }

}
