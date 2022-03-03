package comp3350.group6.promise.tests;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import comp3350.group6.promise.tests.business.ProjectServiceTest;
import comp3350.group6.promise.tests.objects.ProjectTest;


@RunWith(Suite.class)
@Suite.SuiteClasses({
        ProjectServiceTest.class,
        ProjectTest.class,

})

public class AllUnitTests {}
