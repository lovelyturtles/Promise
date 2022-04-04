package comp3350.group6.promise.presentation;

import static comp3350.group6.promise.util.FileSystemUtil.copyDatabaseToDevice;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import comp3350.group6.promise.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        copyDatabaseToDevice(this);
    }

}