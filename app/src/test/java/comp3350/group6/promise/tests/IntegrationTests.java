package comp3350.group6.promise.tests;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import comp3350.group6.promise.tests.business.AccessServiceTest;
import comp3350.group6.promise.tests.business.AccountServiceTest;
import comp3350.group6.promise.tests.business.AccountUserServiceTest;
import comp3350.group6.promise.tests.business.HandleServiceTest;
import comp3350.group6.promise.tests.business.NotifServiceTest;
import comp3350.group6.promise.tests.business.ProjectServiceTest;
import comp3350.group6.promise.tests.business.TaskServiceTest;
import comp3350.group6.promise.tests.business.UserServiceIT;
import comp3350.group6.promise.tests.business.UserServiceTest;
import comp3350.group6.promise.tests.objects.UserTest;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        UserTest.class,
        AccessServiceTest.class,
        AccountServiceTest.class,
        TaskServiceTest.class,
        ProjectServiceTest.class,
        UserServiceTest.class,
        HandleServiceTest.class,
        AccountUserServiceTest.class,
        NotifServiceTest.class,
        UserServiceIT.class
})


public class IntegrationTests {

}
