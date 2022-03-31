package comp3350.group6.promise.persistence.hsqldb;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import comp3350.group6.promise.objects.Account;
import comp3350.group6.promise.objects.AccountUser;
import comp3350.group6.promise.objects.Exceptions.PersistenceException;
import comp3350.group6.promise.objects.User;
import comp3350.group6.promise.persistence.AccountUserDao;
import comp3350.group6.promise.util.DBConnectorUtil;

public class AccountUserImp implements AccountUserDao {

    @Override
    public AccountUser getUserByAccount( Account account ){

        AccountUser result = null;
        int userID;
        String name;
        String intro;

        String selectStatement = "SELECT User.userID, User.name, User.introduction " +
                "FROM User LEFT JOIN Account " +
                "ON User.userID = Account.userID " +
                "WHERE Account.userID = ?";

        try (final Connection cnn = DBConnectorUtil.getConnection();
             PreparedStatement pStatement = cnn.prepareStatement( selectStatement ) ) {
            pStatement.setInt( 1, account.getUserID() );
            ResultSet resultSet = pStatement.executeQuery();

            if( resultSet.next() ){

                userID = resultSet.getInt( "User.userID" );
                name = resultSet.getString( "User.name" );
                intro = resultSet.getString( "User.introduction" );
                result = new AccountUser( account, new User( userID, name, intro ) );

            }

        }

        catch ( SQLException e ) {
            throw new PersistenceException( e );
        }

        return result;

    }

}
