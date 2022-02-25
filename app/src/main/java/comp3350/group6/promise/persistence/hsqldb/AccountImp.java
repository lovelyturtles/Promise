package comp3350.group6.promise.persistence.hsqldb;

import org.apache.commons.codec.binary.Base64;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.sql.PreparedStatement;


import comp3350.group6.promise.persistence.AccountDao;
import comp3350.group6.promise.util.DBConnectorUtil;

public class AccountImp implements AccountDao {

    @Override
    public int createAccount(String email, String password, int userId) throws Exception{
        password = new String(Base64.encodeBase64URLSafe(password.getBytes(StandardCharsets.UTF_8)), Charset.defaultCharset());
        Connection cnn = DBConnectorUtil.getConnection();
        assert cnn != null;
        PreparedStatement preparedStatement = cnn.prepareStatement("insert into Account values (?,?,?)");
        preparedStatement.setString(1, email);
        preparedStatement.setString(2, password);
        preparedStatement.setInt(3, userId);
        try {
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            throw e;
        } finally {
            cnn.close();
        }
        return userId;
    }

    @Override
    public int changePassword(int userId, String oldPassword, String newPassword) throws Exception{
        oldPassword = new String(Base64.encodeBase64URLSafe(oldPassword.getBytes(StandardCharsets.UTF_8)), Charset.defaultCharset());
        newPassword = new String(Base64.encodeBase64URLSafe(newPassword.getBytes(StandardCharsets.UTF_8)), Charset.defaultCharset());
        Connection cnn = DBConnectorUtil.getConnection();
        int res = 0;
        try {
            assert cnn != null;
            PreparedStatement preparedStatement = cnn.prepareStatement("update Account set password = ? where userId = ? and password = ?");
            preparedStatement.setString(1, newPassword);
            preparedStatement.setInt(2, userId);
            preparedStatement.setString(3, oldPassword);
            res = preparedStatement.executeUpdate();
        } catch (Exception e) {
            throw e;
        } finally {
            cnn.close();
        }
        return res;
    }
}
