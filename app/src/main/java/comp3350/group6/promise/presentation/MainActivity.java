package comp3350.group6.promise.presentation;

import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;

import comp3350.group6.promise.R;
import comp3350.group6.promise.application.Main;
import comp3350.group6.promise.objects.FakeDB;
import comp3350.group6.promise.util.DBConnectorUtil;

public class MainActivity extends AppCompatActivity {
    //What does this do? Do we need it?
    public static void run() {
        System.out.println("hello");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //populate our fake database
        try {
            copyDatabaseToDevice();
        } catch (Exception e) {
            e.printStackTrace();
        }

        Button createButton = findViewById(R.id.goToCreate);
        Button loginButton = findViewById(R.id.goToLogin);

        createButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToCreate();
            }
        });

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToLogin();
            }
        });

//        Button projectButton = findViewById( R.id.goToProjects);
//        projectButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                goToProjects();
//            }
//        });
    }

    private void populateFakeDatabase() throws Exception {

        FakeDB fakes = new FakeDB();
        fakes.initialize();

    }

    private void goToCreate() {

        Intent intent = new Intent(this, CreateActivity.class);
        startActivity(intent);

    }

    private void goToLogin() {

        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);

    }

//    private void goToProjects(){
//        Intent intent = new Intent( this, ProjectList.class );
//        startActivity( intent );
//    }

    private void copyDatabaseToDevice() {
        final String DB_PATH = "db";

        String[] assetNames;
        Context context = getApplicationContext();
        File dataDirectory = context.getDir(DB_PATH, Context.MODE_PRIVATE);
        AssetManager assetManager = getAssets();

        try {

            assetNames = assetManager.list(DB_PATH);
            for (int i = 0; i < assetNames.length; i++) {
                assetNames[i] = DB_PATH + "/" + assetNames[i];
            }

            copyAssetsToDirectory(assetNames, dataDirectory);

            Main.setDBPath(dataDirectory.toString() + "/" + Main.getDBPath());

            Log.i("ssw", "copyDatabaseToDevice: " + Main.getDBPath());
        } catch (final IOException ioe) {
//            Messages.warning(this, "Unable to access application data: " + ioe.getMessage());
        }
    }

    public void copyAssetsToDirectory(String[] assets, File directory) throws IOException {
        AssetManager assetManager = getAssets();

        for (String asset : assets) {
            String[] components = asset.split("/");
            String copyPath = directory.toString() + "/" + components[components.length - 1];

            char[] buffer = new char[1024];
            int count;

            File outFile = new File(copyPath);

            if (!outFile.exists()) {
                InputStreamReader in = new InputStreamReader(assetManager.open(asset));
                FileWriter out = new FileWriter(outFile);

                count = in.read(buffer);
                while (count != -1) {
                    out.write(buffer, 0, count);
                    count = in.read(buffer);
                }

                out.close();
                in.close();
            }
        }
    }


}