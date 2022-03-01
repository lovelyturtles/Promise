package comp3350.group6.promise.persistence.hsqldb;

import java.util.List;

import comp3350.group6.promise.objects.FakeDB;
import comp3350.group6.promise.objects.Project;
import comp3350.group6.promise.persistence.ProjectDao;

public class ProjectImpNoDB implements ProjectDao {

    @Override
    public List<Project> getProjectList() {
        return FakeDB.projects;
    }

    @Override
    public Project getProjectByID(int id){ return FakeDB.projects.get(getIndexByID(id)); }

    @Override
    public Project insertProject(Project project){

        FakeDB.projects.add(project);

        return project;
    }

    @Override
    public Project updateProject(Project project){
        int index = getIndexByID(project.getProjectID());
        FakeDB.projects.set(index, project);
        return project;
    }

    @Override
    public void deleteProject(Project project){
        int index = getIndexByID(project.getProjectID());
        FakeDB.projects.remove(index);
    }

    private int getIndexByID(int id){
        int index = -1;
        boolean found = false;

        for(int i = 0; i < FakeDB.projects.size() && !found; i++){
            if(FakeDB.projects.get(i).getProjectID() == id){
                found = true;
                index = i;
            }
        }

        return index;
    }
}
