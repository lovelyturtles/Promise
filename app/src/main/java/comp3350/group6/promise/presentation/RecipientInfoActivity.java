package comp3350.group6.promise.presentation;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import comp3350.group6.promise.R;

public class RecipientInfoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView( R.layout.activity_recipient_info );

        //when the user enters the person they want to invite, see if that userName exists
        //and if it does, add it to the notifications database
        Button sendInvite = findViewById( R.id.sendInviteButton );
        sendInvite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }
}