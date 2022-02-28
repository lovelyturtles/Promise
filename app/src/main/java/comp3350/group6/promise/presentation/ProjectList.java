package comp3350.group6.promise.presentation;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import comp3350.group6.promise.R;
import comp3350.group6.promise.business.ProjectService;
import comp3350.group6.promise.objects.Project;
import comp3350.group6.promise.util.ProjectAdapter;

// Reference for Class: https://www.geeksforgeeks.org/cardview-using-recyclerview-in-android-with-example/
public class ProjectList extends AppCompatActivity implements View.OnClickListener{

    private ProjectService projectService;
    private ArrayList<Project> projects;

    private RecyclerView projectRV;
    private ProjectAdapter projectAdapter;
    private LinearLayoutManager linearLayoutManager;
    private ImageButton sendButton;
    private ImageButton moreButton;

    private String newProjectName;
    private String newProjectDesc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard_page);

        projectRV = findViewById(R.id.projectRecyclerView);
        sendButton = findViewById(R.id.inviteButton);
        moreButton = findViewById(R.id.moreButton);

        //projectService = new ProjectService();
//        projects = (ArrayList<Project>) projectService.getProjects();
        projects = new ArrayList<Project>();

        // Testing
        projects.add(new Project("Project A","This is a sample description for this very cool project."));
        projects.add(new Project("Project B", "This is a sample description for this very cool project."));
        projects.add(new Project("Project C", "This is a sample description for this very cool project."));
        projects.add(new Project("Project D", "This is a sample description for this very cool project."));
        projects.add(new Project("Project E", "This is a sample description for this very cool project."));
        projects.add(new Project("Project F", "This is a sample description for this very cool project."));
        projects.add(new Project("Project G", "This is a sample description for this very cool project."));

        projectAdapter = new ProjectAdapter(this, projects);

        linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);

        projectRV.setLayoutManager(linearLayoutManager);
        projectRV.setAdapter(projectAdapter);

        sendButton.setOnClickListener(this); // call onClick()
        moreButton.setOnClickListener(this);
    }

    @Override
    protected void onResume() {
        super.onResume();

        if (getIntent() != null && getIntent().getExtras() != null) { // returned from create project activity
            newProjectName = getIntent().getStringExtra("name");
            newProjectDesc = getIntent().getStringExtra("desc");
            //projectService.insertProject(new Project(newProjectName, newProjectDesc));
        }

        projectAdapter.notifyDataSetChanged();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.inviteButton:
                goToInviteSent();
                break;
            case R.id.moreButton:
                goToAddProject();
                break;
            default:
                break;
        }
    }

    private void goToInviteSent(){
        Intent intent = new Intent( this, RecipientInfoActivity.class );
        startActivity( intent );
    }

    private void goToAddProject(){
        Intent intent = new Intent( this, AddProjectActivity.class );
        startActivity( intent );
    }
}