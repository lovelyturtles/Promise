package comp3350.group6.promise.tests.business;

import org.junit.Before;
import org.junit.Test;

import comp3350.group6.promise.business.UserService;
import comp3350.group6.promise.objects.User;
import static org.junit.Assert.*;

public class UserServiceTest {

    private UserService userService;

    /* Each time the fake DB get reinitialized */
    @Before
    public void setup() {
        System.out.println("Starting test for TaskService");
        userService = new UserService(); // false init a fake DB
    }

    @Test
    public void testAddUser() throws Exception{
        int userId = userService.addUser("sunsiwen", "introduction");
        assertTrue(userId > 0);
    }

    @Test
    public void testUpdateUserByUserId( ) throws Exception{
        userService.updateUserByUserId(1, "sunsiwen", "introduction");
    }

    @Test
    public void testGetUserByUserId( ) throws Exception{
        User user = userService.getUserByUserId(1);
    }
}
