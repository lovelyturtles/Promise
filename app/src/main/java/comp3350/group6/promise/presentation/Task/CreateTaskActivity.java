package comp3350.group6.promise.presentation.Task;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;

import java.sql.Timestamp;

import comp3350.group6.promise.R;
import comp3350.group6.promise.application.CurrentSession;
import comp3350.group6.promise.application.Service;
import comp3350.group6.promise.objects.Handle;
import comp3350.group6.promise.objects.Task;
import comp3350.group6.promise.presentation.Project.ProjectActivity;

public class CreateTaskActivity extends AppCompatActivity {
    private Toolbar taskToolBar;
    private EditText taskNameText;
    private EditText taskDesText;
    private EditText taskEstimateText;
    private EditText taskPriorityText;
    private Button submitTaskButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_task);

        taskToolBar = (Toolbar) findViewById(R.id.task_toolBar);
        setSupportActionBar(taskToolBar);
        taskNameText = findViewById(R.id.task_name_input);
        taskDesText = findViewById(R.id.task_description_input);
        taskEstimateText = findViewById(R.id.task_estimate_input);
        taskPriorityText = findViewById(R.id.task_priority_input);

        taskToolBar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        submitTaskButton = findViewById(R.id.submit_task_button);

        submitTaskButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    createTask();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

        });

    }

    private void createTask() throws Exception {
//        try {
        String taskName = taskNameText.getText().toString();
        String taskDes = taskDesText.getText().toString();
        String taskEstimate = taskEstimateText.getText().toString();
        int taskPriority = Integer.parseInt(taskPriorityText.getText().toString());
        Timestamp defaultTime = new Timestamp(System.currentTimeMillis());
        int projectId = 0;

        int taskId = Service.tasks.insertTask(new Task(taskName, taskDes, taskPriority, 0, projectId, defaultTime, defaultTime));
        Handle handleRet = Service.handles.insertHandle(new Handle(taskId, CurrentSession.currentUser.getUserID(), defaultTime));
        Service.handles.insertHandle(handleRet);

        // Return to project
        Intent intent = new Intent(this, ProjectActivity.class);
        startActivity(intent);
//        }catch(Exception e){
        Toast.makeText(getBaseContext(), "Please enter all fields", Toast.LENGTH_SHORT).show();
//            throw new Exception(e.getCause());
//        }
    }

    private Timestamp convertStringToTime(String str) {
        return null;
    }
}