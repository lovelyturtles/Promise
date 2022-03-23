package comp3350.group6.promise.tests.utils;


import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import comp3350.group6.promise.application.Main;

// This class create a temporary DB to test (no need to run Simulation)
public class TestUtils {
    private static final File DB_ORIGINAL = new File("src/main/assets/db/PROMISE.script");

    public static File copyDB() throws IOException {
        final File target = File.createTempFile("tempDB", ".script");
        Files.copy(Paths.get(String.valueOf(DB_ORIGINAL)),Paths.get(String.valueOf(target))); // check this
        Main.setDBPath(target.getAbsolutePath().replace(".script",""));
        return target;
    }
}
