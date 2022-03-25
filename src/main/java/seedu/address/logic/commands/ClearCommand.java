package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.model.ContactList;
import seedu.address.model.Model;

/**
 * Clears the class group.
 */
public class ClearCommand extends Command {

    public static final String COMMAND_WORD = "clear";
    public static final String MESSAGE_SUCCESS = "Class group has been cleared!";


    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.setContactList(new ContactList());
        model.commitContent();
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
