package seedu.address.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.person.UniqueModuleList;

/**
 * Represents a storage for {@link PriorityTaskList}.
 */
public interface ModuleListStorage {
    /**
     * Returns the file path of the data file.
     */
    Path getModuleListFilePath();

    /**
     * Returns Task list data as a {@link ReadOnlyTaskList}.
     *   Returns {@code Optional.empty()} if storage file is not found.
     * @throws DataConversionException if the data in storage is not in the expected format.
     * @throws IOException if there was any problem when reading from the storage.
     */
    Optional<UniqueModuleList> readModuleList() throws DataConversionException, IOException;

    /**
     * @see #getTaskListFilePath()
     */
    Optional<UniqueModuleList> readModuleList(Path filePath) throws DataConversionException, IOException;

    /**
     * Saves the given {@link ReadOnlyTaskList} to the storage.
     * @param contactList cannot be null.
     * @throws IOException if there was any problem writing to the file.
     */
    void saveModuleList(UniqueModuleList contactList) throws IOException;

    /**
     * @see #saveTaskList(ReadOnlyTaskList)
     */
    void saveModuleList(UniqueModuleList contactList, Path filePath) throws IOException;

}
