package comp3350.group6.promise.presentation.Project.Invitation;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDialogFragment;

import comp3350.group6.promise.R;
import comp3350.group6.promise.application.Service;
import comp3350.group6.promise.objects.Exceptions.AccountDNException;
import comp3350.group6.promise.objects.Exceptions.DuplicateNotificationException;

public class RecipientInfoActivity extends AppCompatActivity {
    private Button sendInvite;
    EditText textEmail;
    String recipientEmail;
    int projectID;

    @Override
    protected void onCreate( Bundle savedInstanceState ) {
        super.onCreate(savedInstanceState);
        setContentView( R.layout.activity_recipient_info );

        //Get the projectID that was passed in
        Intent intent = getIntent();
        projectID = intent.getIntExtra( "projectID", -1 );

        /*
        * When the user enters the username of the person they want to invite,
        * see if that userName exists (is in the database).
        * If it does, add it to the Notifications database,
        * if it doesn't, give them an error message.
        */

        sendInvite = findViewById( R.id.sendInviteButton );
        sendInvite.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Get the email address that was submitted
                textEmail = findViewById( R.id.recipientEmailHint );
                recipientEmail = textEmail.getText().toString();

                /* Invite this user to our project */
                try{
                    Service.notifications.invite( recipientEmail , projectID );
                    //If the invitation was successfully sent, go to the sent page
                    goToSentPage();
                }

                catch( AccountDNException e ){
                    openUserDoesNotExistDialog();
                }

                catch( DuplicateNotificationException e ){
                    openAlreadyNotifiedDialog();
                }

            }
        });
    }

    public void goToSentPage(){
        Intent intent = new Intent( this, SentInviteActivity.class );
        Bundle extras = new Bundle();
        extras.putInt( "projectID2", projectID );
        extras.putString( "emailInput", recipientEmail );
        intent.putExtras( extras );
        startActivity( intent );
    }

    public void openUserDoesNotExistDialog(){
        UserDoesNotExistDialogFragment errorDialog = new UserDoesNotExistDialogFragment();
        errorDialog.show( getSupportFragmentManager(), "error message" );
    }

    public static class UserDoesNotExistDialogFragment extends AppCompatDialogFragment {

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState ) {
            AlertDialog.Builder builder = new AlertDialog.Builder( getActivity() );
            builder.setMessage("This email is not associated with a registered user.")
                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            //do nothing. Let them edit the username and try again
                        }
                    })
                    .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick( DialogInterface dialogInterface, int i ) {
                            getActivity().finish();
                        }
                    });

            return builder.create();
        }

    }

    public void openAlreadyNotifiedDialog(){
        AlreadyNotifiedDialogFragment errorDialog = new AlreadyNotifiedDialogFragment();
        errorDialog.show( getSupportFragmentManager(), "error message" );
    }

    public static class AlreadyNotifiedDialogFragment extends AppCompatDialogFragment {

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState ) {
            AlertDialog.Builder builder = new AlertDialog.Builder( getActivity() );
            builder.setMessage("This user has already been invited to this project. " +
                    "Would you like to try another user?")
                    .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            //do nothing. Let them edit the email and try again
                        }
                    })
                    .setNegativeButton("No", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick( DialogInterface dialogInterface, int i ) {
                            getActivity().finish();
                        }
                    });

            return builder.create();
        }

    }

}