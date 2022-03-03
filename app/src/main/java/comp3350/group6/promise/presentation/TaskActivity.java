package comp3350.group6.promise.presentation;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import java.sql.Timestamp;
import java.util.ArrayList;

import comp3350.group6.promise.R;
import comp3350.group6.promise.objects.Task;
import comp3350.group6.promise.util.TaskAdapter;

public class TaskActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task);

        // Temporary test data

        Task task = this.generateTask("Test Task");
        ArrayList<Task> subtasks = generateSubtasks(4);

        // Obtain views

        TextView titleView = (TextView) findViewById(R.id.task_page_title);
        TextView descriptionView = (TextView) findViewById(R.id.task_page_description);
        TextView priorityView = (TextView) findViewById(R.id.task_page_priority);
        TextView deadlineView = (TextView) findViewById(R.id.task_page_deadline);
        RecyclerView subtaskRecyclerView = (RecyclerView) findViewById(R.id.subtasks_recycler);

        // Set content of views

        titleView.setText(task.getTitle());
        descriptionView.setText(task.getDescription());
        priorityView.setText("Priority: " + task.getPriority());
        deadlineView.setText("Deadline: " + task.getDeadline().toLocaleString());

        // Setup subtask list

        TaskAdapter subtaskListAdapter = new TaskAdapter(this, subtasks);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        subtaskRecyclerView.setLayoutManager(linearLayoutManager);
        subtaskRecyclerView.setAdapter(subtaskListAdapter);
    }

    // Temporary task generation functions

    private ArrayList<Task> generateSubtasks(int count) {
        ArrayList<Task> subtasks = new ArrayList<Task>();

        for(int i = 0; i < count; i++) {
            char letter = (char) ('A' + i);
            subtasks.add(this.generateTask("Subtask " + letter));
        }

        return subtasks;
    }

    private Task generateTask(String name) {
        return new Task(
                name,
                String.format("This is the description for \"%s\". It has enough characters for at least two lines of text.", name),
                1,
                1,
                0,
                new Timestamp(System.currentTimeMillis()),
                new Timestamp(System.currentTimeMillis())
        );
    }

}