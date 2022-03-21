package seedu.address.model;

import java.util.LinkedList;
import java.util.List;

import static java.util.Objects.requireNonNull;

/**
 * Stores previous Content versions since application initialisation
 */
public class VersionedContents {
    private final List<Content> contentVersions;

    /**
     * Constructor for the VersionedContactList object.
     */
    public VersionedContents(Content content) {
        this.contentVersions = new LinkedList<>();

        // adds initial content version during start up
        addContentVersion(content);
    }

    /**
     * Adds a new Content version.
     * @param newVersionedContent new ContentVersion to add
     */
    public void addContentVersion(Content newVersionedContent) {
        requireNonNull(newVersionedContent);
        contentVersions.add(newVersionedContent);
    }

    /**
     * Removes and returns the previous Content version
     * @return previous Content version
     */
    public Content undo() {
        return popLastContentVersion();
    }

    /**
     * Pops the previous Content version
     * @return previous Content version
     */
    private Content popLastContentVersion() {
        int lastIndex = contentVersions.size() - 1;
        if (lastIndex == 0) {
            return contentVersions.get(0);
        }

        return contentVersions.remove(lastIndex);
    }

    /**
     * Returns true if at the earliest Content version, false otherwise
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
