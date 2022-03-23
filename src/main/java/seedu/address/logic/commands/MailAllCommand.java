package seedu.address.logic.commands;

import static seedu.address.commons.util.MailUtil.launchMail;

import java.util.List;
import java.util.stream.Collectors;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Email;
import seedu.address.model.person.Person;

/**
 * Opens the system default mail application to mail all the people in the contact list.
 */
public class MailAllCommand extends Command {

    public static final String COMMAND_WORD = "mailall";

    private static final String MESSAGE_SUCCESS = "Opening app for bulk emailing";

    /**
     * Executes the mailall command.
     * @param model {@code Model} which the command should operate on.
     * @return A {@code CommandResult} object specifying the success message.
     * @throws CommandException if the command execution goes unexpected.
     */
    @Override
    public CommandResult execute(Model model) throws CommandException {
        List<Person> lastShownList = model.getFilteredPersonList();
        List<Email> emails = lastShownList.stream().map(Person::getEmail).distinct().collect(Collectors.toList());

        Email[] emailArray = new Email[emails.size()];

        emailArray = emails.toArray(emailArray);
        launchMail(emailArray);

        return new CommandResult(MESSAGE_SUCCESS);
    }
}
