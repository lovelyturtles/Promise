package comp3350.group6.promise.persistence.hsqldb;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import comp3350.group6.promise.objects.Task;
import comp3350.group6.promise.persistence.TaskDao;
import comp3350.group6.promise.util.DBConnectorUtil;

public class TaskImp implements TaskDao {


    private Task fromResultSet(final ResultSet rs) throws SQLException {
        final int taskId = rs.getInt("taskId");
        final String title = rs.getString("title");
        final String description = rs.getString("description");
        final int priority = rs.getInt("priority");
        final int statusNum = rs.getInt("statusNum");
        final int projectId = rs.getInt("projectId");
        final Timestamp createdTime = rs.getTimestamp("createdTime");
        final Timestamp estimatedEndTime = rs.getTimestamp("estimatedEndTime");
        final Timestamp deadline = rs.getTimestamp("deadline");
        return new Task(taskId, title, description, priority, statusNum, projectId, createdTime, estimatedEndTime, deadline);
    }


    @Override
    public List<Task> getTaskList() {
        List<Task> taskList = new ArrayList<>();

        try (final Connection con = DBConnectorUtil.getConnection()) {
            final PreparedStatement pre = con.prepareStatement("SELECT * FROM task");
            final ResultSet rs = pre.executeQuery();
            while (rs.next()) {
                Task task = fromResultSet(rs);
                taskList.add(task);
            }
            rs.close();
            pre.close();
            return taskList;
        } catch (final SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public Task getTask(int taskId) {
        Task result = null;
        try (final Connection con = DBConnectorUtil.getConnection()) {
            final PreparedStatement pre = con.prepareStatement("SELECT * FROM task WHERE taskId = ?");
            pre.setString(1, String.valueOf(taskId));

            final ResultSet rs = pre.executeQuery();
            rs.next(); // move to first row
            result = fromResultSet(rs);
            rs.close();
            pre.close();

            return result;
        } catch (final SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public Task insertTask(Task t) {
        try (final Connection con = DBConnectorUtil.getConnection()) {
            final PreparedStatement pre = con.prepareStatement("INSERT INTO task VALUES(?,?,?,?,?,?,?,?,?)");
            pre.setInt(1, t.getTaskId());
            pre.setString(2, t.getTitle());
            pre.setString(3, t.getDescription());
            pre.setInt(4, t.getPriority());
            pre.setInt(5, t.getStatusNum());
            pre.setInt(6, t.getProjectId());
            pre.setTimestamp(7, t.getCreatedTime());
            pre.setTimestamp(8, t.getEstimatedEndTime());
            pre.setTimestamp(9, t.getDeadline());
            pre.executeUpdate();
            pre.close();
            return t;
        } catch (final SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    } // TODO handling NULL values

    @Override
    public Task updateTask(Task t) {
        try (final Connection con = DBConnectorUtil.getConnection()) {
            final PreparedStatement pre = con.prepareStatement("UPDATE task SET title = ?, description = ?, priority = ?, statusNum = ?, estimatedEndTime = ? deadline = ? WHERE taskId = ?");
            pre.setString(1, t.getTitle());
            pre.setString(2, t.getDescription());
            pre.setInt(3, t.getPriority());
            pre.setInt(4, t.getStatusNum());
            pre.setTimestamp(5, t.getEstimatedEndTime());
            pre.setTimestamp(6, t.getDeadline());
            pre.setInt(7, t.getTaskId());
            pre.executeUpdate();
            pre.close();
            return t;
        } catch (final SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public void deleteTask(Task t) {
        try (final Connection con = DBConnectorUtil.getConnection()) {
            final PreparedStatement pre = con.prepareStatement("DELETE FROM task WHERE taskId = ?");
            pre.setInt(1, t.getTaskId());
            pre.executeUpdate();
            pre.close();
        } catch (final SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}