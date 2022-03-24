package seedu.address.model;

import static java.util.Objects.requireNonNull;

import seedu.address.commons.util.StringBuilderUtil;
import seedu.address.model.tasks.PriorityTaskList;
import seedu.address.model.tasks.ReadOnlyTaskList;

/**
 * Wrapper class for contents that are undoable in the Model
 */
public class Content {
    private final ContactList contactList;
    private final PriorityTaskList taskList;

    /**
     * Constructor for the Content class.
     * @param contactList contact list version of the content
     */
    public Content(ReadOnlyContactList contactList, ReadOnlyTaskList taskList) {
        requireNonNull(contactList);
        requireNonNull(taskList);
        this.contactList = new ContactList(contactList);
        this.taskList = new PriorityTaskList(taskList);
    }

    /**
     * Constructor for Content class that takes in another content.
     * Used to make copies the content objects
     * @param otherContent other content to create a copy of
     */
    public static Content getContentCopy(Content otherContent) {
        return new Content(otherContent.getContactList(), otherContent.getTaskList());
    }

    /**
     * Returns the Contact List.
     */
    public ReadOnlyContactList getContactList() {
        return new ContactList(this.contactList);
    }

    /**
     * Returns the taskList
     */
    public ReadOnlyTaskList getTaskList() {
        return new PriorityTaskList(this.taskList);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) { // short circuit if same object
            return true;
        } else if (other instanceof Content) { // instance of handles nulls
            Content otherContent = (Content) other;

            // checks both contactList and taskLists are the same
            return contactList.equals(otherContent.getContactList())
                    && taskList.equals(otherContent.getTaskList());
        }

        return false;
    }

    @Override
    public String toString() {
        final StringBuilderUtil stringBuilderUtil = StringBuilderUtil.getInstance();
        stringBuilderUtil.appendAll("Contact List: ", contactList.toString(),
                "\nTask List: ", taskList.toString());

        return stringBuilderUtil.getFormattedOutput();
    }
}
