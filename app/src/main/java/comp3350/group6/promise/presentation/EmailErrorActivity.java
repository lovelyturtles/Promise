package comp3350.group6.promise.presentation;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatDialogFragment;

public class EmailErrorActivity extends AppCompatDialogFragment {

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

        Intent intent = new Intent( this.getActivity(), CreateActivity.class );
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
