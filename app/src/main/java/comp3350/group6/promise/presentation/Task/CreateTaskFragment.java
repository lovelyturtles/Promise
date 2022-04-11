package comp3350.group6.promise.presentation.Task;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.NavDirections;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;

import comp3350.group6.promise.R;
import comp3350.group6.promise.application.CurrentSession;
import comp3350.group6.promise.application.Service;
import comp3350.group6.promise.objects.Handle;
import comp3350.group6.promise.objects.Task;

public class CreateTaskFragment extends Fragment {

    private Toolbar toolbar;
    private EditText nameInput;
    private EditText descriptionInput;
    private EditText deadlineInput;
    private EditText priorityInput;
    private Button submitButton;

    private NavController navController;

    public CreateTaskFragment() {
        super(R.layout.fragment_create_task);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        // Get arguments passed to fragment

        int id = CreateTaskFragmentArgs.fromBundle(getArguments()).getProjectId();

        // Obtain views from layout

        navController = NavHostFragment.findNavController(this);

        toolbar = view.findViewById(R.id.task_toolBar);
        nameInput = view.findViewById(R.id.task_name_input);
        descriptionInput = view.findViewById(R.id.task_description_input);
        deadlineInput = view.findViewById(R.id.task_estimate_input);
        priorityInput = view.findViewById(R.id.task_priority_input);
        submitButton = view.findViewById(R.id.create_task_button);

        // Update layout behaviours

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String name = nameInput.getText().toString();
                String description = descriptionInput.getText().toString();
                String deadline = deadlineInput.getText().toString();
                String priority = priorityInput.getText().toString();
                Timestamp deadlineTime;

                if (TextUtils.isEmpty(name)) {
                    nameInput.setError("This item cannot be empty");
                    return;
                } else if (TextUtils.isEmpty(priority)) {
                    priorityInput.setError("This item cannot be empty");
                    return;
                } else if (TextUtils.isEmpty(deadline)) {
                    deadlineInput.setError("This item cannot be empty");
                    return;
                }
                Calendar c = Calendar.getInstance();
                c.setTime(new Date());
                c.add(Calendar.DATE, Integer.valueOf(deadline));
                deadlineTime = new Timestamp(c.getTimeInMillis());
                int p = Integer.valueOf(priority);

                handleSubmit(name, description, p, deadlineTime, deadlineTime, id);
            }
        });

        // Set up toolbar
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(navController.getGraph()).build();
        NavigationUI.setupWithNavController(toolbar, navController, appBarConfiguration);
        setHasOptionsMenu(true);
    }

    private void handleSubmit(String name, String desc, int priority, Timestamp endTime, Timestamp deadline, int projectId) {
        // TODO: Handle invalid inputs
        int taskId = createTask(name, desc, priority, endTime, deadline, projectId);
        // Return to projects page if creation is successful
        NavDirections action = CreateTaskFragmentDirections.createTaskSuccess(projectId);
        navController.navigate(action);
    }

    private int createTask(String name, String desc, int priority, Timestamp endTime, Timestamp deadline, int projectId) {
        int taskId = Service.tasks.insertTask(new Task(name, desc, priority, 0, projectId, endTime, deadline));
        Service.handles.insertHandle(new Handle(taskId, CurrentSession.getAccount().getUserID(), new Timestamp(System.currentTimeMillis())));
        return taskId;
    }

}