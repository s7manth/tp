package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MOD;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.Arrays;

import seedu.address.logic.commands.FindCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.predicates.CompoundedPredicates;
import seedu.address.model.person.predicates.EmailContainsKeywordsPredicate;
import seedu.address.model.person.predicates.ModContainsKeywordsPredicate;
import seedu.address.model.person.predicates.NameContainsKeywordsPredicate;
import seedu.address.model.person.predicates.PhoneContainsKeywordsPredicate;
import seedu.address.model.person.predicates.TagsContainsKeywordsPredicate;

/**
 * Parses input arguments and creates a new FindCommand object
 */
public class FindCommandParser implements Parser<FindCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the FindCommand
     * and returns a FindCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public FindCommand parse(String args) throws ParseException {
        requireNonNull(args);

        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
        }
        // need to add whitespace for compatibility with tokenizer that was originally used for commands
        // with index preambles.
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(" " + trimmedArgs, PREFIX_NAME, PREFIX_PHONE, PREFIX_EMAIL,
                        PREFIX_MOD, PREFIX_TAG);




        CompoundedPredicates compPreds = new CompoundedPredicates();
        if (argMultimap.getValue(PREFIX_NAME).isPresent()) {
            String[] keywords = argMultimap.getValue(PREFIX_NAME).get().split("\\s+");
            compPreds.addPredicate(new NameContainsKeywordsPredicate(Arrays.asList(keywords)));
        }
        if (argMultimap.getValue(PREFIX_PHONE).isPresent()) {
            String[] keywords = argMultimap.getValue(PREFIX_PHONE).get().split("\\s+");
            compPreds.addPredicate(new PhoneContainsKeywordsPredicate(Arrays.asList(keywords)));

        }
        if (argMultimap.getValue(PREFIX_EMAIL).isPresent()) {
            String[] keywords = argMultimap.getValue(PREFIX_EMAIL).get().split("\\s+");
            compPreds.addPredicate(new EmailContainsKeywordsPredicate(Arrays.asList(keywords)));

        }
        if (argMultimap.getValue(PREFIX_MOD).isPresent()) { // to be changed to MOD
            String[] keywords = argMultimap.getValue(PREFIX_MOD).get().split("\\s+");
            compPreds.addPredicate(new ModContainsKeywordsPredicate(Arrays.asList(keywords)));

        }
        if (argMultimap.getValue(PREFIX_TAG).isPresent()) {
            String[] keywords = argMultimap.getValue(PREFIX_TAG).get().split("\\s+");
            compPreds.addPredicate(new TagsContainsKeywordsPredicate(Arrays.asList(keywords)));
        }

        if (compPreds.isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
        }

        return new FindCommand(compPreds);
    }

}
