package comp3350.group6.promise.business;


import java.util.List;

import comp3350.group6.promise.application.Service;
import comp3350.group6.promise.objects.Task;
import comp3350.group6.promise.persistence.TaskDao;
import comp3350.group6.promise.persistence.hsqldb.TaskImp;
//import comp3350.group6.promise.persistence.stub.TaskImpNoDB;

public class TaskService {
    private List<Task> allTask;
    private TaskDao taskDao;


    public TaskService() {
        allTask = null;
        taskDao = new TaskImp();
    }

    public TaskService(boolean forProduction) {
        taskDao = Service.getTaskImp(forProduction);
        allTask = null;
    }

    public TaskService(TaskDao db) {
        this();
        this.taskDao = db;
    }

    public Task getTask(int taskId) {
        return taskDao.getTask(taskId);
    }

    public List<Task> getTasksByProjectId(int projectId) { return taskDao.getTasksByProjectId(projectId); }

    public Task insertTask(Task newTask) {
        return taskDao.insertTask(newTask);
    }

    public void deleteTask(Task task) {
        taskDao.deleteTask(task);
    }

    public void updateTask(Task task) {
        taskDao.updateTask(task);
    }

    public List<Task> getAllTask() {
        allTask = taskDao.getTaskList();
        return allTask;
    }

}


// TODO: sorting, compare
