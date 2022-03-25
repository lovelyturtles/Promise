package comp3350.group6.promise.tests.business;

import org.junit.After;
import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.*;

import comp3350.group6.promise.business.AccessService;
import comp3350.group6.promise.util.DBConnectorUtil;

public class AccessServiceTest {

    //TODO: Implement tests

    private AccessService accessService;

    @Before
    public void setup(){
        DBConnectorUtil.initialLocalDB();
        System.out.println("Starting test for AccessService");
        accessService = new AccessService();
    }

    @After
    public void clean(){
        DBConnectorUtil.cleanLocalDB();
    }

    @Test
    public void testGetProjectAccess(){

    }

    @Test
    public void testGetUsers(){

    }

    @Test
    public void testGetUserAccess(){

    }

    @Test
    public void testGetProjects(){

    }

    @Test
    public void testInsertAccess(){

    }

    @Test
    public void testUpdateAccess(){

    }
}
