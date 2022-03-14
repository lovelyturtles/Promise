package comp3350.group6.promise.persistence;

import java.sql.Timestamp;
import java.util.List;

import comp3350.group6.promise.objects.Access;
import comp3350.group6.promise.objects.Project;
import comp3350.group6.promise.objects.User;

public interface AccessDao {

    List<Access> getAccessByProject(int projectId);

    List<Access> getAccessByUser(int userId);

}
