package seedu.address.model;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.nio.file.Path;
import java.util.function.Predicate;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.person.Mod;
import seedu.address.model.person.Person;
import seedu.address.model.person.UniqueModuleList;


/**
 * Represents the in-memory model of the contact list data.
 */
public class ModelManager implements Model {
    private static final Logger logger = LogsCenter.getLogger(ModelManager.class);

    private final ContactList contactList;
    private final UserPrefs userPrefs;
    private final FilteredList<Person> filteredPersons;

    /** List of versioned contents */
    private final VersionedContentList versionedContents;

    /**
     * Initializes a ModelManager with the given contactList and userPrefs.
     */
    public ModelManager(ReadOnlyContactList contactList, ReadOnlyUserPrefs userPrefs) {
        requireAllNonNull(contactList, userPrefs);

        logger.fine("Initializing with contact list: " + contactList + " and user prefs " + userPrefs);

        this.contactList = new ContactList(contactList);
        this.userPrefs = new UserPrefs(userPrefs);
        filteredPersons = new FilteredList<>(this.contactList.getPersonList());
        this.versionedContents = new VersionedContentList(new Content(getContactList()));
    }

    public ModelManager() {
        this(new ContactList(), new UserPrefs());
    }

    //=========== UserPrefs ==================================================================================

    @Override
    public void setUserPrefs(ReadOnlyUserPrefs userPrefs) {
        requireNonNull(userPrefs);
        this.userPrefs.resetData(userPrefs);
    }

    @Override
    public ReadOnlyUserPrefs getUserPrefs() {
        return userPrefs;
    }

    @Override
    public GuiSettings getGuiSettings() {
        return userPrefs.getGuiSettings();
    }

    @Override
    public void setGuiSettings(GuiSettings guiSettings) {
        requireNonNull(guiSettings);
        userPrefs.setGuiSettings(guiSettings);
    }

    @Override
    public Path getContactListFilePath() {
        return userPrefs.getContactListFilePath();
    }

    @Override
    public void setContactListFilePath(Path contactListFilePath) {
        requireNonNull(contactListFilePath);
        userPrefs.setContactListFilePath(contactListFilePath);
    }

    //=========== ContactList ================================================================================

    @Override
    public void setContactList(ReadOnlyContactList newContactList) {
        this.contactList.resetData(newContactList);

        // adding this command to each method that affects content as temporary solution
        // updateVersionedContent();
    }

    @Override
    public ReadOnlyContactList getContactList() {
        return contactList;
    }

    @Override
    public boolean hasPerson(Person person) {
        requireNonNull(person);
        return contactList.hasPerson(person);
    }

    @Override
    public void deletePerson(Person target) {
        contactList.removePerson(target);

        // adding this command to each method that affects content as temporary solution
        updateVersionedContent();
    }

    @Override
    public void addPerson(Person person) {
        contactList.addPerson(person);
        updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);

        // adding this command to each method that affects content as temporary solution
        updateVersionedContent();
    }

    @Override
    public void setPerson(Person target, Person editedPerson) {
        requireAllNonNull(target, editedPerson);

        contactList.setPerson(target, editedPerson);

        // adding this command to each method that affects content as temporary solution
        updateVersionedContent();
    }

    //=========== VersionedContent ================================================================================

    /**
     * Changes the content state of the app to the version just before the current
     */
    public void undoContents() {
        // Removes the current content version from version list and gets the previous content version
        Content newContent = versionedContents.undo();
        ContactList newContactList = new ContactList(newContent.getContactList());

        this.contactList.resetData(newContactList);
    }

    @Override
    public boolean isEarliestContentVersion() {
        return versionedContents.isEarliestVersion();
    }

    private void updateVersionedContent() {
        Content newContent = new Content(getContactList());
        versionedContents.addContentVersion(newContent);
    }

    public VersionedContentList getVersionedContents() {
        return this.versionedContents;
    }

    //=========== Filtered Person List Accessors =============================================================

    /**
     * Returns an unmodifiable view of the list of {@code Person} backed by the internal list of
     * {@code versionedContactList}
     */
    @Override
    public ObservableList<Person> getFilteredPersonList() {
        return filteredPersons;
    }

    @Override
    public void updateFilteredPersonList(Predicate<Person> predicate) {
        requireNonNull(predicate);
        filteredPersons.setPredicate(predicate);
    }

    @Override
    public boolean isDefaultPresent(Mod mod) {
        requireNonNull(mod);
        return mod.getDefaultGroup() != null;
    }

    @Override
    public boolean doesModExist(Mod mod) {
        requireNonNull(mod);
        return new UniqueModuleList().contains(mod.value);
    }

    @Override
    public String retrievePrevDefault(Mod mod) {
        return mod.getDefaultGroup();
    }

    @Override
    public void setDefaultGroup(Mod mod, String value) {
        mod.setDefaultGroup(value);
    }

    @Override
    public boolean equals(Object obj) {
        // short circuit if same object
        if (obj == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(obj instanceof ModelManager)) {
            return false;
        }

        // state check
        ModelManager other = (ModelManager) obj;
        return contactList.equals(other.contactList)
                && userPrefs.equals(other.userPrefs)
                && filteredPersons.equals(other.filteredPersons);
    }

}
