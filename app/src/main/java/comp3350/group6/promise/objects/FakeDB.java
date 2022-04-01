package comp3350.group6.promise.objects;

import java.util.ArrayList;

import comp3350.group6.promise.application.Service;
import comp3350.group6.promise.objects.Exceptions.EmptyInputException;

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

        //fillAccounts();
        fillProjects();

    }

    private static void fillAccounts() throws Exception {

        Service.accounts.register( "wharfhorse@app.com", "Calvin", "password1", "What" );
        Service.accounts.register( "lazerrazor@bob.com", "Louise", "password2", "No" );
        Service.accounts.register( "bapple@app.com", "Ben", "password3", "No" );
        Service.accounts.register( "wendall@app.com", "Wendall", "password4",  "Forts" );
        Service.accounts.register( "lyjericacran@app.com", "Lyjerica", "password5", "Cranberries" );
        Service.accounts.register( "pimmypr@app.com", "Tina", "password6", "No" );

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
