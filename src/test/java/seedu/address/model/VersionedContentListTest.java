package seedu.address.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.junit.jupiter.api.Test;
import seedu.address.model.person.Person;

import java.util.Collection;
import java.util.List;

import static seedu.address.testutil.TypicalPersons.ALICE;
import static seedu.address.testutil.TypicalPersons.getTypicalContactList;

import static org.junit.jupiter.api.Assertions.*;

class VersionedContentListTest {
    private final Content typicalContent = new Content(getTypicalContactList());
    private final Content emptyContent = new Content(new ContactListStub(List.of()));

    @Test
    void constructor() {
        VersionedContentList versionedContentList = new VersionedContentList(emptyContent);

        assertEquals(versionedContentList.getContentVersions(), List.of(emptyContent));
    }

    @Test
    void addContentVersion() {
        VersionedContentList versionedContentList = new VersionedContentList(emptyContent);
        versionedContentList.addContentVersion(typicalContent);

        assertEquals(versionedContentList.getContentVersions(), List.of(emptyContent, typicalContent));
    }

    @Test
    void addContentVersion_null_throwsNullPointerException() {
        VersionedContentList versionedContentList = new VersionedContentList(emptyContent);
        assertThrows(NullPointerException.class, () -> versionedContentList.addContentVersion(null));
    }


    @Test
    void undo() {
        List<Person> newPersons = List.of(ALICE);
        ContactListStub newData = new ContactListStub(newPersons);
        Content content = new Content(newData);
        VersionedContentList emptyVersionedContents = new VersionedContentList(emptyContent);

        emptyVersionedContents.addContentVersion(content);
        Content undoneContent = emptyVersionedContents.undo();
        assertEquals(undoneContent, emptyContent);
    }


    @Test
    void isEarliestVersion() {
        VersionedContentList emptyVersionedContents = new VersionedContentList(emptyContent);
        assertTrue(emptyVersionedContents.isEarliestVersion());

        emptyVersionedContents.addContentVersion(typicalContent);
        assertFalse(emptyVersionedContents.isEarliestVersion());
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