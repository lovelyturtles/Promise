package comp3350.group6.promise.tests;

import junit.framework.Test;
import junit.framework.TestSuite;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import comp3350.group6.promise.tests.business.AccountServiceTest;
import comp3350.group6.promise.tests.business.NotifServiceTest;
import comp3350.group6.promise.tests.business.ProjectServiceTest;
import comp3350.group6.promise.tests.business.TaskServiceTest;
import comp3350.group6.promise.tests.business.UserServiceTest;
import comp3350.group6.promise.tests.business.testFakeProjectDB;
import comp3350.group6.promise.tests.objects.AccountTest;
import comp3350.group6.promise.tests.objects.NotifTest;
import comp3350.group6.promise.tests.objects.ProjectTest;
import comp3350.group6.promise.tests.objects.TaskTest;
import comp3350.group6.promise.tests.objects.UserTest;


@RunWith(Suite.class)
@Suite.SuiteClasses({
        AccountServiceTest.class,
        NotifServiceTest.class,
        ProjectServiceTest.class,
        TaskServiceTest.class,
        testFakeProjectDB.class,
        UserServiceTest.class,
        AccountTest.class,
        NotifTest.class,
        ProjectTest.class,
        TaskTest.class,
        UserTest.class
})

public class AllUnitTests {}
