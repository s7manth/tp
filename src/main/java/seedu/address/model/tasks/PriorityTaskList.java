package seedu.address.model.tasks;

import seedu.address.model.ReadOnlyContactList;
import seedu.address.model.tasks.exceptions.DuplicateTaskException;
import seedu.address.model.tasks.exceptions.TaskNotFoundException;

import java.util.Collection;
import java.util.Iterator;
import java.util.PriorityQueue;

import static java.util.Objects.requireNonNull;

public class PriorityTaskList implements ReadOnlyTaskList {

    private PriorityQueue<Task> pq = new PriorityQueue<>();

    public PriorityTaskList() {}

    public PriorityTaskList(PriorityQueue<Task> pq) {
        this.pq = new PriorityQueue<>(pq);
    }
    public PriorityTaskList(ReadOnlyTaskList other) {
        Collection<Task> otherList = other.getInternalList();
        if (otherList instanceof PriorityQueue<?>) {
            pq = (PriorityQueue<Task>) otherList;
        } else {
            throw new RuntimeException("Unsupported Task List");
        }
    }
    /**
     * Resets the existing data of this {@code PriorityTaskList} with {@code newData}.
     */
    public void resetData(PriorityTaskList newData) {
        requireNonNull(newData);
        pq = new PriorityQueue<>(newData.pq);
    }

    @Override
    public void add(Task task) {
        requireNonNull(task);
        if (this.contains(task)) {
            throw new DuplicateTaskException();
        }
        pq.add(task);
    }

    @Override
    public void remove(Task task) {
        requireNonNull(task);
        if (!pq.remove(task)) {
            throw new TaskNotFoundException();
        }
    }

    @Override
    public void remove(int index) {
        if (index > pq.size() || index < 0) return;
        Iterator<Task> iterator = pq.iterator();
        Task toRemove = iterator.next();
        for (int i = 0; i < index; i++) {
            toRemove = iterator.next();
        }
        pq.remove(toRemove);
    }

    @Override
    public boolean contains(Task task) {
        requireNonNull(task);
        return pq.stream().anyMatch(task::equals);
    }

    @Override
    public Task get(int index) {
        if (index > pq.size() || index < 0) return null;
        Iterator<Task> iterator = pq.iterator();
        Task toReturn = iterator.next();
        for (int i = 0; i < index; i++) {
            toReturn = iterator.next();
        }
        return toReturn;
    }

    @Override
    public ReadOnlyTaskList getCopy() {
        return new PriorityTaskList(this.pq);
    }

    @Override
    public Collection<Task> getInternalList() {
        return pq;
    }

    @Override
    public int size() {
        return pq.size();
    }
}
