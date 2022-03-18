package comp3350.group6.promise.business;


import comp3350.group6.promise.objects.CurrentSession;
import comp3350.group6.promise.objects.User;
import comp3350.group6.promise.persistence.AccountDao;
import comp3350.group6.promise.persistence.hsqldb.AccountImp;
//import comp3350.group6.promise.persistence.stub.AccountImpNoDB;

public class AccountService {

    private static final UserService userService = UserService.getInstance();
    private static final AccountDao accountDao = new AccountImp();
    private static AccountService instance;

    private AccountService() {}

    public int createAccount( String email, String password, String name, String introduction ) throws Exception {

        int userID = userService.addUser( name, introduction );
        userID = accountDao.createAccount( email, password, userID );
        //if the userID is -1, that means the account already exists, otherwise it's created
        return userID;
    }

    public boolean changePassword( int userID, String oldPassword, String newPassword ) throws Exception{

        return 1 == accountDao.changePassword( userID, oldPassword, newPassword );

    }

    public boolean accountExists( String email ){

        CurrentSession.emailCheck = accountDao.accountExists( email );
        return CurrentSession.emailCheck;

    }

    public boolean passwordsMatch( String email, String password ){

        CurrentSession.passwordCheck = accountDao.passwordsMatch( email, password );
        return CurrentSession.passwordCheck;

    }

    public boolean setCurrentAccount( String email, String password ){

        boolean success = false;

//        if( CurrentSession.emailCheck && CurrentSession.passwordCheck ){

            CurrentSession.currentUser = accountDao.getAccountByEmail( email );

            if( CurrentSession.currentUser != null )
                success = true;

//        }

        return success;

    }

    public static AccountService getInstance() {
        if(AccountService.instance == null) {
            AccountService.instance = new AccountService();
        }
        return AccountService.instance;
    }

/*  Database Implementation

    private static final UserService userService = new UserService();
    private static final AccountDao accountDao = new AccountImp();

    public int createAccount(String email, String password, String name, String introduction) throws Exception {
        int userId = userService.addUser(name, introduction);
        if (userId < 0) {
            throw new Exception("Adding user fails");
        }
       try {
           userId = accountDao.createAccount(email,password,userId);
       }catch (Exception e){
           throw new Exception("Your email has been registered");
       }
       return userId;
    }

    public boolean changePassword(int userId, String oldPassword, String newPassword) throws Exception {

        return  1 == accountDao.changePassword(userId,oldPassword,newPassword);

    }

 */

}
