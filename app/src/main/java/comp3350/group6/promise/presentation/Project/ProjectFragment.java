package comp3350.group6.promise.presentation.Project;


import android.content.DialogInterface;
import android.os.Bundle;
import android.view.MenuInflater;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

import comp3350.group6.promise.R;
import comp3350.group6.promise.application.Service;
import comp3350.group6.promise.business.ProjectService;
import comp3350.group6.promise.business.TaskService;
import comp3350.group6.promise.objects.Project;
import comp3350.group6.promise.objects.Task;
import comp3350.group6.promise.presentation.Task.TaskAdapter;

public class ProjectFragment extends Fragment implements TaskAdapter.OnTaskClickListener, TaskAdapter.OnTaskLongClickListener {

    private Project project;
    private List<Task> listOfTasksIP; // In Progress
    private List<Task> listOfTasksFinished; // Finished

    private Toolbar toolbarView;
    private TextView projectDescriptionView;
    private TextView taskListIP;
    private TextView taskListFinish;
    private RecyclerView taskRecyclerViewIP;
    private RecyclerView taskRecyclerViewFinished;

    private FloatingActionButton fab;

    private NavController navController;

    public ProjectFragment() {
        super(R.layout.fragment_project);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {

        // Get project ID from navigation arguments

        int projectId = ProjectFragmentArgs.fromBundle(getArguments()).getProjectId();

        // Get project data
        if (projectId != -1) {
            project = ProjectService.getInstance().getProjectByID(projectId);
            listOfTasksIP = TaskService.getInstance().getTasksByProjectId(projectId);
        }

        // Get views from layout

        navController = Navigation.findNavController(view);

        toolbarView = view.findViewById(R.id.toolbar);
        projectDescriptionView = view.findViewById(R.id.project_page_desc);
        taskListIP = view.findViewById(R.id.task_in_progress_label);
        taskListFinish = view.findViewById(R.id.task_finished_label);
        taskRecyclerViewIP = view.findViewById(R.id.task_recycler_in_progress);
        taskRecyclerViewFinished = view.findViewById(R.id.task_recycler_finished);
        fab = getActivity().findViewById(R.id.fab);

        // Update layout content

        projectDescriptionView.setText(project.getStatement());

        TaskAdapter taskListAdapterIP = new TaskAdapter(getContext(), listOfTasksIP, this, this);
        taskRecyclerViewIP.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        taskRecyclerViewIP.setAdapter(taskListAdapterIP);

        TaskAdapter taskListAdapterFinished = new TaskAdapter(getContext(), listOfTasksFinished, this, this);
        taskRecyclerViewFinished.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        taskRecyclerViewFinished.setAdapter(taskListAdapterFinished);


        // Update layout behaviours

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                handleAddTask(projectId);
            }
        });

        initializeToolbar();

    }

    public void handleAddTask(int projectId) {
        NavDirections action = ProjectFragmentDirections.createTask(projectId);
        navController.navigate(action);
    }

    // Task List Methods

    @Override
    public void onTaskClick(int position) {
        int taskId = listOfTasksIP.get(position).getTaskId();
        NavDirections action = ProjectFragmentDirections.selectTask(taskId);
        navController.navigate(action);
    }

    @Override
    public void onLongTaskClick(int position) {
        Toast.makeText(getContext(), "Long Pressed Task: " + position, Toast.LENGTH_SHORT).show();
    }

    // Toolbar Methods

    private void initializeToolbar() {
        AppCompatActivity activity = (AppCompatActivity) requireActivity();
        setHasOptionsMenu(true);
        activity.setTitle(project.getProjectName());
        activity.setSupportActionBar(toolbarView);
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(navController.getGraph()).build();
        NavigationUI.setupWithNavController(toolbarView, navController, appBarConfiguration);
        NavigationUI.setupActionBarWithNavController(activity, navController, appBarConfiguration);
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.project_toolbar_menu, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_invite_user) {
            NavDirections action = ProjectFragmentDirections.actionInviteUser(project.getProjectID());
            navController.navigate(action);
            return true;
        } else if (id == R.id.action_delete_project) {
            deleteDialogue();
            return true;
        } else if (id == R.id.action_edit_project) {
            NavDirections action = ProjectFragmentDirections.actionEditProject(project.getProjectID());
            navController.navigate(action);
            return true;
        }

        return super.onOptionsItemSelected(item);
//        return NavigationUI.onNavDestinationSelected(item, navController) || super.onOptionsItemSelected(item);
    }

    private void deleteDialogue() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setMessage("Are you sure you want to delete \"" + project.getProjectName() + "\"?");
        builder.setCancelable(true);

        builder.setPositiveButton(
                "Delete",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // delete current project
                        Service.projects.deleteProject(project);
                        // go back to dashboard fragment
                        NavDirections action = ProjectFragmentDirections.actionDeleteProject();
                        navController.navigate(action);
                    }
                });

        builder.setNegativeButton(
                "Cancel",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });

        AlertDialog dialog = builder.create();
        dialog.show();
    }

}