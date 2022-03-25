package comp3350.group6.promise.util;

import android.os.Build;

import androidx.annotation.RequiresApi;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import comp3350.group6.promise.application.Main;

public class TestUtils {
    private static final File  DB_SRC = new File("src/main/assets/db/PROMISE.script");


    @RequiresApi(api = Build.VERSION_CODES.O)
    public static File copyDB() throws IOException{
        final File target = File.createTempFile("temp-db",".script");
        Files.copy(DB_SRC.toPath(),target.toPath()); // this throw IOException
        Main.setDBPath(target.getAbsolutePath().replace(".script", ""));
        return target;
    }
}
