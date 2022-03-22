package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Person's student number in the class group.
 * Guarantees: immutable; is valid as declared in {@link #isValidStudentNumber(String)}
 */
public class StudentNumber {


    public static final String MESSAGE_CONSTRAINTS =
            "Student Number numbers should begin with a capital 'A', followed by 7 digits" +
                    " and finally concluded by one capitalised letter.";
    public static final String VALIDATION_REGEX = "^[A][0-9]{7}[A-Z]";
    public final String value;

    /**
     * Constructs a {@code StudentNumber}.
     *
     * @param studentNumber A valid student number.
     */
    public StudentNumber(String studentNumber) {
        requireNonNull(studentNumber);
        checkArgument(isValidStudentNumber(studentNumber), MESSAGE_CONSTRAINTS);
        value = studentNumber;
    }

    /**
     * Returns true if a given string is a valid phone number.
     */
    public static boolean isValidStudentNumber(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof StudentNumber // instanceof handles nulls
                && value.equals(((StudentNumber) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}
