package comp3350.group6.promise.util;

import java.sql.*;


public class DBConnectorUtil {

    public static void test(){

        String url = "jdbc:hsqldb:hsql://localhost/";

        String user = "SA";

        String password = "";

        try {
            Class.forName("org.hsqldb.jdbcDriver");

            Connection conn = DriverManager.getConnection(url, user, password);

            Statement state = conn.createStatement();

            ResultSet rs = state.executeQuery("SELECT email,password,userId FROM account");
//
//            while (rs.next()) {
//                System.out.print(rs.getString("email") + " ");
//
//                System.out.print(rs.getString("password") + " ");
//
//                System.out.println("");
//
//            }

        } catch (ClassNotFoundException e) {
            e.printStackTrace();

        } catch (SQLException e) {
            e.printStackTrace();

        }

    }
}



