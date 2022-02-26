package comp3350.group6.promise;

import org.junit.Test;

import java.lang.reflect.Array;
import java.util.ArrayList;

import comp3350.group6.promise.business.ProjectService;
import comp3350.group6.promise.business.UserService;
import comp3350.group6.promise.objects.Project;
import comp3350.group6.promise.presentation.UserController;
import comp3350.group6.promise.util.DBConnectorUtil;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    private ProjectService pj = new ProjectService();

    @Test
    public void addition_isCorrect() throws Exception {
        Project sd = new Project("das", "sad");

        pj.insertProject(sd);
        for (Project project : pj.getProjects()) {
            System.out.println(project);
        }
    }


}