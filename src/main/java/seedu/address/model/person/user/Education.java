package seedu.address.model.person.user;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents the user's Education, as part of the Resume
 */
public class Education {

    public static final String MESSAGE_CONSTRAINTS =
            "Names should only contain alphanumeric characters and spaces, and it should not be blank";

    /*
     * The first character of the address must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "[\\p{Alnum}][\\p{Alnum} ]*";

    public final String education;
    /**
     * Constructs a {@code Education}.
     *
     * @param education A valid string.
     */
    public Education(String education) {
        requireNonNull(education);
        checkArgument(isValidEducation(education), MESSAGE_CONSTRAINTS);
        this.education = education;
    }
    /**
     * Returns true if a given string is a valid name.
     */
    public static boolean isValidEducation(String test) {
        return test.matches(VALIDATION_REGEX);
    }
    @Override
    public String toString() {
        return education;
    }
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof Education)) {
            return false;
        }

        Education otherName = (Education) other;
        return education.equals(otherName.education);
    }

    @Override
    public int hashCode() {
        return education.hashCode();
    }
}
