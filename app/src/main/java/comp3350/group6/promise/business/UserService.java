package comp3350.group6.promise.business;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import comp3350.group6.promise.util.DBConnectorUtil;
import lombok.Data;


@Data
public class UserService {

    public static void test() {
        try {
            Connection conn = DBConnectorUtil.getConnection();
            Statement state = conn.createStatement();

            state.execute("INSERT INTO ACCOUNT VALUES('115@gmail.com','123',456)");
            ResultSet rs = state.executeQuery("SELECT email,password,userId FROM account");

            while (rs.next()) {
                String email = rs.getString("email");
                String pass = rs.getString("password");
                int id = rs.getInt("userId");
                System.out.println("email：" + email + " pss：" + pass
                        + "  userid：" + id);
                System.out.println("=============================");
            }

            state.close();
            conn.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
