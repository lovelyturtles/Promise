package comp3350.group6.promise.presentation;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import comp3350.group6.promise.R;

public class AddProjectActivity extends AppCompatActivity{

    private EditText name;
    private EditText description;
    private ImageButton back;
    private Button createProject;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView( R.layout.activity_add_project );

        name = findViewById(R.id.project_name);
        description = findViewById(R.id.project_desc);
        back = findViewById(R.id.back_button);
        createProject = findViewById(R.id.create_project_button);

        back.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick( View view ) {
                back();
            }
        });

        createProject.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick( View view ) {
                createProject(view);
            }
        });
    }

    private void back(){
        Intent intent = new Intent( this, DashboardActivity.class );
        startActivity( intent );
    }

    private void createProject(View v){
        String projectName = name.getText().toString();
        String projectDesc = description.getText().toString();

        if(projectName.equals("")){
            Toast.makeText(this, "You need a name for your project.", Toast.LENGTH_LONG).show();
        } else {
            Intent intent = new Intent(v.getContext(), DashboardActivity.class );
            intent.putExtra("name", projectName);
            intent.putExtra("desc", projectDesc);
            startActivity( intent );
        }
    }
}
