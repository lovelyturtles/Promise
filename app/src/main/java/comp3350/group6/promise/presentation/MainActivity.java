package comp3350.group6.promise.presentation;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import comp3350.group6.promise.R;

public class MainActivity extends AppCompatActivity {

    public static void run(){
        System.out.println("hello");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}