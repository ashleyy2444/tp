package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.address.model.person.user.Education;

public class EducationTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Education(null));
    }
    @Test
    public void constructor_invalidName_throwsIllegalArgumentException() {
        String invalidName = "";
        assertThrows(IllegalArgumentException.class, () -> new Education(invalidName));
    }
    @Test
    public void isValidEducation() {
        assertTrue(Education.isValidEducation("AaBb123"));
        assertTrue(Education.isValidEducation("A"));

        assertFalse(Education.isValidEducation("!Invalid")); // Starts with a non-alphanumeric character
        assertFalse(Education.isValidEducation("Contains!@#")); // Contains special characters
    }
    @Test
    public void equals() {
        Education name = new Education("Valid Name");

        // same values -> returns true
        assertTrue(name.equals(new Education("Valid Name")));

        // same object -> returns true
        assertTrue(name.equals(name));

        // null -> returns false
        assertFalse(name.equals(null));

        // different types -> returns false
        assertFalse(name.equals(5.0f));

        // different values -> returns false
        assertFalse(name.equals(new Education("Other Valid Name")));
    }
}
