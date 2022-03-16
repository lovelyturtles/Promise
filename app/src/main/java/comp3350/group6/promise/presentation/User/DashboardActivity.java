package comp3350.group6.promise.presentation.User;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import comp3350.group6.promise.R;
import comp3350.group6.promise.business.ProjectService;
import comp3350.group6.promise.objects.Project;
import comp3350.group6.promise.presentation.Project.CreateProjectActivity;
import comp3350.group6.promise.presentation.Project.ProjectActivity;
import comp3350.group6.promise.util.ProjectAdapter;

// Reference for Class: https://www.geeksforgeeks.org/cardview-using-recyclerview-in-android-with-example/
public class DashboardActivity extends AppCompatActivity implements ProjectAdapter.ViewHolder.OnProjectClickListener {

    private static final ProjectService projectService = new ProjectService();

    private List<Project> projects;

    private RecyclerView projectRecyclerView;
    private ProjectAdapter projectAdapter;
    private ImageButton moreButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        projectRecyclerView = findViewById(R.id.projectRecyclerView);
        moreButton = findViewById(R.id.moreButton);

        projects = projectService.getProjects();

        projectAdapter = new ProjectAdapter(this, projects, this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        projectRecyclerView.setLayoutManager(linearLayoutManager);
        projectRecyclerView.setAdapter(projectAdapter);

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
        // refresh list of projects
        projectAdapter.notifyDataSetChanged();
    }

    private void goToAddProject(){
        Intent intent = new Intent( this, CreateProjectActivity.class );
        startActivity( intent );
    }

    @Override
    public void onProjectClick(int position) {
        Project clickedProject = projects.get(position);
        Intent intent = new Intent(this, ProjectActivity.class);
        intent.putExtra("projectID", clickedProject.getProjectID());
        startActivity(intent);
    }
}