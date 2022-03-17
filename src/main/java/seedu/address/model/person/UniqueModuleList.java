package seedu.address.model.person;

import seedu.address.model.person.exceptions.DuplicatePersonException;
import seedu.address.model.person.exceptions.ModuleNotFoundException;

import static java.util.Objects.requireNonNull;

import java.util.ArrayList;
import java.util.Iterator;

public class UniqueModuleList implements Iterable<Mod> {


    private static final ArrayList<Mod> internalList = new ArrayList<>();

    /**
     * Returns true if the list contains a module with the same code as the given argument.
     */
    public boolean contains(String moduleCode) {
        requireNonNull(moduleCode);
        return internalList.stream().anyMatch(x -> x.value.equalsIgnoreCase(moduleCode));
    }

    /**
     * Adds a person to the list.
     * The person must not already exist in the list.
     */
    public void add(String modCode) {
        requireNonNull(modCode);
        if (contains(modCode)) {
            throw new DuplicatePersonException();
        }
        internalList.add(new Mod(modCode));
    }

    /**
     * Removes the equivalent person from the list.
     * The person must exist in the list.
     */
    public void remove(Mod toRemove) {
        requireNonNull(toRemove);
        if (!internalList.remove(toRemove)) {
            throw new ModuleNotFoundException();
        }
    }

    @Override
    public Iterator<Mod> iterator() {
        return internalList.iterator();
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        return (other instanceof UniqueModuleList); // instanceof handles nulls
    }

    @Override
    public int hashCode() {
        return internalList.hashCode();
    }

}
