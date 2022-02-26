package comp3350.group6.promise.presentation;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatDialogFragment;

public class NameErrorActivity extends AppCompatDialogFragment{

    @Override
    public Dialog onCreateDialog( Bundle savedInstanceState ) {
        AlertDialog.Builder builder = new AlertDialog.Builder( getActivity() );
        builder.setMessage("This username does not exist. Please try again.")
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        //do nothing. Let them edit the username and try again
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick( DialogInterface dialogInterface, int i ) {
                        goToProject();
                    }
        });

        return builder.create();
    }

    /*
    * If user decides to give up and cancel, take them back to the project page
    */
    private void goToProject(){
        Intent intent = new Intent( this.getActivity(), ProjectActivity.class );
        startActivity( intent );
    }


}
