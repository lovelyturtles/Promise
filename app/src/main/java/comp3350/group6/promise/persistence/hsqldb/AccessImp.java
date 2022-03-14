package comp3350.group6.promise.persistence.hsqldb;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.sql.SQLException;

import java.util.List;
import java.util.ArrayList;

import comp3350.group6.promise.objects.Access;
import comp3350.group6.promise.persistence.AccessDao;
import comp3350.group6.promise.util.DBConnectorUtil;

public class AccessImp implements AccessDao {

    /*
     * Used to create a Project object from a SQL ResultSet
     */
    private Access createAccessObject(ResultSet rs) throws SQLException{
        int defaultId = rs.getInt("defaultId"); // what's the default id?
        int projectId = rs.getInt("projectId");
        int userId = rs.getInt("userId");
        String role = rs.getString("role");
        Timestamp startTime = rs.getTimestamp("startTime");

        return new Access(defaultId, projectId, userId, role, startTime);
    }


    @Override
    public List<Access> getAccessByProject(int projectId) {
        final List<Access> userList = new ArrayList<Access>();

        try(Connection con = DBConnectorUtil.getConnection();
            PreparedStatement pstmt = con.prepareStatement("select * from user, participate where user.userId = participate.userId and projectId = ?");
            ResultSet rs = pstmt.executeQuery()){

            while(rs.next()){
                Access acc = createAccessObject(rs);
                userList.add(acc);
            }

        } catch (SQLException e) {
            throw new PersistenceException(e);
        }

        return userList;
    }

    @Override
    public List<Access> getAccessByUser(int userId) {
        final List<Access> userList = new ArrayList<Access>();

        try(Connection con = DBConnectorUtil.getConnection();
            PreparedStatement pstmt = con.prepareStatement("select * from project, participate where project.projectId = participate.projectId and userId = ?");
            ResultSet rs = pstmt.executeQuery()){

            while(rs.next()){
                Access acc = createAccessObject(rs);
                userList.add(acc);
            }

        } catch (SQLException e) {
            throw new PersistenceException(e);
        }

        return userList;
    }
}
