package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_GROUP;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MOD;

import java.util.Optional;
import java.util.logging.Logger;

import seedu.address.commons.core.LogsCenter;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Mod;




public class SetDefaultGroupCommand extends Command {

    public static final String COMMAND_WORD = "set-default-group";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Sets a default value for group in the given Mod. "
            + "Parameters: "
            + PREFIX_MOD + "MOD "
            + PREFIX_GROUP + "GROUP"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_GROUP + "W12-1 "
            + PREFIX_MOD + "CS2103T";

    public static final String MESSAGE_SUCCESS = "Default value for %1$s Module has been set to %2$s successfully!";
    public static final String MESSAGE_DEFAULT_UPDATE = "Default value for %1$s has been updated from %2$s to %3$s.";

    private static final Logger logger = LogsCenter.getLogger(SetDefaultGroupCommand.class);

    private final Mod mod;
    private final String defaultValue;

    /**
     * Constructor for {@code SetDefaultGroupCommand}.
     * @param mod The module whose default has to be set.
     * @param defaultValue The default value.
     */
    public SetDefaultGroupCommand(Mod mod, String defaultValue) {
        this.mod = mod;
        this.defaultValue = defaultValue;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        if (model.doesModExistInList(mod)) {
            Optional<Mod> matchingMod = model.getMod(mod);
            assert matchingMod != null;
            if (model.isDefaultGroupOfModPresent(matchingMod.get())) {
                String prevDefault = model.retrievePrevDefault(matchingMod.get());
                model.setDefaultGroup(matchingMod.get(), defaultValue);
                return new CommandResult(String.format(MESSAGE_DEFAULT_UPDATE, mod, prevDefault, defaultValue));
            }
        } else {
            model.addMod(mod);
            model.setDefaultGroup(mod, defaultValue);
        }

        return new CommandResult(String.format(MESSAGE_SUCCESS, mod, defaultValue));
    }

}

