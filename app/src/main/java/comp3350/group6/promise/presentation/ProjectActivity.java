/*
 * We've modified our code not to use this class along with Send
 * something but if they want to see how it works they can run a certain activity
 */

package comp3350.group6.promise.presentation;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import comp3350.group6.promise.R;

public class ProjectActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView( R.layout.activity_project );

        Button invite = findViewById( R.id.inviteButton );
        invite.setOnClickListener( new View.OnClickListener() {
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
}