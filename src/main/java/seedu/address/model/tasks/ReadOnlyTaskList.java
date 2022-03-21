package seedu.address.model.tasks;

import java.util.Collection;

public interface ReadOnlyTaskList {
    void add(Task task);
    void remove(Task task);
    void remove(int index);
    boolean contains(Task task);
    Task get(int index);
    ReadOnlyTaskList getCopy();
    Collection<Task> getInternalList();
    int size();
}
