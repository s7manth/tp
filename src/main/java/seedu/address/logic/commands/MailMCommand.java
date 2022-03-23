package seedu.address.logic.commands;

import static java.util.Objects.isNull;
import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.MailUtil.launchMail;
import static seedu.address.logic.parser.CliSyntax.*;

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
 * Opens the system default mail app with person identified using it's
 * displayed index from the class group as the receiver.
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
     * @param mailMDescriptor The indexes of the students to mail to.
     */
    public MailMCommand(MailMDescriptor mailMDescriptor) {
        requireNonNull(mailMDescriptor);

        this.mailMDescriptor = mailMDescriptor;
    }

    /**
     * Executes the mail command.
     * @param model {@code Model} which the command should operate on.
     * @return {@code CommandResult} object that contains the result of the execution.
     * @throws CommandException if any noncompliance occurs in the command execution.
     */
    @Override
    public CommandResult execute(Model model) throws CommandException {
        launchMail(createEmailList(mailMDescriptor, model));
        return new CommandResult(MESSAGE_SUCCESS);
    }

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
         * Copy constructor.
         * A defensive copy of {@code tags} is used internally.
         */
        public MailMDescriptor(MailMDescriptor toCopy) {
            setName(toCopy.name);
            setEmail(toCopy.email);
            setGroup(toCopy.group);
            setMod(toCopy.mod);
        }

        public void setName(Name name) {
            this.name = name;
        }

        public Optional<Name> getName() {
            return Optional.ofNullable(name);
        }

        public void setEmail(Email email) {
            this.email = email;
        }

        public Optional<Email> getEmail() {
            return Optional.ofNullable(email);
        }

        public void setMod(Mod mod) {
            this.mod = mod;
        }

        public Optional<Mod> getMod() {
            return Optional.ofNullable(mod);
        }

        public void setGroup(Group group) {
            this.group = group;
        }

        public Optional<Group> getGroup() {
            return Optional.ofNullable(group);
        }

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
