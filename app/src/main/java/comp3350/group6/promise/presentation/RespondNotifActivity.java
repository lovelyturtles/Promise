package comp3350.group6.promise.presentation;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatDialogFragment;

public class RespondNotifActivity extends AppCompatDialogFragment {

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState ) {
        AlertDialog.Builder builder = new AlertDialog.Builder( getActivity() );
        //figure out how to add the specifics here
        builder.setMessage("userName has sent you a request!")
                .setPositiveButton("Accept", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        //Add this userName to the Participate database
                    }
                })
                .setNegativeButton("Reject", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick( DialogInterface dialogInterface, int i ) {
                        //Remove this userName from the Notifications database
                    }
                });

        return builder.create();
    }

}
