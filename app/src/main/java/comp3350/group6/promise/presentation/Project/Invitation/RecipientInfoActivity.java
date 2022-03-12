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
import comp3350.group6.promise.objects.CurrentSession;

public class RecipientInfoActivity extends AppCompatActivity {
    private Button sendInvite;
    EditText textName;
    String recipientEmail;

    @Override
    protected void onCreate( Bundle savedInstanceState ) {
        super.onCreate(savedInstanceState);
        setContentView( R.layout.activity_recipient_info );

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
                //Get the userName that was submitted
                textName = findViewById( R.id.recipientEmailHint );
                recipientEmail = textName.getText().toString();

                /*
                 * Check if the username is in our user database
                 *  if it isn't, open the error message dialog
                 *  if it is, call RecipientService and then goToSentPage()
                 *
                 */
                if(CurrentSession.accounts.accountExists( recipientEmail ) ) {
                    goToSentPage();
                }
                else {
                    openUserDoesNotExistDialog();
                }

            }
        });
    }

    public void goToSentPage(){
        Intent intent = new Intent( this, SentInviteActivity.class );
        intent.putExtra( "userInput", recipientEmail);
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

}