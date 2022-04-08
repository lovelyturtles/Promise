package comp3350.group6.promise.presentation.Project.Invitation;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatDialogFragment;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import comp3350.group6.promise.R;
import comp3350.group6.promise.application.Service;
import comp3350.group6.promise.business.AccountUserService;
import comp3350.group6.promise.objects.AccountUser;
import comp3350.group6.promise.presentation.User.AccountUserAdapter;

public class InviteFormFragment extends Fragment implements AccountUserAdapter.OnUserClickListener {

    private MutableLiveData<List<AccountUser>> searchListLive = new MutableLiveData<List<AccountUser>>(new ArrayList<AccountUser>());
    private MutableLiveData<List<AccountUser>> selectedListLive = new MutableLiveData<List<AccountUser>>(new ArrayList<AccountUser>());
    private List<AccountUser> searchList = searchListLive.getValue();
    private List<AccountUser> selectedList = selectedListLive.getValue();
    private EditText searchInput;
    private RecyclerView searchListRecycler;
    private RecyclerView selectedListRecycler;
    private Button sendButton;

    private NavController navController;

    public InviteFormFragment() {
        super(R.layout.fragment_recipient_info);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        //Get the projectID that was passed in

        int projectId = InviteFormFragmentArgs.fromBundle(getArguments()).getProjectId();

        /*
         * When the user enters the username of the person they want to invite,
         * see if that userName exists (is in the database).
         * If it does, add it to the Notifications database,
         * if it doesn't, give them an error message.
         */

        navController = NavHostFragment.findNavController(this);
        searchInput = view.findViewById( R.id.userSearch);
        sendButton = view.findViewById( R.id.sendInviteButton );
        searchListRecycler = view.findViewById( R.id.user_search_result_list );
        selectedListRecycler = view.findViewById( R.id.user_search_selection_list );

        AccountUserAdapter searchListAdapter = new AccountUserAdapter(getContext(), searchList,this);
        searchListRecycler.setLayoutManager( new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL,false));
        searchListRecycler.setAdapter(searchListAdapter);

        searchInput.addTextChangedListener(new TextWatcher() {
            @Override public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) { }
            @Override public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) { }
            @Override
            public void afterTextChanged(Editable editable) {
                String searchTerm = editable.toString();
                searchList.clear();
                searchList.addAll(Service.accountUser.search(searchTerm));
                searchListAdapter.notifyDataSetChanged();
                System.out.println("Searching for users matching " + searchTerm + searchListAdapter.toString());
            }
        });

//        sendButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                //Get the email address that was submitted
//                String recipientEmail = userSearchInput.getText().toString();
//
//                // Invite this user to our project
//                try{
//                    Service.notifications.invite( recipientEmail , projectId );
//
//                    //If the invitation was successfully sent, go to the sent page
//                    NavDirections action = InviteFormFragmentDirections.inviteSuccess(recipientEmail, projectId);
//                    navController.navigate(action);
//                }
//
//                catch( AccountDNException e ){
//                    openUserDoesNotExistDialog();
//                }
//
//                catch( DuplicateNotificationException e ){
//                    openAlreadyNotifiedDialog();
//                }
//
//            }
//        });
    }

    public void openUserDoesNotExistDialog(){
        UserDoesNotExistDialogFragment errorDialog = new UserDoesNotExistDialogFragment();
        errorDialog.show( getParentFragmentManager(), "error message" );
    }

    @Override
    public void onUserClick(int position) {

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
            NavController navController = NavHostFragment.findNavController(this);
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
                            navController.navigateUp();
                        }
                    });

            return builder.create();
        }

    }

}