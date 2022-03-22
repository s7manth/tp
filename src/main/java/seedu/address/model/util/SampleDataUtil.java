package seedu.address.model.util;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.address.model.ContactList;
import seedu.address.model.ReadOnlyContactList;
import seedu.address.model.person.*;
import seedu.address.model.person.StudentNumber;
import seedu.address.model.tag.Tag;

/**
 * Contains utility methods for populating {@code ContactList} with sample data.
 */
public class SampleDataUtil {
    public static Person[] getSamplePersons() {
        return new Person[] {
            new Person(new Name("Alex Yeoh"), new StudentNumber("87438807"), new Email("alexyeoh@example.com"),
            new Mod("CS2030S"), new Group("T01"),
                getTagSet("friends")),
            new Person(new Name("Bernice Yu"), new StudentNumber("99272758"), new Email("berniceyu@example.com"),
                new Mod("CS2030S"), new Group("T01"),
                getTagSet("colleagues", "friends")),
            new Person(new Name("Charlotte Oliveiro"), new StudentNumber("93210283"), new Email("charlotte@example.com"),
                new Mod("CS2030S"), new Group("T02"),
                getTagSet("neighbours")),
            new Person(new Name("David Li"), new StudentNumber("91031282"), new Email("lidavid@example.com"),
                new Mod("CS2030S"), new Group("T03"),
                getTagSet("family")),
            new Person(new Name("Irfan Ibrahim"), new StudentNumber("92492021"), new Email("irfan@example.com"),
                new Mod("CS2030S"), new Group("T02"),
                getTagSet("classmates")),
            new Person(new Name("Roy Balakrishnan"), new StudentNumber("92624417"), new Email("royb@example.com"),
                new Mod("CS2030S"), new Group("T01"),
                getTagSet("colleagues"))
        };
    }

    public static ReadOnlyContactList getSampleContactList() {
        ContactList sampleAb = new ContactList();
        for (Person samplePerson : getSamplePersons()) {
            sampleAb.addPerson(samplePerson);
        }
        return sampleAb;
    }

    /**
     * Returns a tag set containing the list of strings given.
     */
    public static Set<Tag> getTagSet(String... strings) {
        return Arrays.stream(strings)
                .map(Tag::new)
                .collect(Collectors.toSet());
    }

}
