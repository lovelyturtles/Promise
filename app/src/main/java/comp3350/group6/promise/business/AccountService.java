package comp3350.group6.promise.business;


import org.apache.commons.codec.binary.Base64;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import comp3350.group6.promise.persistence.AccountDao;
import comp3350.group6.promise.persistence.hsqldb.AccountImp;
import comp3350.group6.promise.util.DBConnectorUtil;

public class AccountService {

    private static final UserService userService = new UserService();
    private static final AccountDao accountDao = new AccountImp();

    public int createAccount(String email, String password, String name, String introduction) throws Exception {
        int userId = userService.addUser(name, introduction);
        if (userId < 0) {
            throw new Exception("Adding user fails");
        }
       try {
           userId = accountDao.createAccount(email,password,userId);
       }catch (Exception e){
           throw new Exception("Your email has been registered");
       }
       return userId;
    }

    public boolean changePassword(int userId, String oldPassword, String newPassword) throws Exception {

        return  1 == accountDao.changePassword(userId,oldPassword,newPassword);

    }

}
