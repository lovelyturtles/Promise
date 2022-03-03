package comp3350.group6.promise.tests.business;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import java.util.List;

import comp3350.group6.promise.business.ProjectService;
import comp3350.group6.promise.objects.FakeDB;
import comp3350.group6.promise.objects.Project;
import comp3350.group6.promise.persistence.hsqldb.ProjectImpNoDB;


/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ProjectServiceTest {

    private FakeDB db;
    private ProjectService projectService;

    @Before
    public void setUp() throws Exception{
        db = new FakeDB();
        projectService = new ProjectService(new ProjectImpNoDB());
        db.initialize();
    }


    @Test
    public void testInsertProject() {

        System.out.println("Testing insertProject method from ProjectService Class");

        List<Project> projects = projectService.getProjects();
        Project p = new Project("Test Project", "This is a test.");
        int id = p.getProjectID();
        int originalSize = projects.size();

        projectService.insertProject(p);

        assertTrue("Inserted object should be equal to project in List.", projectService.getProjectByID(id).equals(p));
        assertNotEquals("List size should be different after insertion.", originalSize, projectService.getProjects().size());

        System.out.println("Passed insertProject method from ProjectService Class.");
    }

    @Test
    public void testUpdateProject() {

        System.out.println("Testing updateProject method from ProjectService Class");

        List<Project> projects = projectService.getProjects();
        Project target;
        int originalSize = projects.size();

        if (!projects.isEmpty()){
            int index = 0;
            target = projects.get(index);
            target.setStatement("A changed Statement.");
            projectService.updateProject(target);
            assertEquals("Updated object should be equal to project in List.", target.getStatement(), projectService.getProjects().get(index).getStatement());
            assertEquals("List size should not be changed after update.", originalSize, projectService.getProjects().size());
        }


        System.out.println("Passed updateProject method from ProjectService Class.");
    }

    @Test
    public void testDeleteProject() {

        System.out.println("Testing deleteProject method from ProjectService Class");

        List<Project> projects = projectService.getProjects();
        Project target;
        int originalSize = projects.size();

        if (!projects.isEmpty()){
            int index = 0;
            target = projects.get(index);
            projectService.deleteProject(target);
            assertNotEquals("Deleted object should be not equal to project in List.", target, projectService.getProjects().get(index));
            assertNotEquals("List size should be different after deletion.", originalSize, projectService.getProjects().size());
        }


        System.out.println("Passed deleteProject method from ProjectService Class.");
    }

}