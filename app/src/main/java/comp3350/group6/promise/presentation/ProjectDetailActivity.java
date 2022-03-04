package comp3350.group6.promise.presentation;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import comp3350.group6.promise.R;
import comp3350.group6.promise.business.ProjectService;
import comp3350.group6.promise.objects.Project;

public class ProjectDetailActivity extends AppCompatActivity {

    private static final ProjectService projectService = new ProjectService();

    private Project currentProject; // project that we are viewing
    private TextView projectTitle;
    private TextView projectDesc;
    private ImageView projectImg;
    private ImageButton moreButton;
    private ImageButton backButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_project_detail);

        // Create the project detail page based on the intent passed to it.
        if (getIntent() != null && getIntent().getExtras() != null) {
            int id = getIntent().getIntExtra("projectID", -1);
            if (id != -1){
                currentProject = projectService.getProjectByID(id);
            }
        }

        projectTitle = findViewById(R.id.project_page_title);
        projectDesc = findViewById(R.id.project_page_desc);
        projectImg = findViewById(R.id.imageView);
        moreButton = findViewById(R.id.project_page_more);
        backButton = findViewById(R.id.back_button);

        projectTitle.setText(currentProject.getProjectName());
        projectDesc.setText(currentProject.getStatement());

        backButton.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick( View view ) {
                back();
            }
        });
    }

    // go to the previous page
    private void back() {
        Intent intent = new Intent( this, DashboardActivity.class );
        startActivity( intent );
    }
}
