package comp3350.group6.promise.presentation;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import comp3350.group6.promise.R;
import comp3350.group6.promise.objects.CurrentSession;

public class CreateActivity extends AppCompatActivity {

    private Button registerButton;
    private EditText textEmail;
    private EditText textPass;
    private EditText textName;
    private EditText textIntro;

    private String userEmail;
    private String userPassword;
    private String userName;
    private String userIntro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView( R.layout.activity_create );

        // Keep "next" keyboard action while allowing multiline text
        textIntro = findViewById( R.id.introInput );
        textIntro.setImeOptions(EditorInfo.IME_ACTION_DONE);
        textIntro.setRawInputType(InputType.TYPE_CLASS_TEXT);

        registerButton = findViewById( R.id.registerButton );

        registerButton.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //Get the email that was submitted
                textEmail = findViewById( R.id.emailInput );
                userEmail = textEmail.getText().toString();

                //Check if the email is already registered
                if( CurrentSession.accounts.accountExists( userEmail ) )
                    openDuplicateDialog();  //dialog message that this email is already in use

                else {

                    //let them create an account
                    //get the name they submitted
                    textName  = findViewById( R.id.firstNameInput );
                    userName  = textName.getText().toString();

                    //get the password they want to use
                    textPass = findViewById( R.id.passwordInput );
                    userPassword = textPass.getText().toString();

                    //get the introduction they submitted
                    userIntro = textIntro.getText().toString();

                    try {
                        //create the account
                        CurrentSession.accounts.createAccount( userEmail, userPassword, userName, userIntro );
                        //check that the email is in our database
                        if( CurrentSession.accounts.accountExists( userEmail ) ) {
                            //make sure the password was set correctly
                            if ( CurrentSession.accounts.passwordsMatch( userEmail, userPassword ) ) {
                                //set this as the current user and go to their home page
                                assert (CurrentSession.accounts.setCurrentAccount(userEmail, userPassword));
                                goToUserHome();
                            }

                        }
                    }
                    catch (Exception e) {
                        e.printStackTrace();
                    }

                }

            }
        });
    }

    private void goToUserHome(){

        String message = "Welcome, " + userEmail;
        Intent intent = new Intent( this, UserActivity.class );
        intent.putExtra( "userInputEmail", message );
        startActivity( intent );

    }

    private void openDuplicateDialog(){

        DuplicateEmailActivity errorDialog = new DuplicateEmailActivity();
        errorDialog.show( getSupportFragmentManager(), "email exists error message" );

    }
}