package comp3350.group6.promise.persistence.stub;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import comp3350.group6.promise.objects.FakeDB;
import comp3350.group6.promise.objects.Task;
import comp3350.group6.promise.persistence.TaskDao;

public class TaskImpNoDB implements TaskDao {

    ArrayList<Task> taskList;
    Task task1, task2, task3;
    Timestamp cTime;
    Timestamp eTime;
    Timestamp dTime;


    public TaskImpNoDB() {
        initDB();
    }

    private void initDB() {
        cTime = new Timestamp(System.currentTimeMillis());
        eTime = new Timestamp(System.currentTimeMillis());
        dTime = new Timestamp(System.currentTimeMillis());

        task1 = new Task(1, "myTask", "default", 0, 0, 0, cTime, eTime, dTime);
        task2 = new Task(2, "myTask", "default", 0, 0, 0, cTime, eTime, dTime);
        task3 = new Task(3, "myTask", "default", 0, 0, 0, cTime, eTime, dTime);

        taskList = new ArrayList<>();
        taskList.add(task1);
        taskList.add(task2);
        taskList.add(task3);
    }

    @Override
    public List<Task> getTaskList() {
        return this.taskList;
    }

    @Override
    public Task getTask(int taskId) {
        if (getIndex(taskId) >= 0)
            return taskList.get(getIndex(taskId));
        return null;
    }

    @Override
    public Task insertTask(Task t) {
        if (getIndex(t.getTaskId()) == -1) {
            taskList.add(t);
            return t;
        }
        return null;
    }

    @Override
    public Task updateTask(Task t) {
        if (getIndex(t.getTaskId()) >= 0) {
            taskList.set(getIndex(t.getTaskId()), t);
            return t;
        }
        return null;
    }

    @Override
    public void deleteTask(Task t) {
        if (getIndex(t.getTaskId()) >= 0)
            taskList.remove(getIndex(t.getTaskId()));
    }

    private int getIndex(int taskId) {
        for (int i = 0; i < taskList.size(); i++) {
            int res = taskList.get(i).getTaskId();
            if (res == taskId)
                return i;
        }
        return -1;
    }
}
