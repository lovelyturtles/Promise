package comp3350.group6.promise.presentation.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import comp3350.group6.promise.R;
import comp3350.group6.promise.objects.CurrentSession;
import comp3350.group6.promise.presentation.DashboardActivity;
import comp3350.group6.promise.presentation.EmailErrorActivity;
import comp3350.group6.promise.presentation.PassErrorActivity;


public class LoginFormFragment extends Fragment {

    private Button submitButtonView;
    private EditText emailInputView;
    private EditText passwordInputView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_login_form, container, false);

        emailInputView = (EditText) view.findViewById( R.id.loginEmailInput );
        passwordInputView = (EditText) view.findViewById( R.id.loginPasswordInput );
        submitButtonView = (Button) view.findViewById( R.id.signInButton );

        submitButtonView.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick( View view ) {
                handleSubmitClick();
            }
        });

        return view;
    }

    private void handleSubmitClick() {
        String email = emailInputView.getText().toString();
        String password = emailInputView.getText().toString();

        //Check if the email is in our "database"
        if( CurrentSession.accounts.accountExists( email ) ) {

            //check if this is the right password
            if ( CurrentSession.accounts.passwordsMatch( email, password ) ) {

                //if the passwords match, set this as the current user and go to their dashboard
                assert( CurrentSession.accounts.setCurrentAccount( email, password ) );
                navigateToDashboard();

            }

            else {
                openPasswordErrorDialogue(); //if the passwords don't match, open the password dialog
            }

        }

        else {
            openEmailErrorDialogue();
        }
    }

    private void navigateToDashboard(){

        Intent intent = new Intent( getActivity(), DashboardActivity.class );
        startActivity( intent );

    }

    private void openEmailErrorDialogue() {

        EmailErrorActivity errorDialog = new EmailErrorActivity();
        errorDialog.show( getActivity().getSupportFragmentManager(), "email error message" );

    }

    private void openPasswordErrorDialogue() {

        PassErrorActivity errorDialog = new PassErrorActivity();
        errorDialog.show( getActivity().getSupportFragmentManager(), "password error message" );

    }

}