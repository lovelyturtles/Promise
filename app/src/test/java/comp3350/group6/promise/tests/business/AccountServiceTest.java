package comp3350.group6.promise.tests.business;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import java.util.Collections;
import java.util.List;

import comp3350.group6.promise.business.AccountService;
import comp3350.group6.promise.business.ProjectService;
import comp3350.group6.promise.objects.CurrentSession;
import comp3350.group6.promise.objects.FakeDB;
import comp3350.group6.promise.objects.Project;
import comp3350.group6.promise.persistence.ProjectDao;

public class AccountServiceTest {
    private AccountService accountService;

    /* Each time the fake DB get reinitialized */
    @Before
    public void setup() throws Exception {
        System.out.println("Starting test for AccountServiceTest");
        FakeDB.initialize();
        accountService = AccountService.getInstance(); // false init a fake DB
    }

    @Test
    public void testCreateAccount() throws Exception {
        int account = accountService.createAccount("siwensun@gmail.com", "123", "ssw", "do some things");
        assert account > 0;
    }

    @Test
    public void testChangePassword() throws Exception {
//        assertTrue(accountService.changePassword(1, "password1", "newpass"));
    }

    @Test
    public void testAccountExists() {
        assertTrue(accountService.accountExists("wharfhorse@app.com"));
    }

    @Test
    public void testPasswordsMatch() {
        assertTrue(accountService.passwordsMatch("wendall@app.com", "password4"));
    }

    @Test
    public void testSetCurrentAccount() {
//        accountService.setCurrentAccount()
    }
}
