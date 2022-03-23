package seedu.address.logic.commands;

import static java.util.Objects.isNull;
import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.MailUtil.launchMail;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_GROUP;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MOD;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Email;
import seedu.address.model.person.Group;
import seedu.address.model.person.Mod;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;

/**
 * Opens the system default mail app with people
 * identified by the arguments specified by the user.
 */
public class MailMCommand extends Command {

    public static final String COMMAND_WORD = "mail-m";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Opens the mail box with the emails of the people identified by parameters \n"
            + "Optionally include parameters: \n"
            + "[" + PREFIX_NAME + "NAME] "
            + "[" + PREFIX_EMAIL + "EMAIL] "
            + "[" + PREFIX_GROUP + "GROUP] "
            + "[" + PREFIX_MOD + "MOD] "
            + "Example: " + COMMAND_WORD + " 1"
            + "or " + COMMAND_WORD + PREFIX_EMAIL + "johndoe@example.com";

    public static final String MESSAGE_SUCCESS = "Opening System Default Mail App";

    public static final String NAME_NOT_PRESENT = "Name not present in the contact list";

    public static final String EMAIL_NOT_PRESENT = "Email not present in the contact list";

    public static final String MOD_NOT_PRESENT = "Module not present in the contact list";

    public static final String GROUP_NOT_PRESENT = "Group not present in the contact list";

    private final MailMDescriptor mailMDescriptor;

    /**
     * Constructor for the MailMCommand class.
     * @param mailMDescriptor The {@code MailMDescriptor} object which collates all the email addresses to mail to.
     */
    public MailMCommand(MailMDescriptor mailMDescriptor) {
        requireNonNull(mailMDescriptor);

        this.mailMDescriptor = mailMDescriptor;
    }

    /**
     * Executes the mail-m command.
     * @param model {@code Model} which the command should operate on.
     * @return {@code CommandResult} object that contains the result of the execution.
     * @throws CommandException if any noncompliance occurs in the command execution.
     */
    @Override
    public CommandResult execute(Model model) throws CommandException {
        launchMail(createEmailList(this.mailMDescriptor, model));
        return new CommandResult(MESSAGE_SUCCESS);
    }

    /**
     * Creates the email list as specified the arguments passed by the user.
     * @param mailMDescriptor The {@code MailMDescriptor} object which collates all the email addresses to mail to.
     * @param model {@code Model} which the command should operate on.
     * @return The list of email addresses to whom the mail is directed towards.
     * @throws CommandException if the command execution goes unexpected.
     */
    private Email[] createEmailList(MailMDescriptor mailMDescriptor, Model model) throws CommandException {
        List<Email> emailList = new ArrayList<>();
        List<Person> lastShownList = model.getFilteredPersonList();

        Name name = mailMDescriptor.getName().orElse(null);
        if (!isNull(name)) {
            if (lastShownList.stream().anyMatch(person -> person.getName().equals(name))) {
                emailList.add(lastShownList.stream().filter(person ->
                        person.getName().equals(name)).findFirst().get().getEmail());
            } else {
                throw new CommandException(NAME_NOT_PRESENT);
            }
        }

        Email email = mailMDescriptor.getEmail().orElse(null);
        if (!isNull(email)) {
            if (lastShownList.stream().anyMatch(person -> person.getEmail().equals(email))) {
                Email e = lastShownList.stream().filter(person ->
                        person.getEmail().equals(email)).findFirst().get().getEmail();

                if (!emailList.contains(e)) {
                    emailList.add(e);
                }
            } else {
                throw new CommandException(EMAIL_NOT_PRESENT);
            }
        }

        Mod mod = mailMDescriptor.getMod().orElse(null);
        if (!isNull(mod)) {
            if (lastShownList.stream().anyMatch(person -> person.getMod().equals(mod))) {
                List<Person> peopleInModule = lastShownList.stream().filter(person ->
                        person.getMod().equals(mod)).collect(Collectors.toList());
                for (Person p : peopleInModule) {
                    Email e = p.getEmail();
                    if (!emailList.contains(e)) {
                        emailList.add(e);
                    }
                }
            } else {
                throw new CommandException(MOD_NOT_PRESENT);
            }
        }

        Group group = mailMDescriptor.getGroup().orElse(null);
        if (!isNull(group)) {
            if (lastShownList.stream().anyMatch(person -> person.getGroup().equals(group))) {
                List<Person> peopleInGroup = lastShownList.stream().filter(person ->
                        person.getGroup().equals(group)).collect(Collectors.toList());
                for (Person p : peopleInGroup) {
                    Email e = p.getEmail();
                    if (!emailList.contains(e)) {
                        emailList.add(e);
                    }
                }
            } else {
                throw new CommandException(GROUP_NOT_PRESENT);
            }
        }

        Email[] emailListToReturn = new Email[emailList.size()];
        emailListToReturn = emailList.toArray(emailListToReturn);


        return emailListToReturn;
    }

