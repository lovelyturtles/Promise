package comp3350.group6.promise.business;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import comp3350.group6.promise.objects.User;
import comp3350.group6.promise.persistence.UserDao;
import comp3350.group6.promise.persistence.hsqldb.UserImp;
import comp3350.group6.promise.util.DBConnectorUtil;
import lombok.Data;


@Data
public class UserService {

    private static final UserDao userDao = new UserImp();



    public int addUser(String name, String introduction) throws Exception{
        assert name!= null;
        return userDao.addUser(name, introduction);
    }

    public void updateUserByUserId(int userId, String name, String introduction) throws Exception{
        userDao.updateUserByUserId(userId, name, introduction);
    }

    public User getUserByUserId(int userId) throws Exception{
        return userDao.getUserByUserId(userId);
    }

//    public static void test() {
//        try {
//            Connection conn = DBConnectorUtil.getConnection();
//            Statement state = conn.createStatement();
//
//            state.execute("INSERT INTO ACCOUNT VALUES('115@gmail.com','123',456)");
//            ResultSet rs = state.executeQuery("SELECT email,password,userId FROM account");
//
//            while (rs.next()) {
//                String email = rs.getString("email");
//                String pass = rs.getString("password");
//                int id = rs.getInt("userId");
//                System.out.println("email：" + email + " pss：" + pass
//                        + "  userid：" + id);
//                System.out.println("=============================");
//            }
//
//            state.close();
//            conn.close();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
}

