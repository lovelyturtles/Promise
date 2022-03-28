package comp3350.group6.promise.objects;

import java.util.ArrayList;

import comp3350.group6.promise.application.CurrentSession;
import comp3350.group6.promise.application.Service;
import comp3350.group6.promise.business.EmptyInputException;

public class FakeDB {
    public static int    generatedUserIDCount;
    public static ArrayList<User>       users;
    public static ArrayList<Account> accounts;
    public static ArrayList<Project> projects;
    public static ArrayList<Task>       tasks;
    public static ArrayList<Access>  accesses;

    public static void initialize() throws Exception {

        generatedUserIDCount = 0;
        users    = new ArrayList<User>();
        accounts = new ArrayList<Account>();
        projects = new ArrayList<Project>();
        accesses  = new ArrayList<Access>();
        tasks    = new ArrayList<Task>();

        fillAccounts();
        fillProjects();

    }

    private static void fillAccounts() throws Exception {

        Service.accounts.createAccount( "wharfhorse@app.com", "password1", "Calvin", "What" );
        Service.accounts.createAccount( "lazerrazor@bob.com", "password2", "Louise", "No" );
        Service.accounts.createAccount( "bapple@app.com", "password3", "Ben", "No" );
        Service.accounts.createAccount( "wendall@app.com", "password4", "Wendall", "Forts" );
        Service.accounts.createAccount( "lyjericacran@app.com", "password5", "Lyjerica", "Cranberries" );
        Service.accounts.createAccount( "pimmypr@app.com", "password6", "Tina", "No" );

    }

    private static void fillProjects(){

        try {
            Service.projects.insertProject(new Project("Cereals and Things", "Let's talk about cereal"));
            Service.projects.insertProject(new Project("Astrophysics Calendar", "This is probably for school"));
            Service.projects.insertProject(new Project("New Theater Plan", "We're opening a movie theater"));
            Service.projects.insertProject(new Project("How to open a door", "This is very difficult for me"));
        } catch (EmptyInputException e){
            System.out.println(e);
        }
    }
//
//    private static void fillAccessDB(){
//
//    }
//
//    private static void fillTaskDB(){
//
//    }


}
