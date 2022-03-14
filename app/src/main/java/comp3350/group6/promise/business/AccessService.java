package comp3350.group6.promise.business;

import java.util.Collections;
import java.util.List;

import comp3350.group6.promise.objects.Access;
import comp3350.group6.promise.persistence.AccessDao;
import comp3350.group6.promise.persistence.ProjectDao;
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
    List<Access> getProjectAccess(int projectId){
        List<Access> accessList = accessDao.getAccessByProject(projectId);
        return Collections.unmodifiableList(accessList);
    }

    // returns a list of access a user has
    List<Access> getUserAccess(int userId){
        List<Access> accessList = accessDao.getAccessByUser(userId);
        return Collections.unmodifiableList(accessList);
    }
}
