package comp3350.group6.promise.presentation.Project.Invitation;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import comp3350.group6.promise.R;
import comp3350.group6.promise.application.Service;

public class SentInviteFragment extends Fragment {

    private NavController navController;

    public SentInviteFragment() {
        super(R.layout.fragment_sent_invite);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

            String recipientEmail = SentInviteFragmentArgs.fromBundle(getArguments()).getRecipientEmail();
            String recipientName = Service.accountUser.getNameByEmail(recipientEmail);

            // Get views from layout

            navController = NavHostFragment.findNavController(this);

            TextView messageView = view.findViewById( R.id.successSentMessage );
            Button goBack = view.findViewById( R.id.goBackProject );
            Button moreInvites = view.findViewById( R.id.someoneElse );

            // Update layout content

            messageView.setText("Your invite was sent to " + recipientName);

            // Update layout behaviours

            goBack.setOnClickListener( new View.OnClickListener() {
                @Override
                public void onClick( View view ) {
                    navController.popBackStack();
                    navController.navigateUp();
                }
            });

            moreInvites.setOnClickListener( new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    navController.navigateUp();
                }
            });

    }

}