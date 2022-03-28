package comp3350.group6.promise.application;

import comp3350.group6.promise.business.AccessService;
import comp3350.group6.promise.business.AccountService;
import comp3350.group6.promise.business.ProjectService;
import comp3350.group6.promise.business.TaskService;
import comp3350.group6.promise.business.UserService;
import comp3350.group6.promise.persistence.HandleDao;
import comp3350.group6.promise.persistence.TaskDao;
import comp3350.group6.promise.persistence.hsqldb.HandleImp;
import comp3350.group6.promise.persistence.hsqldb.TaskImp;
import comp3350.group6.promise.persistence.stub.HandleImpNoDB;
import comp3350.group6.promise.persistence.stub.TaskImpNoDB;
//import comp3350.group6.promise.persistence.stub.TaskImpNoDB;

public class Service {

    private static TaskDao taskImp = null;
    private static HandleDao handleImp = null;

    public static UserService users = UserService.getInstance();
    public static AccountService accounts = AccountService.getInstance();
    public static ProjectService projects = ProjectService.getInstance();
    public static TaskService tasks = TaskService.getInstance();
    public static AccessService accesses = AccessService.getInstance();

    public static synchronized TaskDao getTaskImp(boolean forProduction) {
        if (taskImp == null) {
            if (forProduction)
                taskImp = new TaskImp();
            else
                taskImp = new TaskImpNoDB();
        }
        return taskImp;
    }

    public static synchronized HandleDao getHandleImp(boolean forProduction){
        if(handleImp == null){
            if(forProduction)
                handleImp = new HandleImp();
            else
                handleImp = new HandleImpNoDB();
        }
        return handleImp;
    }

    public static synchronized void clean(){
        taskImp = null;
        handleImp = null;
    }
    
}
