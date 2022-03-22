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
     */
    public VersionedContentList(Content initialContent) {
        this.contentVersions = new LinkedList<>();
        contentVersions.add(initialContent);
    }

    /**
     * Adds a new Content version.
     * @param newContentVersion new ContentVersion to add
     */
    public void addContentVersion(Content newContentVersion) {
        requireNonNull(newContentVersion);
        contentVersions.add(newContentVersion);
    }

    /**
     * Removes and returns the previous Content version
     * @return previous Content version
     */
    public void undo() {
        popLastContentVersion();
    }

    /**
     * Pops the previous Content version unless at the earliest version
     * @return previous Content version
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
    public Content latestContent() {
        Content latestContent = contentVersions.get(contentVersions.size() - 1);
        assert latestContent != null;
        return latestContent;
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

    /**
     * String representation of the versioned contact list.
     */
    @Override
    public String toString() {
        String resString = "";
        for (int i = 0; i < contentVersions.size(); i++) {
            resString += contentVersions.get(i).toString() + "\n";
        }
        return resString;
    }
}
