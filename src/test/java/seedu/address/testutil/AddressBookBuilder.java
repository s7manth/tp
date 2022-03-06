package seedu.address.testutil;

import seedu.address.model.ContactList;
import seedu.address.model.person.Person;

/**
 * A utility class to help with building Addressbook objects.
 * Example usage: <br>
 *     {@code ContactList ab = new AddressBookBuilder().withPerson("John", "Doe").build();}
 */
public class AddressBookBuilder {

    private ContactList contactList;

    public AddressBookBuilder() {
        contactList = new ContactList();
    }

    public AddressBookBuilder(ContactList contactList) {
        this.contactList = contactList;
    }

    /**
     * Adds a new {@code Person} to the {@code ContactList} that we are building.
     */
    public AddressBookBuilder withPerson(Person person) {
        contactList.addPerson(person);
        return this;
    }

    public ContactList build() {
        return contactList;
    }
}
