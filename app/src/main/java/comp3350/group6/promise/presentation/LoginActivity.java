package comp3350.group6.promise.presentation;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import comp3350.group6.promise.R;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView( R.layout.activity_login );

        Button signinButton = findViewById( R.id.signInButton );
        signinButton.setOnClickListener(new View.OnClickListener() {
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