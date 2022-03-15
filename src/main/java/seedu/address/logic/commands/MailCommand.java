package seedu.address.logic.commands;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Email;
import seedu.address.model.person.Person;

import java.util.List;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.MailUtil.launchMail;

/**
 * Opens the system default mail app with person identified using it's
 * displayed index from the class group as the receiver.
 */
public class MailCommand extends Command {

    public static final String COMMAND_WORD = "mail";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Opens the mail box with the email of the person identified by the index number.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_SUCCESS = "Opening System Default Mail App: %1$s";

    private final Index index;

    public MailCommand(Index index) {
        requireNonNull(index);
        this.index = index;
    }

    /**
     * Executes the mail command.
     * @param model {@code Model} which the command should operate on.
     * @return {@code CommandResult} object that contains the result of the execution.
     * @throws CommandException if any noncompliance occurs in the command execution.
     */
    @Override
    public CommandResult execute(Model model) throws CommandException {
        List<Person> lastShownList = model.getFilteredPersonList();
        Person personToMail = lastShownList.get(index.getZeroBased());

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Email email = personToMail.getEmail();
        launchMail(email);

        return new CommandResult(String.format(MESSAGE_SUCCESS, personToMail.getName()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof MailCommand // instanceof handles nulls
                && index.equals(((MailCommand) other).index)); // state check
    }
}
