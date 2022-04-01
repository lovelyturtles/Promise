package comp3350.group6.promise.presentation.User;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import comp3350.group6.promise.R;
import comp3350.group6.promise.presentation.Notification.NotificationsActivity;

public class UserActivity extends AppCompatActivity {

    @Override
    protected void onCreate( Bundle savedInstanceState ) {

        super.onCreate(savedInstanceState);

        setContentView( R.layout.activity_user );

        TextView textView = findViewById( R.id.welcomeMessage );
        Intent intent = getIntent();
        String displayMessage = intent.getStringExtra( "welcomeName" );
        textView.setText( displayMessage );

        Button projectButton = findViewById( R.id.goToProjects );
        Button notifButton = findViewById( R.id.goToNotifs );
        projectButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick( View view ) {
                goToProjects();
            }
        });

        notifButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToNotifs();
            }
        });

    }

    private void goToProjects(){
        Intent intent = new Intent( this, DashboardActivity.class );
        startActivity( intent );
    }

    private void goToNotifs(){
        Intent intent = new Intent( this, NotificationsActivity.class );
        startActivity( intent );
    }


}
