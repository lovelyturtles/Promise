//package comp3350.group6.promise.persistence.hsqldb;
//
//import comp3350.group6.promise.objects.FakeDB;
//import comp3350.group6.promise.objects.User;
//import comp3350.group6.promise.persistence.UserDao;
//
//public class UserImpNoDB implements UserDao {
//
//    @Override
//    public int addUser( String name, String introduction ){
//
//        //add this user to the fake User database
//        FakeDB.users.add( new User( FakeDB.generatedUserIDCount, name, introduction ) );
//
//        //return the generatedID to caller and increment
//        return FakeDB.generatedUserIDCount++;
//
//    }
//
//    @Override
//    public void updateUserByUserId( int userId, String name, String introduction ){
//
//        if( getUserByUserId( userId ) != null ) {
//            getUserByUserId( userId ).setName( name );
//            getUserByUserId( userId ).setIntro( introduction );
//        }
//
//    }
//
//    @Override
//    public User getUserByUserId( int userId ){
//
//        User getUser = null;
//        boolean exists = false;
//
//        for( int i = 0; i < FakeDB.users.size() && !exists; i++ ){
//
//            if( FakeDB.users.get( i ).getUserID() == userId ){
//
//                getUser = FakeDB.users.get( i );
//                exists = true;
//
//            }
//
//        }
//
//        return getUser;
//
//    }
//
//}
