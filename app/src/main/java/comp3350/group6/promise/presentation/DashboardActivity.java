package comp3350.group6.promise.presentation;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import comp3350.group6.promise.R;
import comp3350.group6.promise.business.ProjectService;
import comp3350.group6.promise.objects.Project;
import comp3350.group6.promise.util.ProjectAdapter;

// Reference for Class: https://www.geeksforgeeks.org/cardview-using-recyclerview-in-android-with-example/
public class DashboardActivity extends AppCompatActivity implements ProjectAdapter.Viewholder.OnCardListener{

    private static final ProjectService projectService = new ProjectService();

    private List<Project> projects;

    private RecyclerView projectRV;
    private ProjectAdapter projectAdapter;
    private LinearLayoutManager linearLayoutManager;
    private ImageButton moreButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard_page);

        projectRV = findViewById(R.id.projectRecyclerView);
        moreButton = findViewById(R.id.moreButton);

        projects = projectService.getProjects();

        projectAdapter = new ProjectAdapter(this, projects, this::onCardClick);
        linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);

        projectRV.setLayoutManager(linearLayoutManager);
        projectRV.setAdapter(projectAdapter);

        moreButton.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick( View view ) {
                goToAddProject();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();

        if (getIntent() != null && getIntent().getExtras() != null) { // returned from create project activity
            projectService.insertProject(new Project(getIntent().getStringExtra("name"), getIntent().getStringExtra("desc")));

            //remove intents after use
            getIntent().removeExtra("name");
            getIntent().removeExtra("desc");
        }

        projectAdapter.notifyDataSetChanged();
    }

    private void goToAddProject(){
        Intent intent = new Intent( this, AddProjectActivity.class );
        startActivity( intent );
    }

    @Override
    public void onCardClick(int position) {
        Project clickedProject = projects.get(position);

        Intent intent = new Intent(this, ProjectDetailActivity.class);
        intent.putExtra("projectID", clickedProject.getProjectID());
        startActivity(intent);
    }
}