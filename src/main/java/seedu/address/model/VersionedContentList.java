package seedu.address.model;

import java.util.LinkedList;
import java.util.List;

import static java.util.Objects.requireNonNull;

/**
 * Stores previous Content versions since application initialisation
 */
public class VersionedContentList {
    private final List<Content> contentVersions;

    /**
     * Constructor for the VersionedContactList object.
     * @param initialContent initial content state of the app
     */
    public VersionedContentList(Content initialContent) {
        this.contentVersions = new LinkedList<>();
        contentVersions.add(initialContent);
    }

    /**
     * Adds a new Content version.
     * @param newContentVersion new content version to add
     */
    public void addContentVersion(Content newContentVersion) {
        requireNonNull(newContentVersion);
        contentVersions.add(newContentVersion);
    }

    /**
     * Returns a copy of the content version list
     */
    public List<Content> getContentVersions() {
        return List.copyOf(this.contentVersions);
    }

    /**
     * Removes the current content version, and returns the content version just before this
     * @return most recent previous content state
     */
    public Content undo() {
        popLastContentVersion();
        return latestContent();
    }

    /**
     * Returns true if at the earliest Content version, false otherwise
     */
    public boolean isEarliestVersion() {
        return contentVersions.size() == 1;
    }

    /**
     * String representation of the versioned contact list.
     */
    @Override
    public String toString() {
        StringBuilder resString = new StringBuilder();
        for (Content contentVersion : contentVersions) {
            resString.append(contentVersion.toString() + "\n");
        }
        return resString.toString();
    }

    /**
     * Pops the current content version from the content version list
     */
    private void popLastContentVersion() {
        assert contentVersions.size() >= 1;

        if (contentVersions.size() == 1) {
            return;
        }

        int lastIndex = contentVersions.size() - 1;
        contentVersions.remove(lastIndex);
    }

    /**
     * Returns the latest Content Version
     */
    private Content latestContent() {
        return contentVersions.get(contentVersions.size() - 1);
    }
}
