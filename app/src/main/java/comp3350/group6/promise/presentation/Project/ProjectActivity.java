package comp3350.group6.promise.presentation.Project;


import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
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

public class ProjectActivity extends AppCompatActivity implements TaskAdapter.OnTaskClickListener, TaskAdapter.OnTaskLongClickListener {

    private static final ProjectService projectService = new ProjectService();
    private static final TaskService taskService = new TaskService();
    private static final String TAG = "tag" ;

    private Project project;
    private CollapsingToolbarLayout appBarLayoutView;
    private Toolbar toolbarView;
    private TextView projectDescriptionView;
    private ImageView projectImageView;
    private RecyclerView taskRecyclerView;
    private List<Task> listOfTasks;
    private Button createTaskButton;
    private TaskAdapter taskListAdapter;

    private ArrayList<String> mNames = new ArrayList<>();
    private ArrayList<String> mImageUrls = new ArrayList<>();
    private void initImageBitmaps(){
        Log.d(TAG, "initImageBitmaps: preparing bitmaps.");

        mImageUrls.add("https://c1.staticflickr.com/5/4636/25316407448_de5fbf183d_o.jpg");
        mNames.add("Havasu Falls");

        mImageUrls.add("https://i.redd.it/tpsnoz5bzo501.jpg");
        mNames.add("Trondheim");

        mImageUrls.add("https://i.redd.it/qn7f9oqu7o501.jpg");
        mNames.add("Portugal");

        mImageUrls.add("https://i.redd.it/j6myfqglup501.jpg");
        mNames.add("Rocky Mountain National Park");


        mImageUrls.add("https://i.redd.it/0h2gm1ix6p501.jpg");
        mNames.add("Mahahual");

        mImageUrls.add("https://i.redd.it/k98uzl68eh501.jpg");
        mNames.add("Frozen Lake");


        mImageUrls.add("https://i.redd.it/glin0nwndo501.jpg");
        mNames.add("White Sands Desert");

        mImageUrls.add("https://i.redd.it/obx4zydshg601.jpg");
        mNames.add("Austrailia");

        mImageUrls.add("https://i.imgur.com/ZcLLrkY.jpg");
        mNames.add("Washington");

        initRecyclerView();
    }
    private void initRecyclerView(){
        Log.d(TAG, "initRecyclerView: init recyclerview.");
        RecyclerView recyclerView = findViewById(R.id.subtasks_recycler);
        TaskAdapter adapter = new TaskAdapter(this, mNames, mImageUrls);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

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

        appBarLayoutView = findViewById(R.id.toolbar_layout);
        toolbarView = findViewById(R.id.toolbar);
        projectImageView = findViewById(R.id.toolbar_image);
        projectDescriptionView = findViewById(R.id.project_page_desc);
        taskRecyclerView = findViewById(R.id.task_recycler);
        createTaskButton = (Button) findViewById(R.id.button_create_task);

        setSupportActionBar(toolbarView);
        getSupportActionBar().setTitle(project.getProjectName());
        projectDescriptionView.setText(project.getStatement());

        listOfTasks = taskService.getTasksByProjectId(currentProject.getProjectID());
        

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
                // TODO: Implement edit project action handler
                Toast.makeText(this, "Pressed Edit Project", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.action_invite:
                // TODO: Implement invite user to project action handler
                Toast.makeText(this, "Pressed Invite to Project", Toast.LENGTH_SHORT).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
