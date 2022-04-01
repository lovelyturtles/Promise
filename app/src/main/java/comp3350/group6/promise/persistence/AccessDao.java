package comp3350.group6.promise.persistence;

import java.sql.Timestamp;
import java.util.List;

import comp3350.group6.promise.objects.Access;
import comp3350.group6.promise.objects.Project;
import comp3350.group6.promise.objects.User;
import comp3350.group6.promise.objects.enumClasses.AccessRole;

public interface AccessDao {

    List<Access> getAccessByProject(int projectId);

    List<Access> getAccessByUser(int userId);

    Access getAccessByIDs(int userId, int projectId);

    Access insertAccess(Access access);

    Access updateAccess(Access access);

    //get a list of the users in the project that have the role specified by "role"
    List<Integer> getRoleByProjectID( int projectID, AccessRole role );

    //check if this userID has access to this projectID
    boolean hasAccess( int userID, int projectID );

}
