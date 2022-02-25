package comp3350.group6.promise.presentation;

import android.content.Intent;
import android.os.Bundle;
import android.text.style.TtsSpan;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import comp3350.group6.promise.R;

public class AddProjectActivity extends AppCompatActivity implements View.OnClickListener{

    EditText name;
    EditText description;
    ImageButton back;
    Button createProject;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView( R.layout.activity_add_project );

        name = findViewById(R.id.projectName);
        description = findViewById(R.id.projectDesc);
        back = findViewById(R.id.create_project_back);
        createProject = findViewById(R.id.create_project_button);

        back.setOnClickListener(this); // calling onClick() method
        createProject.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.create_project_back:
                back();
                break;
            case R.id.create_project_button:
                createProject(v);
                break;
            default:
                break;
        }
    }

    private void back(){
        Intent intent = new Intent( this, ProjectPage.class );
        startActivity( intent );
    }

    private void createProject(View v){
        String projectName = name.getText().toString();
        String projectDesc = description.getText().toString();

        Intent intent = new Intent(v.getContext(), ProjectPage.class );
        intent.putExtra("name", projectName);
        intent.putExtra("desc", projectDesc);
        startActivity( intent );
    }
}
