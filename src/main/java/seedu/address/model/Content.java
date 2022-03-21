package seedu.address.model;

/**
 * Wrapper class for contents that are undoable in the Model
 */
public class Content {
    private final ReadOnlyContactList contactList;

    public Content(ReadOnlyContactList contactList) {
        this.contactList = contactList;
    }

    /**
     * Creates and returns a copy of the content object.
     * @param content content to be copied
     * @return a copy of the content object
     */
    public static Content copyContent(Content content) {
        return new Content(content.getContactList());
    }

    /**
     * Returns the Contact List.
     */
    public ReadOnlyContactList getContactList() {
        return this.contactList;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Content // instanceof handles nulls
                && contactList.equals(((Content) other).getContactList()));
    }
}
