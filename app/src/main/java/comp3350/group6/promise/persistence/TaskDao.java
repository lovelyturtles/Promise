package comp3350.group6.promise.persistence;


import java.sql.SQLException;
import java.util.List;

import comp3350.group6.promise.objects.Task;

public interface TaskDao {
    List<Task> getTaskList() ;

    Task getTask(int taskId) ;

    Task insertTask(Task t) ; // or pass ID? need project ID?

    Task updateTask(Task t) ;

    void deleteTask(Task t) ;

}
