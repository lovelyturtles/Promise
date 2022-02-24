package comp3350.group6.promise.presentation;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import comp3350.group6.promise.R;

public class RecipientInfoActivity extends AppCompatActivity {
    private Button sendInvite;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView( R.layout.activity_recipient_info );

        /*
        * When the user enters the username of the person they want to invite,
        * see if that userName exists (is in the database).
        * If it does, add it to the Notifications database,
        * if it doesn't, give them an error message.
        */
        sendInvite = findViewById( R.id.sendInviteButton );
        sendInvite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Get the username that was submitted and check it against our user database
                openDialog();
            }
        });
    }

    public void openDialog(){

        NameErrorActivity errorDialog = new NameErrorActivity();
        errorDialog.show( getSupportFragmentManager(), "error message" );

    }
}