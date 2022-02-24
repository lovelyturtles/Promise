package comp3350.group6.promise.presentation;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

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

    }

}