    /**
     * Checks whether two {@code MailMCommand} objects are equal.
     * @param other The {@code MailMCommand} to check equality against.
     * @return The boolean value specifying the equality.
     */
    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof EditCommand)) {
            return false;
        }

        // state check
        MailMCommand m = (MailMCommand) other;
        return mailMDescriptor.equals(m.mailMDescriptor);
    }

    public static class MailMDescriptor {
        private Name name;
        private Email email;
        private Mod mod;
        private Group group;

        public MailMDescriptor() {}

        /**
         * Copy constructor with set values.
         */
        public MailMDescriptor(MailMDescriptor toCopy) {
            setName(toCopy.name);
            setEmail(toCopy.email);
            setGroup(toCopy.group);
            setMod(toCopy.mod);
        }

        /**
         * Sets the name field for the {@code MailMDescriptor} object.
         * @param name The name to be set.
         */
        public void setName(Name name) {
            this.name = name;
        }

        /**
         * Obtains the name field for the {@code MailMDescriptor} object.
         * @return The {@code Optional} object containing the name.
         */
        public Optional<Name> getName() {
            return Optional.ofNullable(name);
        }

        /**
         * Sets the email field for the {@code MailMDescriptor} object.
         * @param email The email to be set.
         */
        public void setEmail(Email email) {
            this.email = email;
        }

        /**
         * Obtains the email field for the {@code MailMDescriptor} object.
         * @return The {@code Optional} object containing the email.
         */
        public Optional<Email> getEmail() {
            return Optional.ofNullable(email);
        }

        /**
         * Sets the mod field for the {@code MailMDescriptor} object.
         * @param mod The mod to be set.
         */
        public void setMod(Mod mod) {
            this.mod = mod;
        }

        /**
         * Obtains the mod field for the {@code MailMDescriptor} object.
         * @return The {@code Optional} object containing the mod.
         */
        public Optional<Mod> getMod() {
            return Optional.ofNullable(mod);
        }

        /**
         * Sets the group field for the {@code MailMDescriptor} object.
         * @param group The group to be set.
         */
        public void setGroup(Group group) {
            this.group = group;
        }

        /**
         * Obtains the group field for the {@code MailMDescriptor} object.
         * @return The {@code Optional} object containing the group.
         */
        public Optional<Group> getGroup() {
            return Optional.ofNullable(group);
        }

        /**
         * Checks whether two {@code MailMDescriptor} objects are equal or not.
         * @param other The {@code MailMDescriptor} to check equality against.
         * @return The boolean value specifying whether the two objects are equal or not.
         */
        @Override
        public boolean equals(Object other) {
            // short circuit if same object
            if (other == this) {
                return true;
            }

            // instanceof handles nulls
            if (!(other instanceof MailMDescriptor)) {
                return false;
            }

            // state check
            MailMDescriptor e = (MailMDescriptor) other;

            return getName().equals(e.getName())
                    && getEmail().equals(e.getEmail())
                    && getMod().equals(e.getMod());
        }
    }
}
