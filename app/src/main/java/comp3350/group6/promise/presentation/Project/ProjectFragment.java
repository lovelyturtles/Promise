package comp3350.group6.promise.presentation.Project;


import android.os.Bundle;
import android.util.Log;
import android.view.MenuInflater;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

import comp3350.group6.promise.R;
import comp3350.group6.promise.business.ProjectService;
import comp3350.group6.promise.business.TaskService;
import comp3350.group6.promise.objects.Project;
import comp3350.group6.promise.objects.Task;
import comp3350.group6.promise.presentation.Task.TaskAdapter;

public class ProjectFragment extends Fragment implements TaskAdapter.OnTaskClickListener, TaskAdapter.OnTaskLongClickListener {

    private static final String TAG = "tag" ;

    private Project project;
    private List<Task> listOfTasks;
    private ArrayList<String> mNames = new ArrayList<>();
    private ArrayList<String> mImageUrls = new ArrayList<>();

    private Toolbar toolbarView;
    private TextView projectDescriptionView;
    private RecyclerView taskRecyclerView;
    private FloatingActionButton fab;

    private NavController navController;

    /*private void initImageBitmaps(){
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
    }*/

    public ProjectFragment() {
        super(R.layout.fragment_project);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {

        // Get arguments passed to fragment

        int id = ProjectFragmentArgs.fromBundle(getArguments()).getProjectId();

        // Get content data

        if (id != -1) {
            project = ProjectService.getInstance().getProjectByID(id);
            listOfTasks = TaskService.getInstance().getTasksByProjectId(id);
        }

        // Get views from layout

        navController = Navigation.findNavController(view);

        toolbarView = view.findViewById(R.id.toolbar);
        projectDescriptionView = view.findViewById(R.id.project_page_desc);
        taskRecyclerView = view.findViewById(R.id.task_recycler);
        fab = view.findViewById(R.id.fab);

        // Update layout content

        projectDescriptionView.setText(project.getStatement());

        TaskAdapter taskListAdapter = new TaskAdapter(getContext(),listOfTasks,this,this);
        taskRecyclerView.setLayoutManager( new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL,false));
        taskRecyclerView.setAdapter(taskListAdapter);

        // Update layout behaviours

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onFloatingButtonClick();
            }
        });

        // Set up toolbar
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(navController.getGraph()).build();
        NavigationUI.setupWithNavController(toolbarView, navController, appBarConfiguration);
        toolbarView.setTitle(project.getProjectName());
        setHasOptionsMenu(true);

    }

    public void onFloatingButtonClick() {
        // TODO: Implement handler for adding tasks
        Toast.makeText(getContext(), "Pressed Add Button", Toast.LENGTH_SHORT).show();
//        NavDirections action = CreateProjectFragmentDirections.createTask();
//        navController.navigate(action);
    }

    // Task List Methods

    @Override
    public void onTaskClick(int position) {
        int taskId = listOfTasks.get(position).getTaskId();
        NavDirections action = ProjectFragmentDirections.selectTask(taskId);
        navController.navigate(action);
    }

    @Override
    public void onLongTaskClick(int position) {
        int taskId = listOfTasks.get(position).getTaskId();
        Log.d(TAG, String.format("Long Pressed Task (Pos: %s, ID: %s)", position, taskId));
    }

    // Toolbar Methods

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.project_toolbar_menu, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_edit:
                // TODO: Implement action handler for project editing
                Toast.makeText(getContext(), "Pressed Edit Project", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.action_invite:
                // TODO: Implement action handler for project invites
                Toast.makeText(getContext(), "Pressed Invite to Project", Toast.LENGTH_SHORT).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}