package comp3350.group6.promise.util;

import android.util.Log;

import java.sql.Time;
import java.sql.Timestamp;
import java.util.ArrayList;

import comp3350.group6.promise.business.AccountService;
import comp3350.group6.promise.business.NotifService;
import comp3350.group6.promise.business.ProjectService;
import comp3350.group6.promise.business.TaskService;
import comp3350.group6.promise.business.UserService;
import comp3350.group6.promise.objects.Project;
import comp3350.group6.promise.objects.Task;
import comp3350.group6.promise.objects.User;

public class TestDataGenerator {

    private AccountService accountService = AccountService.getInstance();
    private UserService userService = UserService.getInstance();
    private ProjectService projectService = ProjectService.getInstance();
    private TaskService taskService = TaskService.getInstance();
    private NotifService notificationService = NotifService.getInstance();

    private int numProjects;
    private int numTasksPerProject;

    private ArrayList<Integer> accountIDs;
    private ArrayList<Integer> projectIDs;
    private ArrayList<Integer> taskIDs;

    public TestDataGenerator(int numProjects, int numTasksPerProject) {
        this.numProjects = numProjects;
        this.numTasksPerProject = numTasksPerProject;
        accountIDs = new ArrayList<Integer>();
        projectIDs = new ArrayList<Integer>();
        taskIDs = new ArrayList<Integer>();
    }

    public void generate() {
        try {
            generateAccounts();
            generateProjects();
            generateTasks();
        }
        catch (Exception e) {
            Log.e("Test Data Generation", e.getMessage());
        }
    }

    private void generateAccounts() throws Exception {
        accountIDs.add(this.accountService.createAccount("test@email.com", "12345", "Kennith Hardy", "Hello!"));
        accountIDs.add(this.accountService.createAccount("test1@email.com", "12345", "Raymond Bradford", "Hello!"));
        accountIDs.add(this.accountService.createAccount("test2@email.com", "12345", "Glenn Erickson", "Hello!"));
        accountIDs.add(this.accountService.createAccount("test3@email.com", "12345", "Corrine Kendall", "Hello!"));
        accountIDs.add(this.accountService.createAccount("test5@email.com", "12345", "Stacey Ridge", "Hello!"));
        Log.e("Generated Account IDs", accountIDs.toString());
    }

    private void generateProjects() throws Exception {
        for(int i = 0; i < numProjects; i++) {
            String projectName = "Project " + letterFromNumber(i);
            Project project = new Project(projectName, "This is the description for " + projectName + ".");
            projectIDs.add(this.projectService.insertProject(project).getProjectID());
        }
        Log.e("Generated Project IDs", projectIDs.toString());
    }

    private void generateTasks() {
        for(int projectID: projectIDs) {
            for(int i = 0; i < numTasksPerProject; i++) {
                String taskName = "Task " + letterFromNumber(i);
                Task task = new Task(
                        taskName,
                        "This is the description for " + taskName + ".",
                        (int) Math.round(Math.random() * 10),
                        (int) Math.round(Math.random() * 3),
                        projectID,
                        generateRandomTimeInNextNDays(2),
                        generateRandomTimeInNextNDays(4)
                );
                taskIDs.add(this.taskService.insertTask(task).getTaskId());
            }
        }
        Log.e("Generated Task IDs", taskIDs.toString());
    }

    private char letterFromNumber(int number) {
        return (char) ('A' + number);
    }

    private Timestamp generateRandomTimeInNextNDays(int n) {
        return new Timestamp(
                System.currentTimeMillis() + (int) Math.random() * (1000 * 60 * 60 * 24 * n)
        );
    }


}
