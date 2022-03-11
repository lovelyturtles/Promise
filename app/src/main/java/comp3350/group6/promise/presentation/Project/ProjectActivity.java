package comp3350.group6.promise.presentation.Project;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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

    private Project currentProject; // project that we are viewing
    private TextView projectTitleView;
    private TextView projectDescView;
    private ImageView projectImgView;
    private ImageButton moreButton;
    private ImageButton backButton;
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
                currentProject = projectService.getProjectByID(id);
            }
        }

        projectTitleView = findViewById(R.id.project_page_title);
        projectDescView = findViewById(R.id.project_page_desc);
        projectImgView = findViewById(R.id.project_page_image);
        moreButton = findViewById(R.id.project_page_more);
        backButton = findViewById(R.id.back_button);
        taskRecyclerView = (RecyclerView) findViewById(R.id.subtasks_recycler);

        projectTitleView.setText(currentProject.getProjectName());
        projectDescView.setText(currentProject.getStatement());

        projectTasks = taskService.getTasksByProjectId(currentProject.getProjectID());

        TaskAdapter taskListAdapter = new TaskAdapter(this, projectTasks, this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        taskRecyclerView.setLayoutManager(linearLayoutManager);
        taskRecyclerView.setAdapter(taskListAdapter);

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

    @Override
    public void onTaskClick(int position) {
        Task clickedTask = projectTasks.get(position);
        Intent intent = new Intent(this, TaskActivity.class);
        intent.putExtra("taskID", clickedTask.getTaskId());
        startActivity(intent);
    }
}
