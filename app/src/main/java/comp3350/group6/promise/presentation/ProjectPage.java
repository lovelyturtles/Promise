package comp3350.group6.promise.presentation;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import comp3350.group6.promise.R;

public class ProjectPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_project_page);

        ImageButton sentButton = findViewById( R.id.inviteButton );
        sentButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToInviteSent();
            }
        });
    }

    private void goToInviteSent(){
        Intent intent = new Intent( this, InviteSent.class );
        startActivity( intent );
    }
}