package comp3350.group6.promise.presentation.Task;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.NavDirections;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import java.sql.Timestamp;

import comp3350.group6.promise.R;
import comp3350.group6.promise.application.CurrentSession;
import comp3350.group6.promise.application.Service;
import comp3350.group6.promise.objects.Handle;
import comp3350.group6.promise.objects.Task;

public class CreateTaskFragment extends Fragment {

    private Toolbar toolbar;
    private EditText taskNameText;
    private EditText taskDesText;
    private EditText taskEstimateText;
    private EditText taskPriorityText;
    private Button submitTaskButton;

    private NavController navController;

    public CreateTaskFragment() {
        super(R.layout.activity_create_task);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        // Get arguments passed to fragment

        int id = CreateTaskFragmentArgs.fromBundle(getArguments()).getProjectId();

        // Obtain views from layout

        navController = NavHostFragment.findNavController(this);

        toolbar = view.findViewById(R.id.task_toolBar);
        taskNameText = view.findViewById(R.id.task_name_input);
        taskDesText = view.findViewById(R.id.task_description_input);
        taskEstimateText = view.findViewById(R.id.task_estimate_input);
        taskPriorityText = view.findViewById(R.id.task_priority_input);
        submitTaskButton = view.findViewById(R.id.submit_task_button);

        // Update layout behaviours

        submitTaskButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String name = taskNameText.getText().toString();
                String description = taskDesText.getText().toString();
                String estimate = taskEstimateText.getText().toString();
                int priority = Integer.parseInt(taskPriorityText.getText().toString());

                try {
                    // TODO: Use times selected by user
                    Timestamp defaultTime = new Timestamp(System.currentTimeMillis() + 1000 * 60 * 60 * 24 * 3);

                    createTask(name, description, priority, defaultTime, defaultTime, id);

                    NavDirections action = CreateTaskFragmentDirections.createTaskSuccess(id);
                    navController.navigate(action);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        // Set up toolbar
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(navController.getGraph()).build();
        NavigationUI.setupWithNavController(toolbar, navController, appBarConfiguration);
        setHasOptionsMenu(true);
    }

    private boolean createTask(String name, String desc, int priority, Timestamp endTime, Timestamp deadline, int projectId) throws Exception {
        try {
            int taskId = Service.tasks.insertTask(new Task(name, desc, priority, 0, projectId, endTime, deadline));
            Service.handles.insertHandle(new Handle(taskId, CurrentSession.currentUser.getUserID(), new Timestamp(System.currentTimeMillis())));
            return true;
        }
        catch (Exception e) {
            Toast.makeText(getContext(), "Please enter all fields", Toast.LENGTH_SHORT).show();
            throw new Exception(e.getCause());
        }
    }

}