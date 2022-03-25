package comp3350.group6.promise.presentation.Project;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import comp3350.group6.promise.R;
import comp3350.group6.promise.business.AccessService;
import comp3350.group6.promise.business.EmptyInputException;
import comp3350.group6.promise.business.ProjectService;
import comp3350.group6.promise.objects.Access;
import comp3350.group6.promise.objects.CurrentSession;
import comp3350.group6.promise.objects.Project;
import comp3350.group6.promise.presentation.User.DashboardActivity;

public class CreateProjectActivity extends AppCompatActivity{

    private static final AccessService accessService = new AccessService();

    private Toolbar toolbarView;
    private EditText nameInputView;
    private EditText descriptionInputView;
    private Button submitButtonView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView( R.layout.activity_create_project);

        nameInputView = findViewById(R.id.project_name_input);
        descriptionInputView = findViewById(R.id.project_description_input);
        submitButtonView = findViewById(R.id.create_project_button);

        submitButtonView.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick( View view ) {
                createProject(view);
            }
        });

        toolbarView = findViewById(R.id.toolbar);
        setSupportActionBar(toolbarView);

        toolbarView.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void createProject(View v){
        String projectName = nameInputView.getText().toString();
        String projectDesc = descriptionInputView.getText().toString();

        try {
            Project newProject = ProjectService.getInstance().insertProject(new Project(projectName, projectDesc));
            Access newAccess = new Access(newProject.getProjectID(), CurrentSession.currentUser.getUserID()); //TODO: Maybe change current session
            accessService.insertAccess(newAccess);

            // go back to dashboard
            Intent intent = new Intent(this, DashboardActivity.class );
            startActivity( intent );

        } catch (EmptyInputException e) {
            // Users cannot add projects with no name
            Toast.makeText(this, "You need a name for your project.", Toast.LENGTH_LONG).show();
        }
    }
}