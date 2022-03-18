package comp3350.group6.promise.objects;

import comp3350.group6.promise.business.AccountService;
import comp3350.group6.promise.business.ProjectService;
import comp3350.group6.promise.business.TaskService;
import comp3350.group6.promise.business.UserService;

public class CurrentSession {

    public static boolean emailCheck     = false;
    public static boolean passwordCheck  = false;
    public static Account currentUser    = null;
    public static Project currentProject = null;

    public static UserService       users = UserService.getInstance();
    public static AccountService accounts = AccountService.getInstance();
    public static ProjectService projects = ProjectService.getInstance();
    public static TaskService       tasks = TaskService.getInstance();
    //public static AccessService accesses = new AccessService();

}
