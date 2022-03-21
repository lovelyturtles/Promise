package comp3350.group6.promise.business;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import comp3350.group6.promise.objects.Access;
import comp3350.group6.promise.objects.Project;
import comp3350.group6.promise.objects.User;
import comp3350.group6.promise.persistence.AccessDao;
import comp3350.group6.promise.persistence.hsqldb.AccessImp;

/*
    Access = User "participates" Project relationship
 */
public class AccessService {

    AccessDao accessDao;

    public AccessService(){
        accessDao = new AccessImp();
    }

    public AccessService(AccessDao accessDao){
        this();
        this.accessDao = accessDao;
    }

    // returns a list of access a project has
    public List<Access> getProjectAccess(int projectId){
        List<Access> accessList = accessDao.getAccessByProject(projectId);
        return Collections.unmodifiableList(accessList);
    }

    //TODO: handle exceptions -> maybe change user exceptions to persistenceException
    public List<User> getUsers(int projectId) throws Exception {
        List<Access> accessList = getProjectAccess(projectId);
        List<User> userList = new ArrayList<>();
        UserService userService = new UserService();

        for (Access access: accessList) {
            userList.add(userService.getUserByUserId(access.getUserId()));
        }

        return Collections.unmodifiableList(userList);
    }

    // returns a list of access a user has
    public List<Access> getUserAccess(int userId){
        List<Access> accessList = accessDao.getAccessByUser(userId);
        return Collections.unmodifiableList(accessList);
    }

    public List<Project> getProjects(int userId) {
        List<Access> accessList = getUserAccess(userId);
        List<Project> projectList = new ArrayList<>();
        ProjectService projectService = new ProjectService();

        for (Access access: accessList) {
            projectList.add(projectService.getProjectByID(access.getUserId()));
        }

        return Collections.unmodifiableList(projectList);
    }

    // insert a new access to the DB
    public Access insertAccess(Access access){ return accessDao.insertAccess(access); }

    // update the role of the access
    public Access updateAccess(Access access){ return accessDao.updateAccess(access); }
}