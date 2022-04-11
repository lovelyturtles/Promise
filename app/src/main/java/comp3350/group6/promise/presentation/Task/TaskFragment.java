package comp3350.group6.promise.presentation.Task;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import comp3350.group6.promise.R;
import comp3350.group6.promise.application.Service;
import comp3350.group6.promise.business.TaskService;
import comp3350.group6.promise.objects.Task;
import comp3350.group6.promise.presentation.Project.ProjectFragmentDirections;

public class TaskFragment extends Fragment {

    private Task task;
    private int taskId;

    private Toolbar toolbarView;
    private TextView descriptionView;
    private TextView priorityView;
    private TextView deadlineView;

    private NavController navController;

    public TaskFragment() {
        super(R.layout.fragment_task);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {

        // Get task using ID passed to fragment

        taskId = TaskFragmentArgs.fromBundle(getArguments()).getTaskId();

        if (taskId != -1) {
            task = TaskService.getInstance().getTask(taskId);
        }

        // Get views from layout

        navController = NavHostFragment.findNavController(this);

        toolbarView = view.findViewById(R.id.toolbar);
        descriptionView = view.findViewById(R.id.task_page_description);
        priorityView = view.findViewById(R.id.task_page_priority);
        deadlineView = view.findViewById(R.id.task_page_deadline);

        // Update layout content with task data

        toolbarView.setTitle(task.getTitle());
        descriptionView.setText(task.getDescription());
        priorityView.setText("Priority: " + task.getPriority());
        deadlineView.setText("Deadline: " + task.getDeadline().toLocaleString());

        // Update layout behaviours

        initializeToolbar();
        toolbarView.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavDirections action = TaskFragmentDirections.actionTaskDeleteDestination(task.getProjectId());
                navController.navigate(action);
            }
        });

    }

    // Toolbar Methods

    private void initializeToolbar() {
        AppCompatActivity activity = (AppCompatActivity) requireActivity();
        setHasOptionsMenu(true);
        activity.setTitle(task.getTitle());
        activity.setSupportActionBar(toolbarView);
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(navController.getGraph()).build();
        NavigationUI.setupWithNavController(toolbarView, navController, appBarConfiguration);
        NavigationUI.setupActionBarWithNavController(activity, navController, appBarConfiguration);
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.task_toolbar_menu, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_edit_task:
                editTask();
                return true;
            case R.id.action_delete_task:
                deleteTaskDialog();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }


    private void deleteTaskDialog() {
        // Use the Builder class for convenient dialog construction
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage(R.string.dialog_delete_task)
                .setPositiveButton(R.string.delete_task, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        Toast.makeText(getContext(), "Click delete", Toast.LENGTH_SHORT).show();

                        Service.tasks.deleteTask(task);
                        NavDirections action = TaskFragmentDirections.actionTaskDeleteDestination(task.getProjectId());
                        navController.navigate(action);
                    }
                })
                .setNegativeButton(R.string.cancel_task, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // User cancelled the dialog
                        dialog.cancel();
                    }
                });
        // Create the AlertDialog object and return it
        AlertDialog dialog = builder.create();
        dialog.show();
    }


    private void editTask() {
        NavDirections action = TaskFragmentDirections.actionGoEditTask(taskId);
        navController.navigate(action);
    }
}