package seedu.address.model;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.nio.file.Path;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.person.Mod;
import seedu.address.model.person.Person;
import seedu.address.model.person.UniqueModuleList;
import seedu.address.model.tasks.PriorityTaskList;
import seedu.address.model.tasks.ReadOnlyTaskList;
import seedu.address.model.tasks.Task;

/**
 * Represents the in-memory model of the contact list and task list data.
 */
public class ModelManager implements Model {
    private static final Logger logger = LogsCenter.getLogger(ModelManager.class);
    private final ContactList contactList;
    private final PriorityTaskList taskList;
    private final UserPrefs userPrefs;
    private final FilteredList<Person> filteredPersons;
    private final UniqueModuleList moduleList;

    /**
     * Initializes a ModelManager with the given contactList, userPrefs and a moduleList.
     */
    public ModelManager(ReadOnlyContactList contactList, ReadOnlyUserPrefs userPrefs, UniqueModuleList moduleList) {
        requireAllNonNull(contactList, userPrefs);

        logger.fine("Initializing with contact list: " + contactList + " and user prefs " + userPrefs);

        this.contactList = new ContactList(contactList);
        this.userPrefs = new UserPrefs(userPrefs);
        this.moduleList = moduleList;
        this.taskList = new PriorityTaskList();
        filteredPersons = new FilteredList<>(this.contactList.getPersonList());
    }

    /**
     * Initializes a ModelManager with the given contactList, userPrefs , taskList and an empty moduleList.
     */
    public ModelManager(ReadOnlyContactList contactList, ReadOnlyUserPrefs userPrefs, ReadOnlyTaskList taskList) {
        requireAllNonNull(contactList, userPrefs, taskList);

        logger.fine("Initializing with contact list: " + contactList + ", user prefs " + userPrefs
                + " and task manager: " + taskList);

        this.contactList = new ContactList(contactList);
        this.taskList = new PriorityTaskList(taskList);
        this.userPrefs = new UserPrefs(userPrefs);
        this.moduleList = new UniqueModuleList();
        filteredPersons = new FilteredList<>(this.contactList.getPersonList());
    }

    public ModelManager() {
        this(new ContactList(), new UserPrefs(), new PriorityTaskList());
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

    @Override
    public Path getTaskListFilePath() {
        return userPrefs.getTaskListFilePath();
    }

    @Override
    public void setTaskListFilePath(Path taskListFilePath) {
        requireNonNull(taskListFilePath);
        userPrefs.setTaskListFilePath(taskListFilePath);
    }

    //=========== ContactList ================================================================================

    @Override
    public void setContactList(ReadOnlyContactList contactList) {
        this.contactList.resetData(contactList);
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
    }

    @Override
    public void addPerson(Person person) {
        contactList.addPerson(person);
        updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
    }

    @Override
    public void setPerson(Person target, Person editedPerson) {
        requireAllNonNull(target, editedPerson);

        contactList.setPerson(target, editedPerson);
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
    public boolean isDefaultGroupOfModPresent(Mod mod) {
        requireNonNull(mod);
        Mod modInList = this.getMod(mod).get();
        return modInList.getDefaultGroup() != null;
    }

    @Override
    public boolean doesModExistInList(Mod mod) {
        requireNonNull(mod);
        return moduleList.contains(mod.value);
    }

    @Override
    public String getDefaultGroupOfMod(Mod mod) {
        Mod modInList = this.getMod(mod).get();
        return modInList.getDefaultGroup();

    }

    @Override
    public void addMod(Mod mod) {
        moduleList.add(mod);
    }

    @Override
    public Optional<Mod> getMod(Mod mod) {
        return moduleList.retrieveMod(mod);
    }

    @Override
    public String retrievePrevDefault(Mod mod) {
        return mod.getDefaultGroup();
    }

    @Override
    public void setDefaultGroup(Mod mod, String value) {
        mod.setDefaultGroup(value);
    }

    //=========== Task Manager =============================================================

    @Override
    public void setTaskList(ReadOnlyTaskList taskList) {
        if (taskList instanceof PriorityTaskList) {
            PriorityTaskList ptl = (PriorityTaskList) taskList;
            this.taskList.resetData(ptl);
        }
    }

    @Override
    public boolean hasTask(Task task) {
        requireNonNull(task);
        return taskList.contains(task);
    }

    @Override
    public void deleteTask(Task target) {
        requireNonNull(target);
        taskList.remove(target);
    }

    @Override
    public void addTask(Task task) {
        requireNonNull(task);
        taskList.add(task);
    }

    @Override
    public ReadOnlyTaskList getTaskList() {
        return this.taskList;
    }

    //=========== OTHERS =============================================================

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
                && filteredPersons.equals(other.filteredPersons)
                && taskList.equals(other.taskList);
    }
}
