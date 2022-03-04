/*
 * Please ignore this class. We started implementing before realizing
 * we don't need a functioning database until iteration 2 so we switched
 * to using the fake databases in the "stub" folder
 */

package comp3350.group6.promise.persistence.hsqldb;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import comp3350.group6.promise.objects.User;
import comp3350.group6.promise.persistence.UserDao;
import comp3350.group6.promise.util.DBConnectorUtil;

public class UserImp implements UserDao {

    @Override
    public int addUser(String name, String introduction) throws Exception{
        Connection cnn = DBConnectorUtil.getConnection();
        try {
            PreparedStatement preparedStatement = cnn.prepareStatement("insert into User (name,introduction) values (?,?)", Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, introduction);
            preparedStatement.executeUpdate();
            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            generatedKeys.next();
            return generatedKeys.getInt(1);
        }finally {
            cnn.close();
        }
    }

    @Override
    public void updateUserByUserId(int userId, String name, String introduction) throws SQLException {
        Connection cnn = DBConnectorUtil.getConnection();
        try {
            PreparedStatement preparedStatement = cnn.prepareStatement("update User set name = ? introduction = ? where userId = ?");
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, introduction);
            preparedStatement.setInt(3, userId);
            preparedStatement.executeUpdate();
        } finally {
            cnn.close();
        }
    }

    @Override
    public User getUserByUserId(int userId) throws Exception {

        Connection cnn = DBConnectorUtil.getConnection();
        try {
            PreparedStatement preparedStatement = cnn.prepareStatement("select userId, name, introduction where userId = ?");
            preparedStatement.setInt(1, userId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) { //每进行一次next()，就将遍历一行属性的值
                int id = resultSet.getInt("userId"); //获取第一列（id)的数据
                String name = resultSet.getString("name"); //获取第二列（bookName）的数据
                String introduction = resultSet.getString("introduction");
                return new User(id,name,introduction);
            }
        } finally {
            cnn.close();
        }
        return new User(userId,null,null);
    }
}
