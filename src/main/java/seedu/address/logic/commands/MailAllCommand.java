package seedu.address.logic.commands;

import static seedu.address.commons.util.MailUtil.launchMail;

import java.util.List;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Email;
import seedu.address.model.person.Person;

public class MailAllCommand extends Command {

    public static final String COMMAND_WORD = "mailall";

    private static final String MESSAGE_SUCCESS = "Opening app for bulk emailing";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": For Mailing everyone in the contact list.\n"
            + "Example: " + COMMAND_WORD;

    @Override
    public CommandResult execute(Model model) throws CommandException {
        List<Person> lastShownList = model.getFilteredPersonList();
        Email[] emailList = new Email[lastShownList.size()];

        int i = 0;
        for (Person p : lastShownList) {
            emailList[i] = p.getEmail();
            i++;
        }

        launchMail(emailList);

        return new CommandResult(MESSAGE_SUCCESS);
    }
}
