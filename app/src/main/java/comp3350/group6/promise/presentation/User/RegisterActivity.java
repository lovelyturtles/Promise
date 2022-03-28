package comp3350.group6.promise.presentation.User;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDialogFragment;

import comp3350.group6.promise.R;
import comp3350.group6.promise.application.CurrentSession;
import comp3350.group6.promise.application.Service;
import comp3350.group6.promise.presentation.MainActivity;

public class RegisterActivity extends AppCompatActivity {

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
        setContentView( R.layout.activity_register);

        // Keep "next" keyboard action while allowing multiline text
        textIntro = findViewById( R.id.introInput );
        textIntro.setImeOptions(EditorInfo.IME_ACTION_DONE);
        textIntro.setRawInputType(InputType.TYPE_CLASS_TEXT);

        registerButton = findViewById( R.id.registerButton );

        registerButton.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //Get the email that was submitted
                textEmail = findViewById( R.id.email_input);
                userEmail = textEmail.getText().toString();

                //Check if the email is already registered
                if( Service.accounts.accountExists( userEmail ) )
                    openDuplicateDialog();  //dialog message that this email is already in use

                else {

                    //let them create an account
                    //get the name they submitted
                    textName  = findViewById( R.id.name_input);
                    userName  = textName.getText().toString();

                    //get the password they want to use
                    textPass = findViewById( R.id.password_input);
                    userPassword = textPass.getText().toString();

                    //get the introduction they submitted
                    userIntro = textIntro.getText().toString();

                    try {
                        //create the account
                        Service.accounts.createAccount( userEmail, userPassword, userName, userIntro );
                        //check that the email is in our database
                        if( Service.accounts.accountExists( userEmail ) ) {
                            //make sure the password was set correctly
                            if ( Service.accounts.passwordsMatch( userEmail, userPassword ) ) {
                                //set this as the current user and go to their home page
                                assert (Service.accounts.setCurrentAccount(userEmail, userPassword));
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

        Intent intent = new Intent( this, DashboardActivity.class );
        startActivity( intent );

    }

    private void openDuplicateDialog(){

        DuplicateEmailDialogFragment errorDialog = new DuplicateEmailDialogFragment();
        errorDialog.show( getSupportFragmentManager(), "email exists error message" );

    }

    public static class DuplicateEmailDialogFragment extends AppCompatDialogFragment {

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState ) {
            AlertDialog.Builder builder = new AlertDialog.Builder( getActivity() );
            builder.setMessage( "This email address is already associated to an account. Please log in or try again." )
                    .setPositiveButton("Try Again", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick( DialogInterface dialogInterface, int i ) {
                            //do nothing. Let them edit their email address and try again
                        }
                    })
                    .setNegativeButton("Log In", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick( DialogInterface dialogInterface, int i ) {
                            goToLogin();
                        }
                    });

            return builder.create();
        }

        private void goToLogin(){
            Intent intent = new Intent( this.getActivity(), MainActivity.class );
            startActivity( intent );
        }

    }

    public static class EmailErrorActivity extends AppCompatDialogFragment {

        @Override
        public Dialog onCreateDialog( Bundle savedInstanceState ) {
            AlertDialog.Builder builder = new AlertDialog.Builder( getActivity() );
            builder.setMessage( "There is no account with this email address. Please create an account or try again." )
                    .setPositiveButton("Try Again", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick( DialogInterface dialogInterface, int i ) {
                            //do nothing. Let them edit the username and try again
                        }
                    })
                    .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick( DialogInterface dialogInterface, int i ) {
                            goHome();
                        }
                    })
                    .setNeutralButton("Create Account", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            goToCreate();
                        }
                    });

            return builder.create();
        }

        /*
         * If the user clicks on "Create Account" take them to that page
         */
        private void goToCreate(){

            Intent intent = new Intent( this.getActivity(), RegisterActivity.class );
            startActivity( intent );

        }

        /*
         * If user decides to give up and cancel, take them back to the home page
         */
        private void goHome(){
            Intent intent = new Intent( this.getActivity(), MainActivity.class );
            startActivity( intent );
        }

    }

}