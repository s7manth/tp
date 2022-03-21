package seedu.address.logic.commands;


import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;

import static java.util.Objects.requireNonNull;

/**
 * Undoes the previous command that commits to contact list.
 */
public class UndoCommand extends Command {
    public static final String MESSAGE_EARLIEST_VERSION = "The contact list is already in its earliest version";
    public static final String MESSAGE_SUCCESS = "Contact List changed to previous version";

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        if (model.isEarliestContactListVersion()) {
            throw new CommandException(MESSAGE_EARLIEST_VERSION);
        }

        model.undoContactList();

        return new CommandResult(MESSAGE_SUCCESS);
    }
}
