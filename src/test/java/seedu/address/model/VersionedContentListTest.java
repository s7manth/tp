package seedu.address.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.TypicalPersons.ALICE;
import static seedu.address.testutil.TypicalPersons.getTypicalContactList;

import java.util.Collection;
import java.util.List;

import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.person.Person;

class VersionedContentListTest {
    private final Content typicalContent = new Content(getTypicalContactList());
    private final Content emptyContent = new Content(new ContactListStub(List.of()));

    @Test
    void constructor() {
        VersionedContents versionedContentList = new VersionedContents(emptyContent);

        assertEquals(versionedContentList.getVersionedContentList(), List.of(emptyContent));
    }

    @Test
    void addContentVersion() {
        VersionedContents versionedContentList = new VersionedContents(emptyContent);
        versionedContentList.addContentVersion(typicalContent);

        assertEquals(versionedContentList.getVersionedContentList(), List.of(emptyContent, typicalContent));
    }

    @Test
    void addContentVersion_null_throwsNullPointerException() {
        VersionedContents versionedContentList = new VersionedContents(emptyContent);
        assertThrows(NullPointerException.class, () -> versionedContentList.addContentVersion(null));
    }


    @Test
    void undo() {
        List<Person> newPersons = List.of(ALICE);
        ContactListStub newData = new ContactListStub(newPersons);
        Content content = new Content(newData);
        VersionedContents emptyVersionedContents = new VersionedContents(emptyContent);

        emptyVersionedContents.addContentVersion(content);
        Content undoneContent = emptyVersionedContents.undo();
        assertEquals(undoneContent, emptyContent);
    }


    @Test
    void isEarliestVersion() {
        VersionedContents emptyVersionedContents = new VersionedContents(emptyContent);
        assertTrue(emptyVersionedContents.isEarliestVersion());

        emptyVersionedContents.addContentVersion(typicalContent);
        assertFalse(emptyVersionedContents.isEarliestVersion());
    }

    @Test
    void equals() {
        VersionedContents emptyVersionedContents = new VersionedContents(emptyContent);
        VersionedContents anotherEmptyVersionedContents = new VersionedContents(emptyContent);
        VersionedContents typicalVersionedContents = new VersionedContents(typicalContent);

        assertEquals(emptyVersionedContents, anotherEmptyVersionedContents);
        assertNotEquals(emptyVersionedContents, typicalVersionedContents);
    }

    /**
     * A stub ReadOnlyContactList whose persons list can violate interface constraints.
     */
    private static class ContactListStub implements ReadOnlyContactList {
        private final ObservableList<Person> persons = FXCollections.observableArrayList();

        ContactListStub(Collection<Person> persons) {
            this.persons.setAll(persons);
        }

        @Override
        public ObservableList<Person> getPersonList() {
            return persons;
        }
    }
}
