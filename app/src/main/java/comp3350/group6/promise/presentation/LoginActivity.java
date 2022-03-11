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