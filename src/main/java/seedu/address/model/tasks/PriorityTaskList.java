package seedu.address.model.tasks;

import static java.util.Objects.requireNonNull;

import java.util.Collection;
import java.util.Iterator;
import java.util.Objects;
import java.util.PriorityQueue;

import seedu.address.model.tasks.exceptions.DuplicateTaskException;
import seedu.address.model.tasks.exceptions.TaskNotFoundException;

/**
 * Wraps all data at the task list level
 * Duplicates are not allowed (by equals comparison)
 */
public class PriorityTaskList implements ReadOnlyTaskList {

    /** The internal representation of the task list is a PQ.*/
    private PriorityQueue<Task> pq = new PriorityQueue<>();

    /**
     * Constructs a PriorityTaskList. This should be used as the default.
     */
    public PriorityTaskList() {}

    /**
     * Constructs a PriorityTaskList with a given PriorityQueue to copy from.
     * @param pq the PQ to copy from.
     */
    public PriorityTaskList(PriorityQueue<Task> pq) {
        this.pq = new PriorityQueue<>(pq);
    }

    /**
     * Constructs a PriorityTaskList with a given ReadOnlyTaskList to copy from.
     * Note that Since only PriorityTaskList implements ReadOnlyTaskList, PriorityTaskList
     * is the only class that fits the requirements, and hence only one instanceof check is done.
     *
     * @param other the ReadOnlyTaskList to copy from.
     */
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
    public void resetData(ReadOnlyTaskList newData) {
        requireNonNull(newData);
        pq = new PriorityQueue<>();
        pq.addAll(newData.getInternalList());
    }

    @Override
    public boolean add(Task task) {
        requireNonNull(task);
        if (this.contains(task)) {
            throw new DuplicateTaskException();
        }
        pq.add(task);
        return true;
    }

    @Override
    public boolean remove(Task task) {
        requireNonNull(task);
        if (!pq.remove(task)) {
            throw new TaskNotFoundException();
        }
        return true;
    }

    @Override
    public boolean remove(int index) {
        if (index > pq.size() || index < 0) {
            return false;
        }
        Iterator<Task> iterator = pq.iterator();
        Task toRemove = iterator.next();
        for (int i = 0; i < index; i++) {
            toRemove = iterator.next();
        }
        pq.remove(toRemove);
        return true;
    }

    @Override
    public boolean contains(Task task) {
        requireNonNull(task);
        return pq.stream().anyMatch(task::equals);
    }

    @Override
    public Task get(int index) {
        if (index > pq.size() || index < 0) {
            return null;
        }
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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        PriorityTaskList that = (PriorityTaskList) o;
        return pq.stream().allMatch(that::contains) && that.pq.stream().allMatch(this::contains);
    }

    @Override
    public int hashCode() {
        return Objects.hash(pq);
    }
}
