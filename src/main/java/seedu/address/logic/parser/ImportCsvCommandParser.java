package seedu.address.logic.parser;

import seedu.address.logic.commands.ImportCsvCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Group;
import seedu.address.model.person.Mod;

import java.nio.file.Path;
import java.nio.file.Paths;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.*;

public class ImportCsvCommandParser implements Parser<ImportCsvCommand> {

    public static final String NO_GROUP_AND_MOD_ARGUMENT_PRESENT = "Module and group arguments are not present.";

    public static final String GROUP_NOT_SPECIFIED = "Group argument not specified";

    public static final String MOD_NOT_SPECIFIED = "Module argument not specified";

    public static final String NO_PATH_MENTIONED = "Path to read the CSV file not specified";

    @Override
    public ImportCsvCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args);

        String preamble = argMultimap.getPreamble().trim();

        System.out.println(preamble);

        if (preamble.isEmpty()) {
            throw new ParseException(NO_PATH_MENTIONED);
        }

        Path path = Paths.get(preamble);
        return new ImportCsvCommand(path);
    }
}
