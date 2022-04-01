package comp3350.group6.promise.tests.business;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.mock;

import org.junit.Before;
import org.junit.Test;

import comp3350.group6.promise.business.UserService;
import comp3350.group6.promise.objects.User;
import comp3350.group6.promise.persistence.UserDao;

public class UserServiceTest {

    private UserService userService;
    private UserDao userDao;

    @Before
    public void setup(){
        userDao = mock(UserDao.class);
        userService = new UserService(userDao);
    }


    @Test
    public void testAddUser() throws Exception{
        int userId = userService.addUser("sunsiwen", "introduction");
        assertFalse(userId > 0);
    }

    @Test
    public void testUpdateUserByUserId( ) throws Exception{
        int i = userService.updateUserByUserId(1, "SUNSIWEN", "introduction");
        assertFalse(i > 0);
    }

    @Test
    public void testGetUserByUserId( ) throws Exception{
        User user = userService.getUserByUserId(1);
        assertNull(user);
    }
}
