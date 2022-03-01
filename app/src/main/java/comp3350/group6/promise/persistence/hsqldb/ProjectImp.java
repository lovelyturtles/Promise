package comp3350.group6.promise.persistence.hsqldb;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Timestamp;
import java.sql.SQLException;

import java.util.List;
import java.util.ArrayList;

import comp3350.group6.promise.objects.Project;
import comp3350.group6.promise.persistence.ProjectDao;
import comp3350.group6.promise.util.DBConnectorUtil;

public class ProjectImp implements ProjectDao{

    /*
     * Used to create a Project object from a SQL ResultSet 
     */
    private Project createProjectObject(ResultSet rs) throws SQLException{
        int projectID = rs.getInt("projectId");
        String projectName = rs.getString("projectName");
        String statement = rs.getString("statement");
        int statusNum = rs.getInt("statusNum");
        Timestamp createdTime = rs.getTimestamp("createdTime");
        Timestamp estimatedEndTime = rs.getTimestamp("estimatedEndTime");

        return new Project(projectID, projectName, statement, statusNum, createdTime, estimatedEndTime);
    }

    @Override
    public List<Project> getProjectList() {
        final List<Project> projects = new ArrayList<Project>();

        try(Connection con = DBConnectorUtil.getConnection();
            PreparedStatement pstmt = con.prepareStatement("select * from project");
            ResultSet rs = pstmt.executeQuery()){
            
            while(rs.next()){
                Project p = createProjectObject(rs);
                projects.add(p);
            }

        } catch (SQLException e) {
            throw new PersistenceException(e);
        }

        return projects;
    }

    @Override
    public Project getProjectByID(int projectID) {
        //TODO: implement
        return null;
    }

    @Override
    public Project insertProject(Project project){
        String query = "insert into project (projectName,statement,statusNum,createdTime,estimatedEndTime) values (?,?,?,?,?)";
        try(Connection con = DBConnectorUtil.getConnection();
            PreparedStatement pstmt = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)){

            pstmt.setString(1, project.getProjectName());
            pstmt.setString(2, project.getStatement());
            pstmt.setInt(3, project.getStatusNum());
            pstmt.setTimestamp(4, project.getCreatedTime());
            pstmt.setTimestamp(5, project.getEstimatedEndTime());
            
            pstmt.executeUpdate();

            ResultSet generatedKeys = pstmt.getGeneratedKeys();

            generatedKeys.next();

            project.setProjectID(generatedKeys.getInt(1));

            con.close();

        } catch (SQLException e) {
            throw new PersistenceException(e);
        }

        return project;
    }

    @Override
    public Project updateProject(Project project){
        try(Connection con = DBConnectorUtil.getConnection();
            PreparedStatement pstmt = con.prepareStatement("update project set projectName = ?, statement = ?, statusNum = ?, estimatedEndTime = ? where projectId = ?")){ 
            
            pstmt.setString(1, project.getProjectName());
            pstmt.setString(2, project.getStatement());
            pstmt.setInt(3, project.getStatusNum());
            pstmt.setTimestamp(4, project.getEstimatedEndTime());
            pstmt.setInt(5, project.getProjectID());
            
            pstmt.executeUpdate();

        } catch (SQLException e) {
            throw new PersistenceException(e);
        }

        return project;
    }

    @Override
    public void deleteProject(Project project){
        try(Connection con = DBConnectorUtil.getConnection();
            PreparedStatement pstmt = con.prepareStatement("delete from project where projectId = ?")){ 
            
            pstmt.setInt(1, project.getProjectID());
            
            pstmt.executeUpdate();

        } catch (SQLException e) {
            throw new PersistenceException(e);
        }

    }
    
}
