package seedu.address.model;

/**
 * Wrapper class for Undoable contents in the Model
 */
public class Content {
    private ReadOnlyContactList contactList;

    public Content(ReadOnlyContactList contactList) {
        this.contactList = contactList;
    }

    public static Content copyContent(Content content) {
        return new Content(content.getContactList());
    }

    public ReadOnlyContactList getContactList() {
        return this.contactList;
    }
}
