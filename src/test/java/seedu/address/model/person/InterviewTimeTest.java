package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

public class InterviewTimeTest {
    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new InterviewTime(null));
    }

    @Test
    public void constructor_invalidDateTime_throwsDateTimeParseException() {
        String invalidDateTime = "dfbnkasljbjbe";
        String invalidDateTime2 = "140020240402";
        assertThrows(IllegalArgumentException.class, () -> new InterviewTime(invalidDateTime));
        assertThrows(IllegalArgumentException.class, () -> new InterviewTime(invalidDateTime2));
    }

    @Test
    public void testIsWithinInterviewTimeRange() {
        InterviewTime dateTimeAfterRange = new InterviewTime("121120271400");
        InterviewTime dateTimeBeforeRange = new InterviewTime("121120201400");
        InterviewTime dateTimeInRange = new InterviewTime("121120231400");

        // Interview time ranges
        List<InterviewTime> wrongSize = Arrays.asList(null, null, null);
        List<InterviewTime> invalidInput = Arrays.asList(null, null);
        List<InterviewTime> validBefore = Arrays.asList(null, new InterviewTime("121220230900"));
        List<InterviewTime> validAfter = Arrays.asList(new InterviewTime("011120231400"), null);
        List<InterviewTime> validRange = Arrays.asList(new InterviewTime("011120231400"),
                new InterviewTime("121220240300"));

        // Assertion error thrown
        try {
            dateTimeInRange.isWithinInterviewTimeRange(wrongSize);
            assert(false);
        } catch (AssertionError e) {
            assertEquals("InterviewTime range should be of size 2", e.getMessage());
        }
        try {
            dateTimeInRange.isWithinInterviewTimeRange(invalidInput);
            assert(false);
        } catch (AssertionError e) {
            assertEquals("InterviewTime range cannot cannot contain more than 1 null Object", e.getMessage());
        }

        // Return false
        assertFalse(dateTimeAfterRange.isWithinInterviewTimeRange(validBefore)); // dateTime falls after range
        assertFalse(dateTimeAfterRange.isWithinInterviewTimeRange(validRange)); // dateTime falls after range
        assertFalse(dateTimeBeforeRange.isWithinInterviewTimeRange(validAfter)); // dateTime falls before range
        assertFalse(dateTimeBeforeRange.isWithinInterviewTimeRange(validRange)); // dateTime falls before range

        // Return true
        assertTrue(dateTimeAfterRange.isWithinInterviewTimeRange(validAfter));
        assertTrue(dateTimeBeforeRange.isWithinInterviewTimeRange(validBefore));
        assertTrue(dateTimeInRange.isWithinInterviewTimeRange(validRange));

    }
    @Test
    public void equals() {
        InterviewTime dateTime = new InterviewTime("121220221400");

        // same values -> returns true
        assertTrue(dateTime.equals(new InterviewTime("121220221400")));

        // same object -> returns true
        assertTrue(dateTime.equals(dateTime));

        // null -> returns false
        assertFalse(dateTime.equals(null));

        // different types -> returns false
        assertFalse(dateTime.equals(5.0f));

        // different values -> returns false
        assertFalse(dateTime.equals(new Address("111220221400")));
    }
    @Test
    public void parseInterviewTime() {
        InterviewTime dateTime = new InterviewTime("121220221400");
        assertEquals("2022-12-12T14:00", dateTime.getDateTime().toString());
    }

    @Test
    public void hashcode() {
        InterviewTime dateTime = new InterviewTime("121220221400");

        //different values -> different hashcode
        assertFalse(dateTime.hashCode() == new InterviewTime("121120221400").hashCode());
    }
    @Test
    public void toStringTest() {
        InterviewTime dateTime = new InterviewTime("121220221400");
        assertEquals("December 12, 2022 02:00 PM", dateTime.toString());
    }
}
