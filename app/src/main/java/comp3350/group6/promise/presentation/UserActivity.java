package comp3350.group6.promise.presentation;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import comp3350.group6.promise.R;

public class UserActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView( R.layout.activity_user );

        Button projectButton = findViewById( R.id.goToProjects);
        projectButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToProjects();
            }
        });

    }

    private void goToProjects(){
        Intent intent = new Intent( this, ProjectList.class );
        startActivity( intent );
    }

}