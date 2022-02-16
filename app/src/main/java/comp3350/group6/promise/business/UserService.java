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

            while (rs.next()) { //每进行一次next()，就将遍历一行属性的值
                String email = rs.getString("email"); //获取第二列（bookName）的数据
                String pass = rs.getString("password");
                int id = rs.getInt("userId"); //获取第一列（id)的数据
                System.out.println("email：" + email + " pss：" + pass
                        + "  userid：" + id);
                System.out.println("=============================");
            }

            state.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
