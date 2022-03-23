package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.MailSCommand;
import seedu.address.logic.parser.exceptions.ParseException;

public class MailSCommandParser {
    /**
     * Parses the given {@code String} of arguments in the context of the MailSCommand
     * and returns a MailSCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public MailSCommand parse(String args) throws ParseException {
        try {
            Index index = ParserUtil.parseIndex(args);
            return new MailSCommand(index);
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, MailSCommand.MESSAGE_USAGE), pe);
        }
    }
}
