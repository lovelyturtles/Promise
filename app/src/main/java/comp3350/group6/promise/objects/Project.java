package comp3350.group6.promise.objects;

import java.sql.Timestamp;

import lombok.Data;

@Data
public class Project {

    private static int count;
    private int projectID;
    private String projectName;
    private String statement;
    private int statusNum;
    private Timestamp createdTime;
    private Timestamp estimatedEndTime;


    //public Project(String projectName, String statement, Timestamp estimatedEndTime)
    public Project(String projectName, String statement){
        this.projectID = count++;
        this.projectName = projectName;
        
        if (statement.equals("")){
            this.statement = "project statement";
        }
        this.statement = statement;
        this.statusNum = 0; //TODO: set to default status
        
        Timestamp time = new Timestamp(System.currentTimeMillis());
        this.createdTime = time;
        this.estimatedEndTime = time;
    }

    public Project(int projectID, String projectName, String statement, int statusNum, Timestamp createdTime, Timestamp estimatedEndTime) {
        this.projectID = projectID;
        this.projectName = projectName;
        this.statement = statement;
        this.statusNum = statusNum;
        this.createdTime = createdTime;
        this.estimatedEndTime = estimatedEndTime;
    }
    

    public int getProjectID(){
        return this.projectID;
    }

    public String getProjectName(){
        return this.projectName;
    }

    public String getStatement(){
        return this.statement;
    }

    public int getStatusNum(){
        return this.statusNum;
    }

    public Timestamp getCreatedTime(){
        return this.createdTime;
    }

    public Timestamp getEstimatedEndTime(){
        return this.estimatedEndTime;
    }

    public void setProjectName(String newName){
        this.projectName = newName;
    }

    public void setStatement(String statement){
        this.statement = statement;
    }

    public void setStatusNum(int status){
        this.statusNum = status;
    }

    public void setEstimatedEndTime(Timestamp time){
        this.estimatedEndTime = time;
    } 
}