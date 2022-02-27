package comp3350.group6.promise.presentation;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import comp3350.group6.promise.R;

public class MainActivity extends AppCompatActivity {
    //What does this do? Do we need it?
    public static void run(){
        System.out.println("hello");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button createButton = findViewById( R.id.goToCreate );
        Button loginButton  = findViewById( R.id.goToLogin );

        createButton.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick( View view ) {
                goToCreate();
            }
        });

        loginButton.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick( View view ) {
                goToLogin();
            }
        });

//        Button projectButton = findViewById( R.id.goToProjects);
//        projectButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                goToProjects();
//            }
//        });
    }

    private void goToCreate(){

        Intent intent = new Intent( this, CreateActivity.class );
        startActivity( intent );

    }

    private void goToLogin(){

        Intent intent = new Intent( this, LoginActivity.class );
        startActivity( intent );

    }

//    private void goToProjects(){
//        Intent intent = new Intent( this, ProjectList.class );
//        startActivity( intent );
//    }

}