package comp3350.group6.promise.presentation.Project;


import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

import comp3350.group6.promise.R;
import comp3350.group6.promise.business.ProjectService;
import comp3350.group6.promise.business.TaskService;
import comp3350.group6.promise.objects.Project;
import comp3350.group6.promise.objects.Task;
import comp3350.group6.promise.objects.enumClasses.NotifType;
import comp3350.group6.promise.presentation.Project.Invitation.RecipientInfoActivity;
import comp3350.group6.promise.presentation.Task.TaskActivity;
import comp3350.group6.promise.util.TaskAdapter;

public class ProjectActivity extends AppCompatActivity implements TaskAdapter.OnTaskClickListener, TaskAdapter.OnTaskLongClickListener {

    private static final String TAG = "tag" ;

    private Project project;
    private CollapsingToolbarLayout appBarLayoutView;
    private Toolbar toolbarView;
    private TextView projectDescriptionView;
    private ImageView projectImageView;
    private RecyclerView taskRecyclerView;
    private List<Task> listOfTasks;
//    private Button createTaskButton;
    private TaskAdapter taskListAdapter;
    private FloatingActionButton fab;

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
        RecyclerView recyclerView = findViewById(R.id.task_recycler);
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
                project = ProjectService.getInstance().getProjectByID(id);
                handleInvite(id);
            }
        }

        appBarLayoutView = findViewById(R.id.toolbar_layout);
        projectImageView = findViewById(R.id.toolbar_image);

        toolbarView = findViewById(R.id.toolbar);
        setSupportActionBar(toolbarView);
        getSupportActionBar().setTitle(project.getProjectName());

        projectDescriptionView = findViewById(R.id.project_page_desc);
        projectDescriptionView.setText(project.getStatement());

        listOfTasks = TaskService.getInstance().getTasksByProjectId(project.getProjectID());
        taskListAdapter = new TaskAdapter(this,listOfTasks,this,this);
        taskRecyclerView = findViewById(R.id.task_recycler);
        taskRecyclerView.setLayoutManager( new LinearLayoutManager(ProjectActivity.this, LinearLayoutManager.VERTICAL,false));
        taskRecyclerView.setAdapter(taskListAdapter);

        toolbarView.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        fab = findViewById(R.id.fab);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onFloatingButtonClick();
            }
        });

//        createTaskButton = (Button) findViewById(R.id.button_create_task);
//
//        createTaskButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                addItem(view);
//            }
//        });

    }

    private void handleInvite(int projectID ){

        Button inviteButton = findViewById( R.id.inviteButton);
        NotifType type = NotifType.INVITE;


    }

    private void goToRecipientDetails(){
        Intent intent = new Intent( this, RecipientInfoActivity.class );
        startActivity( intent );
    }

    public void onFloatingButtonClick() {
        // TODO: Implement handler for adding tasks
        Toast.makeText(getBaseContext(), "Pressed Add Button", Toast.LENGTH_SHORT).show();
    }

    // Task List Methods

    @Override
    public void onTaskClick(int position) {
        Task clickedTask = listOfTasks.get(position);
        Intent intent = new Intent(this, TaskActivity.class);
        intent.putExtra("taskID", clickedTask.getTaskId());
        startActivity(intent);
    } // Go to Task Activity

    @Override
    public void onLongTaskClick(int position) {
        Task longClickedTask = listOfTasks.get(position);
        Log.d(TAG, "onLongTaskClick: ");
    }// Long click delete task

    private void addItem(View view){

    }

    // Toolbar Methods

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
                Toast.makeText(getBaseContext(), "Pressed Edit Project", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.action_invite:
                // TODO: Implement action handler for project invites
                Toast.makeText(getBaseContext(), "Pressed Invite to Project", Toast.LENGTH_SHORT).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}