package seedu.address.model;

import static java.util.Objects.requireNonNull;

import java.util.LinkedList;
import java.util.List;

/**
 * Stores previous Content versions since application initialisation
 */
public class VersionedContents {
    private final List<Content> versionedContentList;

    /**
     * Constructor for the VersionedContactList object.
     * @param initialContent initial content state of the app
     */
    public VersionedContents(Content initialContent) {
        requireNonNull(initialContent);
        this.versionedContentList = new LinkedList<>();
        versionedContentList.add(initialContent);
    }

    /**
     * Adds a new Content version.
     * @param newContentVersion new content version to add
     */
    public void addContentVersion(Content newContentVersion) {
        requireNonNull(newContentVersion);
        versionedContentList.add(newContentVersion);
    }

    /**
     * Returns a copy of the content version list
     */
    public List<Content> getVersionedContentList() {
        return List.copyOf(this.versionedContentList);
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
        return versionedContentList.size() == 1;
    }

    /**
     * String representation of the versioned contact list.
     */
    @Override
    public String toString() {
        StringBuilder resString = new StringBuilder();
        for (Content contentVersion : versionedContentList) {
            resString.append(contentVersion.toString() + "\n");
        }
        return resString.toString();
    }

    /**
     * Pops the current content version from the content version list
     */
    private void popLastContentVersion() {
        assert versionedContentList.size() >= 1;

        if (versionedContentList.size() == 1) {
            return;
        }

        int lastIndex = versionedContentList.size() - 1;
        versionedContentList.remove(lastIndex);
    }

    /**
     * Returns the latest Content Version
     */
    private Content latestContent() {
        return versionedContentList.get(versionedContentList.size() - 1);
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof VersionedContents)) {
            return false;
        }

        VersionedContents otherVersionedContentList = (VersionedContents) other;
        return this.versionedContentList.equals(((VersionedContents) other).getVersionedContentList());
    }
}
