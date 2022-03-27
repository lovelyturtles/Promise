package comp3350.group6.promise.tests.business;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.List;

import comp3350.group6.promise.application.Main;
import comp3350.group6.promise.application.Service;
import comp3350.group6.promise.business.HandleService;
import comp3350.group6.promise.objects.Handle;
import comp3350.group6.promise.persistence.HandleDao;
import comp3350.group6.promise.persistence.hsqldb.HandleImp;
import comp3350.group6.promise.util.DBConnectorUtil;
import comp3350.group6.promise.util.TestUtils;


// This is Integration test class for Handle
public class HandleServiceTest {
    private HandleService handleService;
    private File testDB;

    @Before
    public void setUp() throws IOException {
        System.out.println("Start Integration Test for HandleService");
//        testDB = TestUtils.copyDB();
        DBConnectorUtil.initialLocalDB();
        handleService = new HandleService(true);
        assertNotNull(this.handleService);
    }

    @Test
    public void testGetListOfUserTask() {
        System.out.println("\nStarting testGetListUserTask");
        List<Handle> listOfUserTask = handleService.getListOfUserTask(1);
        assertEquals(4, listOfUserTask.size());
        System.out.println("Finish testGetListOfUserTask");
    }


    @Test
    public void testGetListOfTaskUser() {
        System.out.println("\nStarting testGetListOfTaskUser");
        List<Handle> listOfTaskUser = handleService.getListOfTaskUser(1); // "How many tasks that User 1 handle"
        assertEquals(3, listOfTaskUser.size());
        System.out.println("Finish testGetListOfTaskUser");
    }

    @Test
    public void testInsertHandle() {
        System.out.println("\nStarting testInsertHandle");
        List<Handle> listOfTaskUser1 = handleService.getListOfTaskUser(1);
        assertEquals(4, listOfTaskUser1.size());

        Handle handle = new Handle(5, 1, new Timestamp(System.currentTimeMillis()));
        handleService.insertHandle(handle);

        listOfTaskUser1 = handleService.getListOfTaskUser(1);
        assertEquals(5, listOfTaskUser1.size());

    }


    @After
    public void tearDown() {
        System.out.println("Reset database");
        testDB.delete();
        Service.clean();
        DBConnectorUtil.cleanLocalDB();

    }


}
