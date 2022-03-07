package comp3350.group6.promise.application;

import comp3350.group6.promise.persistence.TaskDao;
import comp3350.group6.promise.persistence.hsqldb.TaskImp;
//import comp3350.group6.promise.persistence.stub.TaskImpNoDB;

public class Service {

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
