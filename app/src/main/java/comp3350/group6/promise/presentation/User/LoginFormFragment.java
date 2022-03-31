package comp3350.group6.promise.presentation.User;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import comp3350.group6.promise.R;
import comp3350.group6.promise.application.Service;
import comp3350.group6.promise.objects.Exceptions.EmptyEmailException;
import comp3350.group6.promise.objects.Exceptions.EmptyPasswordException;
import comp3350.group6.promise.objects.Exceptions.LoginErrorException;


public class LoginFormFragment extends Fragment {

    private Button submitButtonView;
    private EditText emailInputView;
    private EditText passwordInputView;

    String userEmail;
    String userPass;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_login_form, container, false);

        emailInputView = ( EditText ) view.findViewById( R.id.loginEmailInput );
        passwordInputView = ( EditText ) view.findViewById( R.id.loginPasswordInput );
        submitButtonView = ( Button ) view.findViewById( R.id.signInButton );

        submitButtonView.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick( View view ) {

                //convert email and password to strings
                userEmail = emailInputView.getText().toString();
                userPass = passwordInputView.getText().toString();

                //Send input to the business layer
                try{
                    Service.accounts.login( userEmail, userPass );
                    //if no Exception was thrown, we'll go back to the user's home page
                    goToUserHome();
                }
                catch( LoginErrorException e ){
                    openLoginErrorDialog();
                }

                catch( EmptyEmailException e ){
                    System.out.println("Empty email");
                }

                catch( EmptyPasswordException e ){
                    System.out.println("Empty password");
                }

                catch( Exception e ){
                    e.printStackTrace();
                }

            }

        });

        return view;

    }

    private void goToUserHome(){
        //welcome message with user's name
        String message = "Welcome back, " + Service.accountUser.getNameByEmail( userEmail );;
        Intent intent = new Intent( getActivity(), UserActivity.class );
        intent.putExtra( "welcomeName", message );
        startActivity( intent );

    }

    private void openLoginErrorDialog() {

        LoginErrorDialogueFragment errorDialogue = new LoginErrorDialogueFragment();
        errorDialogue.show(getActivity().getSupportFragmentManager(), "loginError");

    }

    public static class LoginErrorDialogueFragment extends DialogFragment {

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

            builder.setMessage( "The email and password provided you provided does not match a registered user." )
                    .setPositiveButton("Try Again", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick( DialogInterface dialogInterface, int i ) {
                            //do nothing. Let them edit the username and try again
                        }
                    })
                    .setNeutralButton("Create Account", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            Intent intent = new Intent( getActivity(), RegisterActivity.class );
                            startActivity( intent );
                        }
                    });

            return builder.create();
        }

    }

}