package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.model.ContactList;
import seedu.address.model.Model;

/**
 * Clears the class group.
 */
public class ClearCommand extends Command {

    public static final String COMMAND_WORD = "clear";
    public static final String MESSAGE_SUCCESS = "Contact list has been cleared!";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Clears the contact list.\n"
            + "Example: " + COMMAND_WORD;


    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.setContactList(new ContactList());
        // TODO : module list and task list needs to be cleared as well
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
