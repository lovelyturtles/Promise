package comp3350.group6.promise.tests.business;


import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

import comp3350.group6.promise.application.Main;
import comp3350.group6.promise.application.Service;
import comp3350.group6.promise.business.HandleService;
import comp3350.group6.promise.objects.Handle;
import comp3350.group6.promise.persistence.HandleDao;
import comp3350.group6.promise.persistence.hsqldb.HandleImp;
import comp3350.group6.promise.util.TestUtils;


// This is Integration test class for Handle
public class HandleServiceTest {
    private HandleService handleService;
    private File testDB;

    @Before
    public void setUp() throws IOException {
        System.out.println("Start Integration Test for HandleService");
        testDB = TestUtils.copyDB();
        handleService = new HandleService(true);
        assertNotNull(this.handleService);
    }



    @Test
    public void testGetListOfUserTask() {
        System.out.println("\nStarting testGetListUserTask");

    }





    @After
    public void tearDown(){
        System.out.println("Reset database");
        assertTrue(testDB.delete());
        Service.clean();
    }


}
