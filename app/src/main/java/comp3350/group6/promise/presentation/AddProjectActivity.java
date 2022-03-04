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
import comp3350.group6.promise.business.EmptyInputException;
import comp3350.group6.promise.business.ProjectService;
import comp3350.group6.promise.objects.Project;

public class AddProjectActivity extends AppCompatActivity{

    private static final ProjectService projectService = new ProjectService();

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

    // go back to dashboard
    private void back(){
        Intent intent = new Intent( this, DashboardActivity.class );
        startActivity( intent );
    }

    private void createProject(View v){
        String projectName = name.getText().toString();
        String projectDesc = description.getText().toString();

        // Users cannot add projects with no name
        try {
            projectService.insertProject(new Project(projectName, projectDesc));
            Intent intent = new Intent(this, DashboardActivity.class );
            startActivity( intent );
        } catch (EmptyInputException e) {
            Toast.makeText(this, "You need a name for your project.", Toast.LENGTH_LONG).show();
        }
    }
}
