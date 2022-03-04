/*
 * Please ignore this class. We started implementing before realizing
 * we don't need a functioning database until iteration 2 so we switched
 * to using the fake databases in the "stub" folder
 */

package comp3350.group6.promise.persistence.hsqldb;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import comp3350.group6.promise.objects.Notification;
import comp3350.group6.promise.objects.enumClasses.NotifType;
import comp3350.group6.promise.util.DBConnectorUtil;

public class NotifImp {

    void addNotif(int userID, int projectID, int recipientID, int type ) throws Exception{

        Connection cnn = DBConnectorUtil.getConnection();
        PreparedStatement preparedStatement = null;
        try {

            assert cnn != null;
            preparedStatement = cnn.prepareStatement("INSERT INTO Notification VALUES(?,?,?,?)" );

            preparedStatement.setInt( 1, userID );
            preparedStatement.setInt( 2, projectID );
            preparedStatement.setInt( 3, recipientID );
            preparedStatement.setInt( 4, type );

            preparedStatement.executeUpdate();

        }

        finally{

            try{ preparedStatement.close(); } catch( Exception e ){/**/}
            try{ cnn.close(); }               catch( Exception e ){/**/}

        }
    }

    void removeNotif( int userID, int projectID, int recipientID ) throws Exception{

        Connection cnn = DBConnectorUtil.getConnection();
        PreparedStatement preparedStatement = null;

        try{
            assert cnn != null;
            preparedStatement = cnn.prepareStatement("DELETE FROM Notification WHERE userID = ? AND projectID = ? AND recipientID = ?" );
            preparedStatement.setInt( 1, userID );
            preparedStatement.setInt( 2, projectID );
            preparedStatement.setInt( 3, recipientID );
            preparedStatement.executeUpdate();
        }

        finally{

            try{ preparedStatement.close(); } catch( Exception e ){/**/}
            try{ cnn.close(); }               catch( Exception e ){/**/}

        }

    }

    ArrayList<Notification> getNotifs( int recipientID ) throws Exception{

        Connection cnn = DBConnectorUtil.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        ArrayList<Notification> notifs;

        int sender;
        int projectID;
        int reciever;
        int checkType;
        NotifType type;

        try{

            assert cnn != null;
            preparedStatement = cnn.prepareStatement( "SELECT * FROM Notification WHERE recipientID = ?" );
            preparedStatement.setInt( 1, recipientID );
            resultSet = preparedStatement.executeQuery();
            notifs = new ArrayList<Notification>();

            while( resultSet.next() ){

                sender = resultSet.getInt( "userID" );
                projectID = resultSet.getInt( "projectID" );
                reciever = resultSet.getInt( "recipientID" );
                checkType = resultSet.getInt( "type" );

                if( checkType == 0 )
                    type = NotifType.INVITE;
                else
                    type = NotifType.REQUEST;

                notifs.add( new Notification( sender, projectID, reciever, type ) );

            }

        }

        finally{

            try{ resultSet.close(); }         catch( Exception e ){/**/}
            try{ preparedStatement.close(); } catch( Exception e ){/**/}
            try{ cnn.close(); }               catch( Exception e ){/**/}

        }

        return notifs;

    }

}
