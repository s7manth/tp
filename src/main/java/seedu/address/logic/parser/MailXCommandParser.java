package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_GROUP;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MOD;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;

import seedu.address.logic.commands.MailXCommand;
import seedu.address.logic.commands.MailXCommand.MailMDescriptor;
import seedu.address.logic.parser.exceptions.ParseException;


/**
 * Parses input arguments and creates a new MailXCommand object
 */
public class MailXCommandParser implements Parser<MailXCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the MailXCommand
     * and returns a MailXCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public MailXCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_EMAIL, PREFIX_MOD, PREFIX_GROUP);

        MailMDescriptor mailMDescriptor = new MailMDescriptor();

        if (argMultimap.getValue(PREFIX_NAME).isPresent()) {
            mailMDescriptor.setName(ParserUtil.parseName(argMultimap.getValue(PREFIX_NAME).get()));
        }

        if (argMultimap.getValue(PREFIX_EMAIL).isPresent()) {
            mailMDescriptor.setEmail(ParserUtil.parseEmail(argMultimap.getValue(PREFIX_EMAIL).get()));
        }

        if (argMultimap.getValue(PREFIX_GROUP).isPresent()) {
            mailMDescriptor.setGroup(ParserUtil.parseGroup(argMultimap.getValue(PREFIX_GROUP).get()));
        }

        if (argMultimap.getValue(PREFIX_MOD).isPresent()) {
            mailMDescriptor.setMod(ParserUtil.parseMod(argMultimap.getValue(PREFIX_MOD).get()));
        }

        return new MailXCommand(mailMDescriptor);
    }
}
