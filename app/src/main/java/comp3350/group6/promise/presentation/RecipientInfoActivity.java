package comp3350.group6.promise.presentation;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import comp3350.group6.promise.R;

public class RecipientInfoActivity extends AppCompatActivity {
    private Button sendInvite;
    EditText textName;
    String recipientName;

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
                //Get the userName that was submitted
                textName = findViewById( R.id.recipientNameHint );
                recipientName = textName.getText().toString();

                /*
                 * Check if the username is in our user database (in RecipientService)
                 *  if it isn't, open the error message dialog
                 *  if it is, call RecipientService and then goToSentPage()
                 *
                 */
                openDialog();
                //goToSentPage();

            }
        });
    }

    public void goToSentPage(){
        Intent intent = new Intent( this, SentInviteActivity.class );
        intent.putExtra( "userInput", recipientName );
        startActivity( intent );
    }

    public void openDialog(){

        NameErrorActivity errorDialog = new NameErrorActivity();
        errorDialog.show( getSupportFragmentManager(), "error message" );

    }
}