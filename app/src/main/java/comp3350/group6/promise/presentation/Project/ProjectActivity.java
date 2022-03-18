package comp3350.group6.promise.presentation.Project;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.appbar.CollapsingToolbarLayout;

import java.util.List;

import comp3350.group6.promise.R;
import comp3350.group6.promise.business.ProjectService;
import comp3350.group6.promise.business.TaskService;
import comp3350.group6.promise.objects.Project;
import comp3350.group6.promise.objects.Task;
import comp3350.group6.promise.presentation.User.DashboardActivity;
import comp3350.group6.promise.presentation.Task.TaskActivity;
import comp3350.group6.promise.util.TaskAdapter;

public class ProjectActivity extends AppCompatActivity implements TaskAdapter.ViewHolder.OnTaskClickListener {

    private static final ProjectService projectService = new ProjectService();
    private static final TaskService taskService = new TaskService();

    private Project project;
    private CollapsingToolbarLayout appBarLayoutView;
    private Toolbar toolbarView;
    private TextView projectDescriptionView;
    private ImageView projectImageView;
    private RecyclerView taskRecyclerView;
    private  List<Task> projectTasks;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_project);

        // Create the project detail page based on the intent passed to it.
        if (getIntent() != null && getIntent().getExtras() != null) {
            int id = getIntent().getIntExtra("projectID", -1);
            if (id != -1){
                project = projectService.getProjectByID(id);
            }
        }

        if(project != null) {
            appBarLayoutView = findViewById(R.id.toolbar_layout);
            projectImageView = findViewById(R.id.toolbar_image);
            projectDescriptionView = findViewById(R.id.project_page_desc);
            taskRecyclerView = findViewById(R.id.task_recycler);

            projectDescriptionView.setText(project.getStatement());

            projectTasks = taskService.getTasksByProjectId(project.getProjectID());
            TaskAdapter taskAdapter = new TaskAdapter(this, projectTasks, this);
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
            taskRecyclerView.setLayoutManager(linearLayoutManager);
            taskRecyclerView.setAdapter(taskAdapter);

            toolbarView = findViewById(R.id.toolbar);
            setSupportActionBar(toolbarView);
            getSupportActionBar().setTitle(project.getProjectName());

            toolbarView.setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    finish();
                }
            });
        }
        else {
            finish();
        }

    }

    @Override
    public void onTaskClick(int position) {
        Task clickedTask = projectTasks.get(position);
        Intent intent = new Intent(this, TaskActivity.class);
        intent.putExtra("taskID", clickedTask.getTaskId());
        startActivity(intent);
    }

    // Toolbar Method Overrides

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.project_toolbar_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_edit:
                // TODO: Implement action handler for project editing
                Toast.makeText(this, "Pressed Edit Project", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.action_invite:
                // TODO: Implement action handler for project invites
                Toast.makeText(this, "Pressed Invite to Project", Toast.LENGTH_SHORT).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
