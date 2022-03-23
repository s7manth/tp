package seedu.address.logic.commands;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Person;

import java.util.List;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.MailUtil.launchMail;

public class MailSCommand extends Command {

    public static final String COMMAND_WORD = "mail-s";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Opens the mailbox to email the person identified by the index number used in the displayed person list.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_DELETE_PERSON_SUCCESS = "Opening System Default Mail Application : %1$s";

    private final Index targetIndex;

    public MailSCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Person> lastShownList = model.getFilteredPersonList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Person personToEmail = lastShownList.get(targetIndex.getZeroBased());

        launchMail(personToEmail.getEmail());
        return new CommandResult(String.format(MESSAGE_DELETE_PERSON_SUCCESS, personToEmail.getEmail()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof MailSCommand// instanceof handles nulls
                && targetIndex.equals(((MailSCommand) other).targetIndex)); // state check
    }
}
