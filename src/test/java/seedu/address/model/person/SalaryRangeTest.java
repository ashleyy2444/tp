package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;



public class SalaryRangeTest {
    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Salary(null));
    }

    @Test
    public void isValidSalaryRange() {
        // Valid salary ranges
        assertTrue(SalaryRange.isValidSalaryRange("0"));
        assertTrue(SalaryRange.isValidSalaryRange("2495762"));
        assertTrue(SalaryRange.isValidSalaryRange("22930-1003874"));
        assertTrue(SalaryRange.isValidSalaryRange(">=0"));
        assertTrue(SalaryRange.isValidSalaryRange("<=2147483647"));

        // Invalid salary ranges
        assertFalse(SalaryRange.isValidSalaryRange(""));
        assertFalse(SalaryRange.isValidSalaryRange("   "));
        assertFalse(SalaryRange.isValidSalaryRange("5>6"));
        assertFalse(SalaryRange.isValidSalaryRange("09<234"));
        assertFalse(SalaryRange.isValidSalaryRange("--80"));
        assertFalse(SalaryRange.isValidSalaryRange("80--80"));
        assertFalse(SalaryRange.isValidSalaryRange("asfkjb"));
        assertFalse(SalaryRange.isValidSalaryRange("2147483648"));
        assertFalse(SalaryRange.isValidSalaryRange("-1-4"));
        assertFalse(SalaryRange.isValidSalaryRange("1--4"));
        assertFalse(SalaryRange.isValidSalaryRange("1-asovn"));
        assertFalse(SalaryRange.isValidSalaryRange(">=-1"));
        assertFalse(SalaryRange.isValidSalaryRange(">=asokc"));
        assertFalse(SalaryRange.isValidSalaryRange("<=-1"));
        assertFalse(SalaryRange.isValidSalaryRange("<=adv^aeo"));

    }
    @Test
    public void isWithinSalaryRange() {
        SalaryRange salaryRange = new SalaryRange("2000-8000");
        Salary lessThanRange = new Salary("100-300");
        Salary moreThanRange = new Salary("8500-10000");
        Salary withinRange1 = new Salary("1000-3000");
        Salary withinRange2 = new Salary("3000-9000");

        // Salaries not within salary range
        assertFalse(salaryRange.isWithinSalaryRange(lessThanRange));
        assertFalse(salaryRange.isWithinSalaryRange(moreThanRange));

        // Salaries within salary range
        assertTrue(salaryRange.isWithinSalaryRange(withinRange1));
        assertTrue(salaryRange.isWithinSalaryRange(withinRange2));
    }

    @Test
    public void toStringTest() {
        // Test with a salary range
        SalaryRange salaryRange = new SalaryRange("4000-5000");
        assertEquals("4000-5000", salaryRange.toString());

        // Test with a single salary range
        salaryRange = new SalaryRange("6000");
        assertEquals("6000", salaryRange.toString());

        // Test with max salary
        salaryRange = new SalaryRange("<=80000");
        assertEquals("<=80000", salaryRange.toString());

        // Test with min salary
        salaryRange = new SalaryRange(">=800920");
        assertEquals(">=800920", salaryRange.toString());
    }

    @Test
    public void equalsTest() {
        SalaryRange singleValue = new SalaryRange("6000");
        SalaryRange toMaxRange1 = new SalaryRange("5000-2147483647");
        SalaryRange toMaxRange2 = new SalaryRange("5000-2147483647");
        SalaryRange minRange = new SalaryRange(">=5000");
        SalaryRange maxRange = new SalaryRange("<=4000");

        // same object -> returns true
        assertTrue(singleValue.equals(singleValue));
        // same range -> returns true
        assertTrue(toMaxRange1.equals(toMaxRange2));
        assertTrue(toMaxRange1.equals(minRange));

        // different type -> returns false
        assertFalse(singleValue.equals(9));
        // null -> returns false
        assertFalse(singleValue.equals(null));
        // different range -> returns false
        assertFalse(toMaxRange1.equals(singleValue));
        assertFalse(toMaxRange1.equals(maxRange));

    }
}
