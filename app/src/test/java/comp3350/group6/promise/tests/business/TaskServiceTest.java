package comp3350.group6.promise.tests.business;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;


import java.util.List;

import comp3350.group6.promise.business.TaskService;
import comp3350.group6.promise.objects.Task;

public class TaskServiceTest {

    private TaskService taskService;

    /* Each time the fake DB get reinitialized */
    @Before
    public void setup() {
        System.out.println("Starting test for TaskService");
        taskService = new TaskService(false); // false init a fake DB
    }

    @Test
    public void testGetTaskList() {
        System.out.println("\nStarting testGetTaskList");
        List<Task> taskList = taskService.getAllTask();
        int size = 3;

        assertEquals(size, taskList.size());

        System.out.println("Finished testGetTaskList");
    }

    @Test
    public void testGetTaskById() {
        System.out.println("\nStarting testGetTaskById");
        List<Task> taskList = taskService.getAllTask();

        assertEquals(3, taskList.size());

        Task expected = new Task(1);
        Task actual = taskService.getTask(1);

        assertEquals(expected, actual);
        assertNull(taskService.getTask(4));

        System.out.println("Finished testGetTaskById");
    }

    @Test
    public void testInsertTask() {
        System.out.println("\nStarting testInsertTask");

        List<Task> taskList = taskService.getAllTask();
        int oldSize = taskList.size();
        int newSize = oldSize + 1;
        Task toInsert = new Task(100);

        taskService.insertTask(toInsert);

        assertEquals(newSize, taskService.getAllTask().size());

        taskService.insertTask(new Task(1));

        assertEquals(newSize, taskService.getAllTask().size());

        System.out.println("Finished testInsertTask");

    }

    @Test
    public void testUpdateTask() {
        System.out.println("\nStarting testUpdateTask");

        Task toUpdate = new Task(1, "updatedTask", "default", 0, 0, 0, null, null, null);

        taskService.updateTask(toUpdate);

        String newTitle = "updatedTask";
        Task actual = taskService.getTask(1);

        assertEquals(newTitle, actual.getTitle());

        System.out.println("Finished testUpdateTask");
    }

    @Test
    public void testDeleteTask() {
        System.out.println("\nStarting testDeleteTask");

        List<Task> taskList = taskService.getAllTask();
        int oldSize = taskList.size();
        int newSize = oldSize - 1;
        Task toDelete = new Task(1);

        taskService.deleteTask(toDelete);

        assertEquals(newSize, taskService.getAllTask().size());

        taskService.deleteTask(new Task(10));

        assertEquals(newSize, taskService.getAllTask().size());

        System.out.println("Finished testDeleteTask");
    }
}