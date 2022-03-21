package seedu.address.model;

import java.util.LinkedList;
import java.util.List;

import static java.util.Objects.requireNonNull;

/**
 * Stores previous Content version since application initialisation
 */
public class VersionedContents {
    private final List<Content> contentVersions;

    /**
     * Constructor for the VersionedContactList object.
     */
    public VersionedContents() {
        this.contentVersions = new LinkedList<>();
    }

    /**
     * Adds a new Content version.
     * @param versionedContent new ContentVersion to add
     */
    public void addContentVersion(Content versionedContent) {
        requireNonNull(versionedContent);
        contentVersions.add(versionedContent);
    }

    /**
     * Removes and returns the previous ContactList version
     * @return previous ContactList version
     */
    public Content undo() {
        return popLastContentVersion();
    }

    /**
     * Pops the previous ContactList version
     * @return previous ContactList version
     */
    private Content popLastContentVersion() {
        int lastIndex = contentVersions.size() - 1;
        if (lastIndex == 0) {
            return getContentCopy(contentVersions.get(0));
        }

        return getContentCopy(contentVersions.remove(lastIndex));
    }

    /**
     * Returns true if at the earliest ContactList version, false otherwise
     */
    public boolean isEarliestVersion() {
        return contentVersions.size() == 1;
    }

    /**
     * Returns a copy of the Content object
     */
    private Content getContentCopy(Content content) {
        return Content.copyContent(content);
    }
}
