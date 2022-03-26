package comp3350.group6.promise.persistence;

import java.util.List;

import comp3350.group6.promise.objects.Handle;

public interface HandleDao {
    List<Handle> getUserTask(int taskId);
    List<Handle> getTaskUser(int userId);
    void insertHandle(Handle handle);
}
