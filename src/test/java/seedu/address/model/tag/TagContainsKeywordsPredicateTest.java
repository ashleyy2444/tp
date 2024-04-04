package seedu.address.model.tag;

import static seedu.address.logic.parser.FilterTagCommandParser.createTags;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.testutil.PersonBuilder;

public class TagContainsKeywordsPredicateTest {

    @Test
    public void equals() throws ParseException {
        List<Tag> firstPredicateKeywordList = createTags("first");
        List<Tag> secondPredicateKeywordList = createTags("first", "second");

        TagContainsKeywordsPredicate firstPredicate = new TagContainsKeywordsPredicate(firstPredicateKeywordList);
        TagContainsKeywordsPredicate secondPredicate = new TagContainsKeywordsPredicate(secondPredicateKeywordList);

        // same object -> returns true
        Assertions.assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        TagContainsKeywordsPredicate firstPredicateCopy = new TagContainsKeywordsPredicate(firstPredicateKeywordList);
        Assertions.assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        Assertions.assertFalse(firstPredicate.equals(1));

        // null -> returns false
        Assertions.assertFalse(firstPredicate.equals(null));

        // different person -> returns false
        Assertions.assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_tagContainsKeywords_returnsTrue() throws ParseException {
        // One keyword
        TagContainsKeywordsPredicate predicate = new TagContainsKeywordsPredicate(createTags("manager"));
        Assertions.assertTrue(predicate.test(new PersonBuilder().withName("Alice Bob").withTags("manager").build()));

        // Multiple keywords
        predicate = new TagContainsKeywordsPredicate(createTags("manager", "HR"));
        Assertions.assertTrue(predicate.test(new PersonBuilder().withName("Alice Bob").withTags("manager")
                .build()));

        // Only one matching keyword
        predicate = new TagContainsKeywordsPredicate(createTags("manager", "HR"));
        Assertions.assertTrue(predicate.test(new PersonBuilder().withName("Alice Carol").withTags("manager", "HR")
                .build()));

    }

    @Test
    public void test_tagDoesNotContainKeywords_returnsFalse() throws ParseException {
        // Zero keywords
        TagContainsKeywordsPredicate predicate = new TagContainsKeywordsPredicate(Collections.emptyList());
        Assertions.assertFalse(predicate.test(new PersonBuilder().withName("Alice").withTags("manager").build()));

        // Non-matching keyword
        predicate = new TagContainsKeywordsPredicate(createTags("manager"));
        Assertions.assertFalse(predicate.test(new PersonBuilder().withName("Alice Bob").build()));
    }

    @Test
    public void toStringMethodOverride() {
        List<Tag> tags = Arrays.asList(new Tag("tag1"), new Tag("tag2"));
        TagContainsKeywordsPredicate instance = new TagContainsKeywordsPredicate(tags);

        String expected = "[tag1], [tag2]";
        Assertions.assertEquals(expected, instance.toString());
    }
}
