package comp3350.group6.promise.business;


import java.util.List;

import comp3350.group6.promise.objects.Task;
import comp3350.group6.promise.persistence.TaskDao;
import comp3350.group6.promise.persistence.hsqldb.TaskImp;

public class TaskService {
    private List<Task> allTask;
    private TaskDao taskDao;

    public TaskService() {
        taskDao = new TaskImp();
        allTask = null;
    }

    public TaskService(TaskDao db) {
        this();
        this.taskDao = db;
    }

    public Task getTask(int taskId) {
        return taskDao.getTask(taskId);
    }

    public Task insertTask(Task newTask) {
        return taskDao.insertTask(newTask);
    }

    public void deleteTask(Task task) {
        taskDao.deleteTask(task);
    }


    public List<Task> getAllTask() {
        allTask = taskDao.getTaskList();
        return allTask;
    }

}


// TODO: sorting, compare
