package comp3350.group6.promise.presentation.Project.Invitation;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatDialogFragment;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavDirections;

import comp3350.group6.promise.R;
import comp3350.group6.promise.application.Service;
import comp3350.group6.promise.objects.Exceptions.AccountDNException;
import comp3350.group6.promise.objects.Exceptions.DuplicateNotificationException;

public class RecipientInfoFragment extends Fragment {

    EditText emailInputView;
    private Button sendButton;

    public RecipientInfoFragment() {
        super(R.layout.fragment_recipient_info);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        //Get the projectID that was passed in

        int projectId = RecipientInfoFragmentArgs.fromBundle(getArguments()).getProjectId();

        /*
         * When the user enters the username of the person they want to invite,
         * see if that userName exists (is in the database).
         * If it does, add it to the Notifications database,
         * if it doesn't, give them an error message.
         */

        emailInputView = view.findViewById( R.id.recipientEmailHint );
        sendButton = view.findViewById( R.id.sendInviteButton );

        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Get the email address that was submitted
                String recipientEmail = emailInputView.getText().toString();

                /* Invite this user to our project */
                try{
                    Service.notifications.invite( recipientEmail , projectId );

                    //If the invitation was successfully sent, go to the sent page
                    NavDirections action = RecipientInfoFragmentDirections.inviteSuccess(recipientEmail);
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

    public void openUserDoesNotExistDialog(){
        UserDoesNotExistDialogFragment errorDialog = new UserDoesNotExistDialogFragment();
        errorDialog.show( getParentFragmentManager(), "error message" );
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
        errorDialog.show( getParentFragmentManager(), "error message" );
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