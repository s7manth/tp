package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class GroupTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Group(null));
    }

    @Test
    public void constructor_invalidName_throwsIllegalArgumentException() {
        String invalidName = "";
        assertThrows(IllegalArgumentException.class, () -> new Group(invalidName));
    }

    @Test
    public void isValidGroup() {
        // null name
        assertThrows(NullPointerException.class, () -> Group.isValidGroup(null));

        // invalid name
        assertFalse(Group.isValidGroup("")); // empty string
        assertFalse(Group.isValidGroup(" ")); // spaces only

        // valid name
        assertTrue(Group.isValidGroup("peter jack")); // alphabets only
        assertTrue(Group.isValidGroup("12345")); // numbers only
        assertTrue(Group.isValidGroup("peter the 2nd")); // alphanumeric characters
        assertTrue(Group.isValidGroup("tal Tan")); // with capital letters
        assertTrue(Group.isValidGroup("David Roger Jackson Ray Jr 2nd")); // long names
    }
}

