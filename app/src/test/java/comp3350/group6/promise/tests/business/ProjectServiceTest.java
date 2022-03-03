package comp3350.group6.promise.tests.business;

import org.junit.Before;
import org.junit.Test;

import java.util.List;

import comp3350.group6.promise.business.ProjectService;
import comp3350.group6.promise.objects.FakeDB;
import comp3350.group6.promise.objects.Project;

public class ProjectServiceTest {
    private ProjectService projectService;

    /* Each time the fake DB get reinitialized */
    @Before
    public void setup() throws Exception {
        System.out.println("Starting test for ProjectServiceTest");
        FakeDB.initialize();
        projectService = new ProjectService(); // false init a fake DB
    }
    @Test
    public void testGetProjectByID(){
//        Project projectByID = projectService.getProjectByID(1);
    }

    @Test
    public void testGetProjects(){
        List<Project> projects = projectService.getProjects();
    }

    @Test
    public void testInsertProject(){
        projectService.insertProject(new Project("projectTest","test"));
    }

    @Test
    public void testUpdateProject(){
        Project project = new Project("projectTest", "test");
        project.setProjectID(1);
//        projectService.updateProject(project);
    }

    @Test
    public void testDeleteProject(){
        Project project = new Project("projectTest", "test");
        project.setProjectID(2);
//        projectService.deleteProject(project);
    }
}
