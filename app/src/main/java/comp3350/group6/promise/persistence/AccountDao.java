package comp3350.group6.promise.persistence;

import comp3350.group6.promise.objects.Account;

public interface AccountDao {

    int createAccount(String email, String password, int userId) throws Exception;

    int changePassword(int userId, String oldPassword, String newPassword) throws Exception;

    boolean accountExists( String email );

    Account getAccountByEmail(String email );

    boolean passwordsMatch( String email, String password );

}
