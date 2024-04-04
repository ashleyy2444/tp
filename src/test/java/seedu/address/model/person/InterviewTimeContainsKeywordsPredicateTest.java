package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.PersonBuilder;


public class InterviewTimeContainsKeywordsPredicateTest {

    @Test
    public void equals() {
        List<List<InterviewTime>> firstPredicateKeywordList =
                Collections.singletonList(Arrays.asList(
                        new InterviewTime("121220210000"), null));
        List<List<InterviewTime>> secondPredicateKeywordList = Arrays.asList(
                Arrays.asList(null, new InterviewTime("121220210000")),
                Arrays.asList(new InterviewTime("121220220000"), new InterviewTime("121220250000")),
                Arrays.asList(new InterviewTime("121220270000"), null)
        );

        InterviewTimeContainsKeywordsPredicate firstPredicate =
                new InterviewTimeContainsKeywordsPredicate(firstPredicateKeywordList);
        InterviewTimeContainsKeywordsPredicate secondPredicate =
                new InterviewTimeContainsKeywordsPredicate(secondPredicateKeywordList);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        InterviewTimeContainsKeywordsPredicate firstPredicateCopy =
                new InterviewTimeContainsKeywordsPredicate(firstPredicateKeywordList);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different person -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_interviewTimeRangeContainsKeywords_returnsTrue() {
        // One interview time range keyword
        InterviewTimeContainsKeywordsPredicate predicate = new InterviewTimeContainsKeywordsPredicate(Arrays.asList(
                Arrays.asList(null, new InterviewTime("121220220900")))
        );
        assertTrue(predicate.test(new PersonBuilder().withDateTime("120320190200").build()));

        // Multiple interview time range keywords
        predicate = new InterviewTimeContainsKeywordsPredicate(Arrays.asList(
                Arrays.asList(null, new InterviewTime("121220270900")),
                Arrays.asList(new InterviewTime("121220230900"), new InterviewTime("121220260900")))
        );
        assertTrue(predicate.test(new PersonBuilder().withDateTime("120920250814").build()));


        // Only one matching interview time range keyword
        predicate = new InterviewTimeContainsKeywordsPredicate(Arrays.asList(
                Arrays.asList(null, new InterviewTime("121220200900")),
                Arrays.asList(new InterviewTime("121220230900"), new InterviewTime("121220260900")))
        );
        assertTrue(predicate.test(new PersonBuilder().withDateTime("120920250814").build()));

    }
    @Test
    public void test_interviewTimeRangeDoesNotContainKeywords_returnsFalse() {
        // Zero keywords
        InterviewTimeContainsKeywordsPredicate predicate = new InterviewTimeContainsKeywordsPredicate(
                Collections.emptyList());
        assertFalse(predicate.test(new PersonBuilder().withDateTime("120920250814").build()));

        // Non-matching keyword
        predicate = new InterviewTimeContainsKeywordsPredicate(Arrays.asList(
                Arrays.asList(null, new InterviewTime("121220200900")),
                Arrays.asList(new InterviewTime("121220230900"), new InterviewTime("121220260900")))
        );
        assertFalse(predicate.test(new PersonBuilder().withDateTime("120920280814").build()));
    }

    @Test
    public void toStringMethod() {
        List<List<InterviewTime>> keywords = Arrays.asList(
                Arrays.asList(null, new InterviewTime("121220210000")),
                Arrays.asList(new InterviewTime("121220220000"), new InterviewTime("121220250000")),
                Arrays.asList(new InterviewTime("121220270000"), null)
        );
        InterviewTimeContainsKeywordsPredicate predicate = new InterviewTimeContainsKeywordsPredicate(keywords);

        String expected =
                InterviewTimeContainsKeywordsPredicate.class.getCanonicalName()
                        + "{interviewTimes=" + keywords + "}";
        assertEquals(expected, predicate.toString());
    }
}
