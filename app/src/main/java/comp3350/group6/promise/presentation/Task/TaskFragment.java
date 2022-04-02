package comp3350.group6.promise.presentation.Task;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import comp3350.group6.promise.R;
import comp3350.group6.promise.business.TaskService;
import comp3350.group6.promise.objects.Task;

public class TaskFragment extends Fragment {

    private Task task;

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

        int id = TaskFragmentArgs.fromBundle(getArguments()).getTaskId();

        if (id != -1) {
            task = TaskService.getInstance().getTask(id);
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

        // Set up toolbar
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(navController.getGraph()).build();
        NavigationUI.setupWithNavController(toolbarView, navController, appBarConfiguration);
        setHasOptionsMenu(true);

    }

    // Toolbar Methods

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.task_toolbar_menu, menu);
    }

    @Override
    public boolean onOptionsItemSelected (MenuItem item){
        switch (item.getItemId()) {
            case R.id.action_edit:
                // TODO: Implement action handler for task editing
                Toast.makeText(getContext(), "Pressed Edit Task", Toast.LENGTH_SHORT).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}