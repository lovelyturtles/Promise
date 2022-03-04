package comp3350.group6.promise.presentation;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import comp3350.group6.promise.R;

public class SentInviteActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView( R.layout.activity_sent_invite );

        TextView textView = findViewById( R.id.successSentMessage );
        Intent intent = getIntent();
        String recipientName = intent.getStringExtra( "userInput" );
        String message = "Your invite was sent to " + recipientName;
        textView.setText( message );

        Button goBack = findViewById( R.id.goBackProject );
        Button moreInvites = findViewById( R.id.someoneElse );

        goBack.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick( View view ) {
                backToProjects();
            }
        });

        moreInvites.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToRecipientDetails();
            }
        });

    }

    private void backToProjects(){

        Intent intent = new Intent( this, ProjectActivity.class );
        startActivity( intent );

    }

    private void goToRecipientDetails(){

        Intent intent = new Intent( this, RecipientInfoActivity.class );
        startActivity( intent );

    }

}