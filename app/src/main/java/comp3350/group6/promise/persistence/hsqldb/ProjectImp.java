package comp3350.group6.promise.persistence.hsqldb;

import org.apache.commons.codec.binary.Base64;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.xml.transform.Result;

import comp3350.group6.promise.persistence.ProjectDao;
import comp3350.group6.promise.util.DBConnectorUtil;

public class ProjectImp implements ProjectDao{

    /*
     * Used to create a Project object from a SQL ResultSet 
     */
    private Project createProjectObject(ResultSet rs){
        int projectID = rs.getInt("projectId");
        String projectName = rs.getString("projectName");
        String statement = rs.getString("statement");
        int statusNum = rs.getInt("statusNum");
        Timestamp createdTime = rs.getTimestamp("createdTime");
        Timestamp estimatedEndTime = rs.getTimestamp("estimatedEndTime");

        return new Project(projectID, projectName, statement, statusNum, createdTime, estimatedEndTime);
    }

    @Override
    List<Project> getProjectList() {
        final List<Project> projects = new ArrayList<>();

        try(Connection con = DBConnectorUtil.getConnection();
            PreparedStatement pstmt = con.prepareStatement("select * from project");
            ResultSet rs = pstmt.executeQuery()){
            
            while(rs.next()){
                Project p = createProjectObject(rs);
                projects.add(p);
            }

        } catch (SQLExceoption e) {
            throw new PersistenceException(e);
        }

        return projects;
    }

    @Override
    Project insertProject(Project project){
        try(Connection con = DBConnectorUtil.getConnection();
            PreparedStatement pstmt = con.prepareStatement("insert into project values (?,?,?,?,?,?)")){ 
            
            pstmt.setInt(1, project.getProjectID());
            pstmt.setString(2, project.getProjectName());
            pstmt.setString(3, project.getStatement());
            pstmt.setString(4, project.getStatusNum());
            pstmt.setTimestamp(5, project.getCreatedTime());
            pstmt.setTimestamp(6, project.getEstimatedEndTime());
            
            pstmt.executeUpdate();

        } catch (SQLExceoption e) {
            throw new PersistenceException(e);
        }

        return project;
    }

    @Override
    Project updateProject(Project project){
        try(Connection con = DBConnectorUtil.getConnection();
            PreparedStatement pstmt = con.prepareStatement("update project set projectName = ?, statement = ?, statusNum = ?, estimatedEndTime = ? where projectId = ?")){ 
            
            pstmt.setString(1, project.getProjectName());
            pstmt.setString(2, project.getStatement());
            pstmt.setInt(3, project.getStatusNum());
            pstmt.setTimestamp(4, project.getEstimatedEndTime());
            pstmt.setInt(5, project.getProjectID());
            
            pstmt.executeUpdate();

        } catch (SQLExceoption e) {
            throw new PersistenceException(e);
        }

        return project;
    }

    @Override
    void deleteProject(Project project){
        try(Connection con = DBConnectorUtil.getConnection();
            PreparedStatement pstmt = con.prepareStatement("delete from project where projectId = ?")){ 
            
            pstmt.setString(1, project.getProjectID());
            
            pstmt.executeUpdate();

        } catch (SQLExceoption e) {
            throw new PersistenceException(e);
        }

    }
    
}
