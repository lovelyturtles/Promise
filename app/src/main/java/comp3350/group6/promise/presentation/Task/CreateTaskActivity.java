package comp3350.group6.promise.presentation.Task;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.widget.Toolbar;

import comp3350.group6.promise.R;

public class CreateTaskActivity extends AppCompatActivity {
    private Toolbar taskToolBar;
    private EditText taskNameText;
    private EditText taskDesText;
    private Button submitTaskButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_task);

        taskToolBar = (Toolbar) findViewById(R.id.task_toolBar);
        setSupportActionBar(taskToolBar);
        taskNameText = findViewById(R.id.task_name_input);
        taskDesText = findViewById(R.id.task_description_input);

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
               createTask();
            }

        });

    }

    private void createTask() {
        String taskName = taskNameText.getText().toString();
        String taskDes = taskDesText.getText().toString();

        try{

        }
    }

}