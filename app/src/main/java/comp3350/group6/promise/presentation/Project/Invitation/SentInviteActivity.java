package comp3350.group6.promise.presentation.Project.Invitation;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import comp3350.group6.promise.R;
import comp3350.group6.promise.application.Service;
import comp3350.group6.promise.presentation.Project.Invitation.RecipientInfoActivity;
import comp3350.group6.promise.presentation.Project.ProjectActivity;

public class SentInviteActivity extends AppCompatActivity {

    String message;
    String recipientEmail;
    String recipientName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView( R.layout.activity_sent_invite );

        TextView textView = findViewById( R.id.successSentMessage );

        Bundle extras = getIntent().getExtras();
        if( extras != null ){
            int projectID = extras.getInt( "projectID2" );
            System.out.println("projectID2: " + projectID );
            recipientEmail = extras.getString( "emailInput" );
            recipientName = Service.accountUser.getNameByEmail( recipientEmail );
            message = "Your invite was sent to " + recipientName;
            textView.setText( message );

            Button goBack = findViewById( R.id.goBackProject );
            Button moreInvites = findViewById( R.id.someoneElse );

            goBack.setOnClickListener( new View.OnClickListener() {
                @Override
                public void onClick( View view ) {
                    goToProjectPage( projectID );
                }
            });

            moreInvites.setOnClickListener( new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    goToRecipientDetails( projectID );
                }
            });
        }
        else {
            message = "Something went wrong. Please click your back button";
            textView.setText( message );
        }

    }

    private void goToRecipientDetails( int projectID ){

        Intent intent = new Intent( this, RecipientInfoActivity.class );
        intent.putExtra( "projectID", projectID );
        startActivity( intent );

    }

    private void goToProjectPage( int projectID ){

        Intent intent = new Intent( this, ProjectActivity.class);
        intent.putExtra( "projectID", projectID );
        startActivity( intent );

    }

}