package comp3350.group6.promise.application;

import comp3350.group6.promise.business.AccountService;
import comp3350.group6.promise.business.ProjectService;
import comp3350.group6.promise.business.TaskService;
import comp3350.group6.promise.business.UserService;
import comp3350.group6.promise.objects.Account;
import comp3350.group6.promise.objects.Project;

//should CurrentSession use accountDao, projectDao instead?
public class CurrentSession {

    public static Account currentUser    = null;

//    Do we need these?
//    public static boolean emailCheck     = false;
//    public static boolean passwordCheck  = false;
//    public static Project currentProject = null;

}
