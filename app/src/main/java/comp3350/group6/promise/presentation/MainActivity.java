package comp3350.group6.promise.presentation;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

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

        Button projectButton = findViewById( R.id.goToProject );
        projectButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToProject();
            }
        });
    }

    private void goToProject(){
        Intent intent = new Intent( this, ProjectActivity.class );
        startActivity( intent );
    }
}