package comp3350.group6.promise.presentation.Project;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import comp3350.group6.promise.R;
import comp3350.group6.promise.application.Service;
import comp3350.group6.promise.business.EmptyInputException;
import comp3350.group6.promise.objects.Access;
import comp3350.group6.promise.application.CurrentSession;
import comp3350.group6.promise.objects.Project;
import comp3350.group6.promise.presentation.User.DashboardActivity;

public class CreateProjectActivity extends AppCompatActivity{

    private Toolbar toolbarView;
    private EditText nameInputView;
    private EditText descriptionInputView;
    private Button submitButtonView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView( R.layout.activity_create_project);

        nameInputView = findViewById(R.id.task_name_input);
        submitButtonView = findViewById(R.id.submit_task_button);

        // Keep "next" keyboard action while allowing multiline text
        descriptionInputView = findViewById(R.id.task_description_input);
        descriptionInputView.setImeOptions(EditorInfo.IME_ACTION_NEXT);
        descriptionInputView.setRawInputType(InputType.TYPE_CLASS_TEXT);

        submitButtonView.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick( View view ) {
                createProject(view);
            }
        });

        toolbarView = findViewById(R.id.task_toolBar);
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
            Project newProject = Service.projects.insertProject(new Project(projectName, projectDesc));
            Access newAccess = new Access(newProject.getProjectID(), CurrentSession.currentUser.getUserID()); //TODO: Maybe change current session
            Service.accesses.insertAccess(newAccess);

            // go back to dashboard
            Intent intent = new Intent(this, DashboardActivity.class );
            startActivity( intent );

        } catch (EmptyInputException e) {
            // Users cannot add projects with no name
            Toast.makeText(this, "You need a name for your project.", Toast.LENGTH_LONG).show();
        }
    }
}