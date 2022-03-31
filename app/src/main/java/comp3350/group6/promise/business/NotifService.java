package comp3350.group6.promise.business;

import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;

import comp3350.group6.promise.application.CurrentSession;
import comp3350.group6.promise.application.Service;
import comp3350.group6.promise.objects.Access;
import comp3350.group6.promise.objects.Account;
import comp3350.group6.promise.objects.Exceptions.AccountDNException;
import comp3350.group6.promise.objects.Exceptions.PersistenceException;
import comp3350.group6.promise.objects.Notification;
import comp3350.group6.promise.objects.enumClasses.AccessRole;
import comp3350.group6.promise.objects.enumClasses.NotifType;
import comp3350.group6.promise.persistence.AccountDao;
import comp3350.group6.promise.persistence.NotifDao;
import comp3350.group6.promise.persistence.hsqldb.NotifImp;

public class NotifService {

    private final NotifDao notification = new NotifImp();

    public void invite( String theirEmail, int projectID ) throws AccountDNException {

        //check if their account exists
        if( Service.accounts.accountExists( theirEmail ) ){

            //set the sender of the request as currentUser
            Account sender = CurrentSession.currentUser;
            //set the receiver of the request as the account associated to the email parameter
            Account recipient = Service.accounts.getAccountByEmail( theirEmail );

            //add this request to the Notification database
            notification.addNotif(sender.getUserID(), projectID, recipient.getUserID(), NotifType.INVITE);

        }

        //if it doesn't, throw a Account Does Not Exist Exception
        else
            throw new AccountDNException( "There is no account with this email" );

    }

    /*
     * If the user is requesting access to this project, we have to go and find the user
     * that has the privileges necessary to grant access and send the request to them
     */
    public void request( int projectID ){

        int senderID = CurrentSession.currentUser.getUserID();
        //find out who has the privileges to accept/deny requests (for now, that's the creator)
        List<Integer> creatorRole = Service.accesses.getRoleByProjectID( projectID, AccessRole.CREATOR );
        int creatorID = creatorRole.get( 0 );
        //add this request to the notifications database
        notification.addNotif(senderID, projectID, creatorID, NotifType.REQUEST);

    }

    private void remove( Notification removeThis ){

        notification.removeNotif( removeThis );

    }

    public void accept( Notification acceptThis ){

        Service.accesses.insertAccess( new Access( acceptThis.getProjectID(), acceptThis.getRecipientID() ) );
        //now that we've updated their access, we can remove the notification
        remove( acceptThis );
    }

    public void reject( Notification rejectThis ){
        //they've rejected the invite so we just remove their notification
        remove( rejectThis );
    }

    public ArrayList<Notification> getNotifs( int recipientID ){

        return notification.getNotifs( recipientID );

    }

}
