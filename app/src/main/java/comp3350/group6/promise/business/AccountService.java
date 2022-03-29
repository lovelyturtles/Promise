package comp3350.group6.promise.business;

import android.widget.EditText;

import comp3350.group6.promise.application.CurrentSession;
import comp3350.group6.promise.application.Service;
import comp3350.group6.promise.objects.Account;
import comp3350.group6.promise.objects.Exceptions.DuplicateEmailException;
import comp3350.group6.promise.objects.Exceptions.EmptyEmailException;
import comp3350.group6.promise.objects.Exceptions.EmptyPasswordException;
import comp3350.group6.promise.objects.Exceptions.LoginErrorException;
import comp3350.group6.promise.persistence.AccountDao;
import comp3350.group6.promise.persistence.hsqldb.AccountImp;
import lombok.extern.java.Log;

public class AccountService {

    private static final AccountDao accountDao = new AccountImp();
    private static AccountService instance;

    private AccountService() {}

    //can I change this method to have a void return statement and just use Exception handling??
    public int createAccount( String email, String password, String name, String introduction ) throws Exception{

        int userID = Service.users.addUser( name, introduction );
        userID = accountDao.createAccount(email, password, userID);

        if( userID == -1 )
            throw new DuplicateEmailException( "An account with this email already exists" );

        //should probably remove the userID return statement and replace tests with exception
        return userID;

    }

    public boolean changePassword( int userID, String oldPassword, String newPassword ) throws Exception {

        return 1 == accountDao.changePassword( userID, oldPassword, newPassword );

    }

    public boolean accountExists( String email ) {

        return accountDao.accountExists( email );

    }

    public boolean passwordsMatch( String email, String password ) {

        return accountDao.passwordsMatch( email, password );

    }

    public Account getAccountByEmail( String email ){

        return accountDao.getAccountByEmail( email );

    }

    public void setCurrentAccount( String email, String password ) throws LoginErrorException {

//        System.out.println("email");
//        System.out.println("passowrd");
//        System.out.println("accountExists(email): " + accountExists(email));
//        System.out.println("passwordsMatch(email,password): " + passwordsMatch(email, password));
        //for us to set the current user, the account has to exist and the password must match
        if( accountExists( email ) && passwordsMatch( email, password ) )
            CurrentSession.currentUser = accountDao.getAccountByEmail( email );

        //whether the account doesn't exist or the passwords don't match, we send the same error
        else
            throw new LoginErrorException( "Wrong password/email combination" );


    }

    public void register( EditText textEmail, EditText textName, EditText textPass, EditText textIntro ) throws Exception {

        String email = textEmail.getText().toString();
        String name = textName.getText().toString();
        String password = textPass.getText().toString();
        String intro = textIntro.getText().toString();

        if( email.equals( "" ) )
            throw new EmptyEmailException( "Please enter an email address." );

        if( password.equals( "" ) )
            throw new EmptyPasswordException( "Please enter a password." );

        //If no Exceptions were thrown, create this account and set it as current user
        try{

            createAccount( email, password, name, intro );
            setCurrentAccount( email, password );

        }

        catch( DuplicateEmailException e ){
            throw new DuplicateEmailException( e.getMessage() );
        }

        catch( LoginErrorException e ){
            throw new LoginErrorException( "Wrong password/email combination" );
        }

    }

    public void login( EditText emailText, EditText passwordText ) throws Exception {

        String email = emailText.getText().toString();
        System.out.println("email");
        String password = passwordText.getText().toString();
        System.out.println("password");

        if( email.equals( "" ) )
            throw new EmptyEmailException( "Please enter an email address" );

        if( password.equals( "" ) )
            throw new EmptyPasswordException( "Please enter a password" );

        //If no Exceptions are thrown, set this as the current user
        try{
            setCurrentAccount( email, password );
        }

        catch( LoginErrorException e ){
            throw new LoginErrorException( "Wrong password/email combination" );
        }

    }

}
