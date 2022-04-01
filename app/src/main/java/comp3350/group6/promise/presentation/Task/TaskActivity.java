package comp3350.group6.promise.presentation.Task;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.appbar.CollapsingToolbarLayout;

import comp3350.group6.promise.R;
import comp3350.group6.promise.business.TaskService;
import comp3350.group6.promise.objects.Task;

public class TaskActivity extends AppCompatActivity {

    private Task task;
    private CollapsingToolbarLayout toolbarLayoutView;
    private Toolbar toolbarView;
    private TextView descriptionView;
    private TextView priorityView;
    private TextView deadlineView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task);

        if (getIntent() != null && getIntent().getExtras() != null) {
            int id = getIntent().getIntExtra("taskID", -1);
            if (id != -1) {
                task = TaskService.getInstance().getTask(id);
            }
        }

        // Obtain views

        toolbarLayoutView = findViewById(R.id.toolbar_layout);
        toolbarView = findViewById(R.id.task_toolBar);

        descriptionView = findViewById(R.id.task_page_description);
        priorityView = findViewById(R.id.task_page_priority);
        deadlineView = findViewById(R.id.task_page_deadline);

        // Set content of views

        toolbarLayoutView.setTitle(task.getTitle());
        descriptionView.setText(task.getDescription());
        priorityView.setText("Priority: " + task.getPriority());
        deadlineView.setText("Deadline: " + task.getDeadline().toLocaleString());

        toolbarView.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    // Toolbar Methods

    @Override
    public boolean onCreateOptionsMenu (Menu menu){
        getMenuInflater().inflate(R.menu.project_toolbar_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected (MenuItem item){
        switch (item.getItemId()) {
            case R.id.action_edit:
                // TODO: Implement action handler for task editing
                Toast.makeText(this, "Pressed Edit Task", Toast.LENGTH_SHORT).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}