package comp3350.group6.promise.presentation;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import comp3350.group6.promise.R;
import comp3350.group6.promise.util.DBConnectorUtil;

public class MainActivity extends AppCompatActivity {

    public static void run(){
        System.out.println("hello");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageButton projectsPageButton = findViewById( R.id.projects );
        projectsPageButton.setOnClickListener(new View.OnClickListener() {
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