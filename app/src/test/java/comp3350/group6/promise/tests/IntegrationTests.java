package comp3350.group6.promise.tests;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import comp3350.group6.promise.tests.business.AccountUserServiceTest;
import comp3350.group6.promise.tests.business.HandleServiceTest;
import comp3350.group6.promise.tests.business.NotifServiceTest;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        HandleServiceTest.class,
        AccountUserServiceTest.class,
        NotifServiceTest.class
})


public class IntegrationTests {

}
