package seedu.address.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.junit.jupiter.api.Test;
import seedu.address.model.person.Person;
import seedu.address.testutil.PersonBuilder;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import static seedu.address.logic.commands.CommandTestUtil.VALID_MOD_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.address.testutil.TypicalPersons.ALICE;
import static seedu.address.testutil.TypicalPersons.getTypicalContactList;

import static org.junit.jupiter.api.Assertions.*;

class VersionedContentsTest {
    private final Content typicalContent = new Content(getTypicalContactList());

    private final VersionedContentList versionedContents = new VersionedContentList(typicalContent);

    /**
    @Test
    void addContentVersion() {
        Content newContent =
    }
    */

    @Test
    void undo() {
        Person editedAlice = new PersonBuilder(ALICE).withMod(VALID_MOD_BOB).withTags(VALID_TAG_HUSBAND)
                .build();
        List<Person> newPersons = Arrays.asList(ALICE, editedAlice);
        ContactListStub newData = new ContactListStub(newPersons);
        Content content = new Content(newData);

        versionedContents.addContentVersion(content);
        versionedContents.undo();
        Content undoneContent = versionedContents.latestContent();
        assertEquals(undoneContent, typicalContent);
    }

    /**
    @Test
    void isEarliestVersion() {
    }
    */

    /**
     * A stub Content
     */
    private static class ContentStub {
        private final ContactListStub contactList;

        ContentStub(ContactListStub contactList) {
            this.contactList = contactList;
        }

        public ContactListStub getContactList() {
            return contactList;
        }
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