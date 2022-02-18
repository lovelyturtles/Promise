package comp3350.group6.promise;

import org.junit.Test;
import comp3350.group6.promise.business.UserService;
import comp3350.group6.promise.presentation.UserController;
import comp3350.group6.promise.util.DBConnectorUtil;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    private UserService userService;

    @Test
    public void addition_isCorrect() {
        UserService.test();
    }

}