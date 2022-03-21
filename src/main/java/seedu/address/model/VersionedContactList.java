package seedu.address.model;

import java.util.LinkedList;
import java.util.List;

import static java.util.Objects.requireNonNull;

/**
 * Stores previous Contact List versions since application initialisation
 */
public class VersionedContactList {
    private final List<ReadOnlyContactList> contactListVersions;


    public VersionedContactList() {
        this.contactListVersions = new LinkedList<ReadOnlyContactList>();
    }

    public void addContactListVersion(ReadOnlyContactList contactList) {
        requireNonNull(contactList);
        contactListVersions.add(contactList);
    }

    public ReadOnlyContactList undo() {
        return popLastContactListVersion();
    }

    private ReadOnlyContactList popLastContactListVersion() {
        int lastIndex = contactListVersions.size() - 1;
        if (lastIndex == 0) {
            return contactListVersions.get(0);
        }

        return contactListVersions.remove(lastIndex);
    }

}
