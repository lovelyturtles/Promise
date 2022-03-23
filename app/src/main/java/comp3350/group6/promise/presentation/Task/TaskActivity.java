package comp3350.group6.promise.presentation.Task;

import static comp3350.group6.promise.persistence.stub.TaskImpNoDB.generateTask;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import java.sql.Timestamp;
import java.util.ArrayList;

import comp3350.group6.promise.R;
import comp3350.group6.promise.business.ProjectService;
import comp3350.group6.promise.business.TaskService;
import comp3350.group6.promise.objects.Task;
import comp3350.group6.promise.util.TaskAdapter;

public class TaskActivity extends AppCompatActivity {

    private static final TaskService taskService = new TaskService();
    private TextView titleView;
    private TextView descriptionView;
    private TextView priorityView;
    private TextView deadlineView;
    private RecyclerView subtaskRecyclerView;
    private Task currentTask;
    private ImageButton moreButton;
    private ImageButton backButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task);

//        currentTask = generateTask("Task A");

        if (getIntent() != null && getIntent().getExtras() != null) {
            int id = getIntent().getIntExtra("taskID", -1);
            if (id != -1){
                currentTask = taskService.getTask(id);
            }
        }

        if(currentTask != null) {

            // Obtain views

            titleView = (TextView) findViewById(R.id.task_page_title);
            descriptionView = (TextView) findViewById(R.id.task_page_description);
            priorityView = (TextView) findViewById(R.id.task_page_priority);
            deadlineView = (TextView) findViewById(R.id.task_page_deadline);
            subtaskRecyclerView = (RecyclerView) findViewById(R.id.subtasks_recycler);

            moreButton = findViewById(R.id.task_page_more);
            backButton = findViewById(R.id.back_button);

            // Set content of views

            titleView.setText(currentTask.getTitle());
            descriptionView.setText(currentTask.getDescription());
            priorityView.setText("Priority: " + currentTask.getPriority());
            deadlineView.setText("Deadline: " + currentTask.getDeadline().toLocaleString());

            backButton.setOnClickListener( new View.OnClickListener() {
                @Override
                public void onClick( View view ) {
                    finish();
                }
            });

        }
    }

}