package comp3350.group6.promise.business;


import comp3350.group6.promise.objects.User;
import comp3350.group6.promise.persistence.UserDao;
import comp3350.group6.promise.persistence.hsqldb.UserImp;
//import comp3350.group6.promise.persistence.hsqldb.UserImpNoDB;

public class UserService {

    private static final UserDao userDao = new UserImp();
    private static UserService instance;


    public int addUser( String name, String introduction ) {
        assert ( name!= null );
        return userDao.addUser(name, introduction);
    }

    public void updateUserByUserId( int userId, String name, String introduction ) {
        userDao.updateUserByUserId(userId, name, introduction);
    }

    public User getUserByUserId( int userId ) {
        return userDao.getUserByUserId(userId);
    }

    public static UserService getInstance() {
        if(UserService.instance == null) {
            UserService.instance = new UserService();
        }
        return UserService.instance;
    }

}

