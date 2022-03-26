package comp3350.group6.promise.business;

import java.util.ArrayList;
import java.util.List;

import comp3350.group6.promise.application.Service;
import comp3350.group6.promise.objects.Handle;
import comp3350.group6.promise.persistence.HandleDao;
import comp3350.group6.promise.persistence.hsqldb.HandleImp;

/*
    This class is used to handle the relationship between User and Task
*/
public class HandleService {
    private final HandleDao handleDao;
    private List<Handle> listOfUserTask;
    private List<Handle> listOfTaskUser;


    public HandleService(boolean forProduction) {
        handleDao = Service.getHandleImp(forProduction);
        listOfUserTask = new ArrayList<>();
        listOfTaskUser = new ArrayList<>();
    }

    public List<Handle> getListOfUserTask(int taskId) {
        listOfUserTask = handleDao.getUserTask(taskId);
        return listOfUserTask;
    } // either return empty list or list of users associated with this task

    public List<Handle> getListOfTaskUser(int userId) {
        listOfTaskUser = handleDao.getTaskUser(userId);
        return listOfTaskUser;
    } // either return empty list or list of tasks associated with this user

    public void insertHandle(Handle handle){
        handleDao.insertHandle(handle);
    }
}
