package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class CompanyNameTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new CompanyName(null));
    }

    @Test
    public void constructor_invalidName_throwsIllegalArgumentException() {
        String invalidName = "";
        assertThrows(IllegalArgumentException.class, () -> new CompanyName(invalidName));
    }

    @Test
    public void isValidName() {
        // null name
        assertThrows(NullPointerException.class, () -> CompanyName.isValidCompanyName(null));

        // invalid name
        assertFalse(CompanyName.isValidCompanyName("")); // empty string
        assertFalse(CompanyName.isValidCompanyName(" ")); // spaces only
        assertFalse(CompanyName.isValidCompanyName("123456789 123456789 123456789 123456789 123456789 123456789 "
                + "123456789 "
                + "123456789 123456789 123456789 1")); // contains more than 100 chracters


        // valid name
        assertTrue(CompanyName.isValidCompanyName("Google")); // alphabets only
        assertTrue(CompanyName.isValidCompanyName("711")); // numbers only
        assertTrue(CompanyName.isValidCompanyName("Capital Land")); // with capital letters
        // with capital letters and non-alphanumeric characters
        assertTrue(CompanyName.isValidCompanyName("S&P 500"));
        // long names
        assertTrue(CompanyName.isValidCompanyName("Essilor International Compagnie Generale d'Optique SA"));
    }

    @Test
    public void equals() {
        CompanyName name = new CompanyName("Valid Name");

        // same values -> returns true
        assertTrue(name.equals(new CompanyName("Valid Name")));

        // same object -> returns true
        assertTrue(name.equals(name));

        // null -> returns false
        assertFalse(name.equals(null));

        // different types -> returns false
        assertFalse(name.equals(5.0f));

        // different values -> returns false
        assertFalse(name.equals(new CompanyName("Other Valid Name")));
    }
}
