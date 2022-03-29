package comp3350.group6.promise.business;

import android.widget.EditText;

import comp3350.group6.promise.application.CurrentSession;
import comp3350.group6.promise.application.Service;
import comp3350.group6.promise.objects.Account;
import comp3350.group6.promise.persistence.AccountDao;
import comp3350.group6.promise.persistence.NotifDao;

public class NotifService {

    private NotifDao notification;

    public void invite( String theirEmail ){

        //handle null case somehow

        //check if the account exists
        if( Service.accounts.accountExists( theirEmail ) ){

            Account thisAccount = CurrentSession.currentUser;
            //Account theirAccount = Service.accounts.getAccountByEmail( theirEmail );

        }


    }

}
