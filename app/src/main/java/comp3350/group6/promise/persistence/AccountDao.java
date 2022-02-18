package comp3350.group6.promise.persistence;

public interface AccountDao {

    int createAccount(String email, String password, int userId) throws Exception;

    int changePassword(int userId, String oldPassword, String newPassword) throws Exception;
}
