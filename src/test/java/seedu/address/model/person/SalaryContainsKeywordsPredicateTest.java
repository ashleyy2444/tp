package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.PersonBuilder;




public class SalaryContainsKeywordsPredicateTest {

    @Test
    public void equals() {
        List<Salary> firstPredicateKeywordList = Collections.singletonList(new Salary("1000"));
        List<Salary> secondPredicateKeywordList = Arrays.asList(new Salary("1000"), new Salary("5000-8000"));

        SalaryContainsKeywordsPredicate firstPredicate = new SalaryContainsKeywordsPredicate(firstPredicateKeywordList);
        SalaryContainsKeywordsPredicate secondPredicate =
                new SalaryContainsKeywordsPredicate(secondPredicateKeywordList);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        SalaryContainsKeywordsPredicate firstPredicateCopy =
                new SalaryContainsKeywordsPredicate(firstPredicateKeywordList);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different person -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_salaryContainsKeywords_returnsTrue() {
        // One salary keyword
        SalaryContainsKeywordsPredicate predicate = new SalaryContainsKeywordsPredicate(
                Collections.singletonList(new Salary("1000")));
        assertTrue(predicate.test(new PersonBuilder().withSalary("1000-2000").build()));

        // Multiple salary keywords
        predicate = new SalaryContainsKeywordsPredicate(Arrays.asList(new Salary("1500"), new Salary("1000")));
        assertTrue(predicate.test(new PersonBuilder().withSalary("1000-2000").build()));

        // Only one matching salary keyword
        predicate = new SalaryContainsKeywordsPredicate(Arrays.asList(new Salary("1000"), new Salary("3000")));
        assertTrue(predicate.test(new PersonBuilder().withSalary("1000-2000").build()));

    }
    @Test
    public void test_salaryDoesNotContainKeywords_returnsFalse() {
        // Zero keywords
        SalaryContainsKeywordsPredicate predicate = new SalaryContainsKeywordsPredicate(Collections.emptyList());
        assertFalse(predicate.test(new PersonBuilder().withSalary("1000-2000").build()));

        // Non-matching keyword
        predicate = new SalaryContainsKeywordsPredicate(Arrays.asList(new Salary("5000")));
        assertFalse(predicate.test(new PersonBuilder().withSalary("1000-2000").build()));
    }

    @Test
    public void toStringMethod() {
        List<Salary> keywords = List.of(new Salary("1000"), new Salary("2000-5000"));
        SalaryContainsKeywordsPredicate predicate = new SalaryContainsKeywordsPredicate(keywords);

        String expected = SalaryContainsKeywordsPredicate.class.getCanonicalName() + "{salaries=" + keywords + "}";
        assertEquals(expected, predicate.toString());
    }
}
