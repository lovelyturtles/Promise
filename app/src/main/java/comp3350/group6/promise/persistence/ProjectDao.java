package comp3350.group6.promise.persistence;

import java.util.List;

import comp3350.group6.promise.objects.Project;

public interface ProjectDao {

    List<Project> getProjectList();

    Project getProjectByID(int projectID);

    Project insertProject(Project project);

    Project updateProject(Project project);

    void deleteProject(Project project);
}
