package comp3350.group6.promise.objects;

import comp3350.group6.promise.objects.enumClasses.NotifType;
import lombok.Data;

@Data
public class Notification {
    private int userID;         //userID of the user that sent the notification
    private int projectID;      //ID of the project this notification refers to
    private int recipientID;    //userID of the user that's receiving the notification
    private NotifType type;     //A notification is either a request or an invite

    public Notification( int userID, int projectID, int recipientID, NotifType type ){

        this.userID      = userID;
        this.projectID   = projectID;
        this.recipientID = recipientID;
        this.type        = type;

    }

    int getUserID(){
        return userID;
    }

    int getProjectID(){
        return projectID;
    }

    int getRecipientID(){
        return recipientID;
    }

    NotifType getType(){
        return type;
    }

}
