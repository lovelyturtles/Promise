package comp3350.group6.promise.presentation.Project;


import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
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

public class ProjectActivity extends AppCompatActivity implements TaskAdapter.OnTaskClickListener, TaskAdapter.OnTaskLongClickListener {

    private static final ProjectService projectService = new ProjectService();
    private static final TaskService taskService = new TaskService();
    private static final String TAG = "tag" ;

    private Project currentProject; // project that we are viewing
    private TextView projectTitleView;
    private TextView projectDescView;
    private ImageView projectImgView;
    private ImageButton moreButton;
    private ImageButton backButton;
    private RecyclerView taskRecyclerView;
    private List<Task> listOfTasks;
    private Button createTaskButton;
    private TaskAdapter taskListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_project);

        // Create the project detail page based on the intent passed to it.
        if (getIntent() != null && getIntent().getExtras() != null) {
            int id = getIntent().getIntExtra("projectID", -1);
            if (id != -1) {
                currentProject = projectService.getProjectByID(id);
            }
        }

        projectTitleView = findViewById(R.id.project_page_title);
        projectDescView = findViewById(R.id.project_page_desc);
        projectImgView = findViewById(R.id.project_page_image);
        moreButton = findViewById(R.id.project_page_more);
        backButton = findViewById(R.id.back_button);
        taskRecyclerView = (RecyclerView) findViewById(R.id.subtasks_recycler);
        createTaskButton = (Button) findViewById(R.id.button_create_task);

        projectTitleView.setText(currentProject.getProjectName());
        projectDescView.setText(currentProject.getStatement());

        listOfTasks = taskService.getTasksByProjectId(currentProject.getProjectID());


        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                back();
            }
        });

        createTaskButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addItem(view);
            }
        });

        taskListAdapter = new TaskAdapter(this,listOfTasks,this,this);
        taskRecyclerView.setLayoutManager( new LinearLayoutManager(ProjectActivity.this, LinearLayoutManager.VERTICAL,false));
        taskRecyclerView.setAdapter(taskListAdapter);

    }


    // go to the previous page
    private void back() {
        Intent intent = new Intent(this, DashboardActivity.class);
        startActivity(intent);
    }

    @Override
    public void onTaskClick(int position) {
        Task clickedTask = listOfTasks.get(position);
        Intent intent = new Intent(this, TaskActivity.class);
        intent.putExtra("taskID", clickedTask.getTaskId());
        startActivity(intent);
    } // Go to Task Activity

    // TASK RELATED METHODS
    @Override
    public void onLongTaskClick(int position) {
        Task longClickedTask = listOfTasks.get(position);
        Log.d(TAG, "onLongTaskClick: ");
    }// Long click delete task

    private void addItem(View view){

    }
}
