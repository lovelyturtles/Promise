package comp3350.group6.promise.business;


import comp3350.group6.promise.objects.User;
import comp3350.group6.promise.persistence.UserDao;
import comp3350.group6.promise.persistence.hsqldb.UserImp;



public class UserService {

    private static final UserDao userDao = new UserImp();

    public int addUser(String name, String introduction) throws Exception{
        assert ( name!= null );
        return userDao.addUser(name, introduction);
    }

    public void updateUserByUserId(int userId, String name, String introduction) throws Exception{
        userDao.updateUserByUserId(userId, name, introduction);
    }

    public User getUserByUserId(int userId) throws Exception{
        return userDao.getUserByUserId(userId);
    }

}
