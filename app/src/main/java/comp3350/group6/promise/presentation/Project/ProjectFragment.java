package comp3350.group6.promise.presentation.Project;


import static comp3350.group6.promise.R.id.action_edit;
import static comp3350.group6.promise.R.id.action_invite;

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

    private Project project;
    private List<Task> listOfTasks;
    private ArrayList<String> mNames = new ArrayList<>();
    private ArrayList<String> mImageUrls = new ArrayList<>();

    private Toolbar toolbarView;
    private TextView projectDescriptionView;
    private RecyclerView taskRecyclerView;
    private FloatingActionButton fab;

    private NavController navController;

    public ProjectFragment() {
        super(R.layout.fragment_project);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {

        // Get arguments passed to fragment

        int projectId = ProjectFragmentArgs.fromBundle(getArguments()).getProjectId();

        // Get content data

        if (projectId != -1) {
            project = ProjectService.getInstance().getProjectByID(projectId);
            listOfTasks = TaskService.getInstance().getTasksByProjectId(projectId);
        }

        // Get views from layout

        navController = Navigation.findNavController(view);

        toolbarView = view.findViewById(R.id.toolbar);
        projectDescriptionView = view.findViewById(R.id.project_page_desc);
        taskRecyclerView = view.findViewById(R.id.task_recycler);
        fab = getActivity().findViewById(R.id.fab);

        // Update layout content

        projectDescriptionView.setText(project.getStatement());

        TaskAdapter taskListAdapter = new TaskAdapter(getContext(),listOfTasks,this,this);
        taskRecyclerView.setLayoutManager( new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL,false));
        taskRecyclerView.setAdapter(taskListAdapter);

        // Update layout behaviours

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                handleAddTask(projectId);
            }
        });

        // Set up toolbar
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(navController.getGraph()).build();
        NavigationUI.setupWithNavController(toolbarView, navController, appBarConfiguration);
        toolbarView.setTitle(project.getProjectName());
        setHasOptionsMenu(true);

    }

    public void handleAddTask(int projectId) {
        NavDirections action = ProjectFragmentDirections.createTask(projectId);
        navController.navigate(action);
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
        Toast.makeText(getContext(), "Long Pressed Task: " + position, Toast.LENGTH_SHORT).show();
    }

    // Toolbar Methods

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.project_toolbar_menu, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int option = item.getItemId();
        NavDirections action;

        if (option == R.id.action_edit) {
            // TODO: Implement action handler for project editing
            Toast.makeText(getContext(), "Pressed Edit Project", Toast.LENGTH_SHORT).show();
            return true;
        }
        if (option == R.id.action_invite) {
            action = ProjectFragmentDirections.inviteUser(project.getProjectID());
            navController.navigate(action);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}