package comp3350.group6.promise.business;

import java.util.Collections;
import java.util.List;

import comp3350.group6.promise.objects.Project;
import comp3350.group6.promise.persistence.ProjectDao;
import comp3350.group6.promise.persistence.hsqldb.ProjectImp;

public class ProjectService {

    private ProjectDao projectDao;
    private List<Project> projects;

    public ProjectService(){
        projectDao = new ProjectImp();
        projects = null;
    }

    public ProjectService(ProjectDao projectDao){
        this();
        this.projectDao = projectDao;
    }

    //returns a read-only list of Projects
    public List<Project> getProjects(){
        projects = projectDao.getProjectList();
        return Collections.unmodifiableList(projects);
    }

    public Project insertProject(Project project){
		return projectDao.insertProject(project);
    }

    public Project updateProject(Project project){
		return projectDao.updateProject(project);
    }

    public void deleteProject(Project project){
		projectDao.deleteProject(project);
    }
}