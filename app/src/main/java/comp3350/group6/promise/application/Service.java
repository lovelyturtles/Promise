package comp3350.group6.promise.application;

import comp3350.group6.promise.business.AccessService;
import comp3350.group6.promise.business.AccountService;
import comp3350.group6.promise.business.AccountUserService;
import comp3350.group6.promise.business.NotifService;
import comp3350.group6.promise.business.ProjectService;
import comp3350.group6.promise.business.TaskService;
import comp3350.group6.promise.business.UserService;
import comp3350.group6.promise.objects.AccountUser;
import comp3350.group6.promise.persistence.HandleDao;
import comp3350.group6.promise.persistence.TaskDao;
import comp3350.group6.promise.persistence.hsqldb.HandleImp;
import comp3350.group6.promise.persistence.hsqldb.TaskImp;
import comp3350.group6.promise.persistence.stub.HandleImpNoDB;
import comp3350.group6.promise.persistence.stub.TaskImpNoDB;
//import comp3350.group6.promise.persistence.stub.TaskImpNoDB;

public class Service {

    public static UserService users = new UserService();
    public static AccountService accounts = new AccountService();
    public static AccountUserService accountUser = new AccountUserService();
    public static ProjectService projects = new ProjectService();
    public static TaskService tasks = new TaskService();
    public static AccessService accesses = new AccessService();
    public static NotifService notifications = new NotifService();

    private static TaskDao taskImp = null;
    private static HandleDao handleImp = null;

    /* Should this go into its own class? */
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
