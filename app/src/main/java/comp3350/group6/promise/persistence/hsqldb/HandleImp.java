package comp3350.group6.promise.persistence.hsqldb;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import comp3350.group6.promise.objects.Handle;
import comp3350.group6.promise.persistence.HandleDao;
import comp3350.group6.promise.util.DBConnectorUtil;

public class HandleImp implements HandleDao {



    private Handle fromResultSet(final ResultSet rs) throws SQLException{
        final int defaultId = rs.getInt("defaultId");
        final int taskId = rs.getInt("taskId");
        final int userId = rs.getInt("userId");
        final Timestamp since = rs.getTimestamp("since");
        return new Handle(defaultId,taskId,userId,since);
    }


    @Override
    public List<Handle> getUserTask(int taskId) {
        final List<Handle> listOfUserTask = new ArrayList<Handle>();

        try (final Connection con = DBConnectorUtil.getConnection()){
            final PreparedStatement ps = con.prepareStatement("SELECT * FROM USER, HANDLE WHERE USER.userId = HANDLE.userId and taskId = ?");
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                Handle handle = fromResultSet(rs);
                listOfUserTask.add(handle);
            }
        }catch (SQLException e){
            throw new PersistenceException(e);
        }
        return listOfUserTask;
    }

    @Override
    public List<Handle> getTaskUser(int userId) {

        final List<Handle> listOfTaskUser = new ArrayList<Handle>();
        try(final Connection con = DBConnectorUtil.getConnection()){
            final PreparedStatement ps = con.prepareStatement("SELECT * FROM TASK, HANDLE WHERE TASK.taskId = HANDLE.taskId and HANDLE.userId = ?");
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                Handle handle = fromResultSet(rs);
                listOfTaskUser.add(handle);
            }
        }catch (SQLException e){
            throw new PersistenceException(e);
        }
        return listOfTaskUser;
    }
}
