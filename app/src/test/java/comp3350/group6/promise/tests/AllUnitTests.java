package comp3350.group6.promise.tests;

import junit.framework.Test;
import junit.framework.TestSuite;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import comp3350.group6.promise.tests.business.testFakeProjectDB;
import comp3350.group6.promise.tests.objects.ProjectTest;


@RunWith(Suite.class)
@Suite.SuiteClasses({
        testFakeProjectDB.class,
        ProjectTest.class,

})

public class AllUnitTests {}
