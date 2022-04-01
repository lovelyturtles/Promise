package comp3350.group6.promise.tests;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import comp3350.group6.promise.tests.business.AccessServiceTest;
import comp3350.group6.promise.tests.business.AccountServiceTest;
import comp3350.group6.promise.tests.business.AccountUserServiceTest;
import comp3350.group6.promise.tests.business.HandleServiceTest;
import comp3350.group6.promise.tests.business.NotifServiceTest;
import comp3350.group6.promise.tests.business.ProjectServiceIT;
import comp3350.group6.promise.tests.business.TaskServiceTest;
import comp3350.group6.promise.tests.business.UserServiceTest;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        AccessServiceTest.class,
        AccountServiceTest.class,
        TaskServiceTest.class,
        ProjectServiceIT.class,
        UserServiceTest.class,
        HandleServiceTest.class,
        AccountUserServiceTest.class,
        NotifServiceTest.class
})


public class IntegrationTests {

}
