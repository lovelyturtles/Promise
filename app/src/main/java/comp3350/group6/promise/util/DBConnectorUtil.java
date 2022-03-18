package comp3350.group6.promise.util;

import android.util.Log;

import java.sql.*;

import comp3350.group6.promise.application.Main;


public class DBConnectorUtil {


    public static Connection getConnection() {
        try {
            return DriverManager.getConnection("jdbc:hsqldb:file:" + Main.getDBPath() + ";shutdown=true", "SA", "");
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

}



