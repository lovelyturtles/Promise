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

    String recipientEmail;
    String recipientName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView( R.layout.activity_sent_invite );

        TextView textView = findViewById( R.id.successSentMessage );
        Intent intent = getIntent();
        recipientEmail = intent.getStringExtra( "emailInput" );
        recipientName = Service.accountUser.getNameByEmail( recipientEmail );
        String message = "Your invite was sent to " + recipientName;
        textView.setText( message );

        Button goBack = findViewById( R.id.goBackProject );
        Button moreInvites = findViewById( R.id.someoneElse );

        goBack.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick( View view ) {
                goToProjectPage();
            }
        });

        moreInvites.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToRecipientDetails();
            }
        });

    }

    private void goToRecipientDetails(){

        Intent intent = new Intent( this, RecipientInfoActivity.class );
        startActivity( intent );

    }

    private void goToProjectPage(){

        Intent intent = new Intent( this, ProjectActivity.class);
        startActivity( intent );

    }

}