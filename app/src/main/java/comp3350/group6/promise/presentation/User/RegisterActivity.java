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
import comp3350.group6.promise.application.Service;
import comp3350.group6.promise.objects.Exceptions.DuplicateEmailException;
import comp3350.group6.promise.objects.Exceptions.EmptyEmailException;
import comp3350.group6.promise.objects.Exceptions.EmptyPasswordException;
import comp3350.group6.promise.objects.Exceptions.LoginErrorException;
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
        textIntro.setImeOptions(EditorInfo.IME_ACTION_NEXT);
        textIntro.setRawInputType(InputType.TYPE_CLASS_TEXT);

        registerButton = findViewById( R.id.registerButton );

        registerButton.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick( View view ) {

                try {
                    //Get their email and turn it into a string
                    textEmail = findViewById(R.id.email_input);
                    userEmail = textEmail.getText().toString();

                    //Get their name and turn it into a string
                    textName = findViewById(R.id.name_input);
                    userName = textName.getText().toString();

                    //Get their password and turn it into a string
                    textPass = findViewById(R.id.password_input);
                    userPassword = textPass.getText().toString();

                    //Turn their into into a string
                    userIntro = textIntro.getText().toString();

                    //Send all this information to the business layer
                    Service.accounts.register( userEmail, userName, userPassword, userIntro );

                    //If we don't get any Exceptions, we can go to the user's home page
                    goToUserHome();

                }

                catch( DuplicateEmailException e ){
                    openDuplicateDialog();
                }

                catch( EmptyEmailException e ){
                    //do a dialog or something here
                }

                catch( EmptyPasswordException e ){
                    //ditto above
                }

                catch( LoginErrorException e ){
                    //maybe use the one in LoginFormFragment
                }

                catch( Exception e ){
                    e.printStackTrace();
                }

            }

        });

    }

    private void goToUserHome(){

        //change this when I get AccountUser working
        String message = "Welcome, " + textEmail.getText().toString();
        Intent intent = new Intent( this, UserActivity.class );
        intent.putExtra( "userInputEmail", message );
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

    /* So I guess delete this since we're now using password/email generic error message? */
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
            //change this back UserActivity.class
            Intent intent = new Intent( this.getActivity(), MainActivity.class );
            startActivity( intent );

        }

    }

}