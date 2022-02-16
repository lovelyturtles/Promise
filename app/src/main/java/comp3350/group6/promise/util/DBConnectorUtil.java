package comp3350.group6.promise.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.sql.*;


public class DBConnectorUtil {

    static {
        File app_db = new File("src/main/java/comp3350/group6/promise/app_db");
        try {
            Class.forName("org.hsqldb.jdbcDriver");
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (!app_db.exists()) {
            try {
                Connection conn = getConnection();
                assert conn != null;
                Statement state = conn.createStatement();
                BufferedReader bf = new BufferedReader(new FileReader("src/main/assets/db/db.script"));
                String line = null;
                while ((line = bf.readLine()) != null) {
                    state.execute(line);
                }
                bf.close();
                state.close();
                conn.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static Connection getConnection() {
        try {
            return DriverManager.getConnection("jdbc:hsqldb:file:" + "src/main/java/comp3350/group6/promise/app_db/PromiseDB" + ";shutdown=true", "SA", "");
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

}



