package seedu.address.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalPersons.ALICE;
import static seedu.address.testutil.TypicalPersons.getTypicalContactList;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.PersonBuilder;

class ContentTest {
    private final Content typicalContent = new Content(getTypicalContactList());

    @Test
    void constructor() {
        assertEquals(typicalContent.getContactList(), getTypicalContactList());
    }

    @Test
    void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Content(null));
    }

    @Test
    void copyContent() {
        assertEquals(typicalContent, Content.copyContent(typicalContent));
    }

    @Test
    void getContactList() {
        assertEquals(typicalContent.getContactList(), getTypicalContactList());
    }

    @Test
    void testEquals() {
        assertEquals(typicalContent, new Content(getTypicalContactList()));
    }

    @Test
    void testNotEquals() {
        ContactList editedContactList = new ContactList(getTypicalContactList());
        editedContactList.addPerson(new PersonBuilder(ALICE).withName("TEST").build());
        Content editedContent = new Content(editedContactList);
        assertNotEquals(typicalContent, editedContent);
    }

}
