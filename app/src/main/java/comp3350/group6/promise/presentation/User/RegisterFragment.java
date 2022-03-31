package comp3350.group6.promise.presentation.User;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatDialogFragment;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.NavDirections;
import androidx.navigation.fragment.NavHostFragment;

import comp3350.group6.promise.R;
import comp3350.group6.promise.application.Service;

public class RegisterFragment extends Fragment {

    private EditText emailInput;
    private EditText nameInput;
    private EditText passwordInput;
    private EditText introInput;
    private Button submitButton;

    private NavController navController;

    public RegisterFragment() {
        super(R.layout.fragment_register);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {

        // Get views from layout

        navController = NavHostFragment.findNavController(this);

        emailInput = view.findViewById(R.id.email_input);
        nameInput = view.findViewById( R.id.name_input);
        passwordInput = view.findViewById( R.id.password_input);
        introInput = view.findViewById(R.id.intro_input);
        submitButton = view.findViewById(R.id.register_button);

        // Update layout behaviours

        introInput.setImeOptions(EditorInfo.IME_ACTION_NEXT);
        introInput.setRawInputType(InputType.TYPE_CLASS_TEXT);

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View buttonView) {

                String email = emailInput.getText().toString();
                String name  = nameInput.getText().toString();
                String password = passwordInput.getText().toString();
                String intro = introInput.getText().toString();

                createAccount(email, name, password, intro);

            }
        });

    }

    private void createAccount(String email, String name, String password, String intro) {
        // Inform user if email is in use
        if( Service.accounts.accountExists( email ) ) {
            openDuplicateDialog();
        }
        // Otherwise create the account
        else {
            try {
                Service.accounts.createAccount( email, password, name, intro );
                // Verify account was created with the provided information
                if( Service.accounts.accountExists( email ) ) {
                    if ( Service.accounts.passwordsMatch( email, password ) ) {
                        // Verify session account was updated
                        if (Service.accounts.setCurrentAccount(email, password)) {
                            NavDirections action = RegisterFragmentDirections.registerSuccess();
                            navController.navigate(action);
                        }
                    }

                }
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void openDuplicateDialog(){
        DuplicateEmailDialogFragment errorDialog = new DuplicateEmailDialogFragment();
        errorDialog.show(getParentFragmentManager(), "email exists error message" );
    }

    public static class DuplicateEmailDialogFragment extends AppCompatDialogFragment {

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState ) {

            AlertDialog.Builder builder = new AlertDialog.Builder( getActivity() );
            NavController navController = NavHostFragment.findNavController(this);

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
                            navController.navigateUp();
                        }
                    });

            return builder.create();
        }

    }

}