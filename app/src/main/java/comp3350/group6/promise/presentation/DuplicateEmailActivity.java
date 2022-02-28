package comp3350.group6.promise.presentation;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatDialogFragment;

public class DuplicateEmailActivity extends AppCompatDialogFragment {

    @Override
    public Dialog onCreateDialog( Bundle savedInstanceState ) {
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

    /*
     * If user wants to try logging in, take them to log in page
     */
    private void goToLogin(){
        Intent intent = new Intent( this.getActivity(), LoginActivity.class );
        startActivity( intent );
    }

}
