package comp3350.group6.promise.persistence.stub;

import java.util.List;

import comp3350.group6.promise.objects.Handle;
import comp3350.group6.promise.persistence.HandleDao;

public class HandleImpNoDB implements HandleDao {
    @Override
    public List<Handle> getUserTask(int taskId) {
        return null;
    } //TODO implement last

    @Override
    public List<Handle> getTaskUser(int userId) {
        return null;
    }

    @Override
    public void insertHandle(Handle handle) {

    }
}
