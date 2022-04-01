package comp3350.group6.promise.persistence;

import java.util.ArrayList;

import comp3350.group6.promise.objects.Notification;
import comp3350.group6.promise.objects.enumClasses.NotifType;

public interface NotifDao {

    //add a notification with these attributes to the table
    void addNotif( int userID, int projectID, int recipientID, int type ) throws Exception;

    //remove the notification with these attributes from the table
    void removeNotif( int userID, int projectID, int recipientID ) throws Exception;

    //get all the notifications that were sent to this recipientID
    ArrayList<Notification> getNotifs(int recipientID ) throws Exception;

}
