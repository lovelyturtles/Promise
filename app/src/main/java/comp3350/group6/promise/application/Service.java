package comp3350.group6.promise.application;

import comp3350.group6.promise.business.AccessService;
import comp3350.group6.promise.business.AccountService;
import comp3350.group6.promise.business.ProjectService;
import comp3350.group6.promise.business.TaskService;
import comp3350.group6.promise.business.UserService;
import comp3350.group6.promise.persistence.TaskDao;
import comp3350.group6.promise.persistence.hsqldb.TaskImp;
//import comp3350.group6.promise.persistence.stub.TaskImpNoDB;

public class Service {

    public static UserService users = new UserService();
    public static AccountService accounts = new AccountService();
    public static ProjectService projects = new ProjectService();
    public static TaskService tasks = new TaskService();
    public static AccessService accesses = new AccessService();

    private static TaskDao taskImp = null;
    public static synchronized TaskDao getTaskImp(boolean forProduction) {
        if (taskImp == null) {
            if (forProduction)
                taskImp = new TaskImp();
            else
                taskImp = new TaskImp();
        }
        return taskImp;
    }


    public static synchronized void clean(){
        taskImp = null;
    }

}
