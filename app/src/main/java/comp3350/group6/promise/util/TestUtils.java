package comp3350.group6.promise.util;

import android.os.Build;

import androidx.annotation.RequiresApi;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

import comp3350.group6.promise.application.Main;

public class TestUtils {
    private static final File DB_SRC = new File("src/main/assets/db/PROMISE.script");


    @RequiresApi(api = Build.VERSION_CODES.O)
    public static File copyDB() throws IOException {
        File target = File.createTempFile("temp-db", ".script");
        Files.copy(DB_SRC.toPath(), target.toPath(), StandardCopyOption.REPLACE_EXISTING); // this throw IOException
        Main.setDBPath(target.getAbsolutePath().replace(".script", ""));
        return target;
    }
}
