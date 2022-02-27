package comp3350.group6.promise.presentation;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import comp3350.group6.promise.R;

public class CreateActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView( R.layout.activity_create );

        Button registerButton = findViewById( R.id.registerButton );
        registerButton.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToUserHome();
            }
        });
    }

    private void goToUserHome(){

        Intent intent = new Intent( this, UserActivity.class );
        startActivity( intent );

    }
}