package comp3350.group6.promise.presentation;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import comp3350.group6.promise.R;
import comp3350.group6.promise.objects.CurrentSession;

public class LoginActivity extends AppCompatActivity {

    private Button signIn;
    private EditText textEmail;
    private EditText textPass;
    private String userEmail;
    private String userPassword;

    @Override
    protected void onCreate( Bundle savedInstanceState ) {
        super.onCreate(savedInstanceState);
        setContentView( R.layout.activity_login );

        signIn = findViewById( R.id.signInButton );
        signIn.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick( View view ) {

                //Get the email that was submitted
                textEmail = findViewById( R.id.loginEmailInput );
                userEmail = textEmail.getText().toString();

                //Check if the email is in our "database"
                if( CurrentSession.accounts.accountExists( userEmail ) ) {
                    //If the email is in our database, get the password that was submitted
                    textPass = findViewById( R.id.loginPasswordInput );
                    userPassword = textPass.getText().toString();
                    //check if this is the right password
                    if ( CurrentSession.accounts.passwordsMatch( userEmail, userPassword ) ) {
                        //if the passwords match, set this as the current user and go to their home page
                        assert( CurrentSession.accounts.setCurrentAccount( userEmail, userPassword ) );
                        goToUserHome();
                    }
                    else
                        openPassDialog(); //if the passwords don't match, open the password dialog

                }

                else
                    openEmailDialog();

            }
        });
    }

    private void goToUserHome(){

        String message = "Welcome back, " + userEmail;
        Intent intent = new Intent( this, UserActivity.class );
        intent.putExtra( "userInputEmail", message );
        startActivity( intent );

    }

    private void openEmailDialog(){

        EmailErrorActivity errorDialog = new EmailErrorActivity();
        errorDialog.show( getSupportFragmentManager(), "email error message" );

    }

    private void openPassDialog(){

        PassErrorActivity errorDialog = new PassErrorActivity();
        errorDialog.show( getSupportFragmentManager(), "password error message" );

    }

}