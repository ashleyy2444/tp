package seedu.address.model.person;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import seedu.address.model.tag.Tag;
import seedu.address.testutil.PersonBuilder;


public class TagContainsKeywordsPredicateTest {

    @Test
    public void equals() {
        List<String> firstPredicateKeywordList = Collections.singletonList("first");
        List<String> secondPredicateKeywordList = Arrays.asList("first", "second");

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
    public void test_tagContainsKeywords_returnsTrue() {
        // One keyword
        TagContainsKeywordsPredicate predicate = new TagContainsKeywordsPredicate(Collections.singletonList("manager"));
        Assertions.assertTrue(predicate.test(new PersonBuilder().withName("Alice Bob").withTags("manager").build()));

        // Multiple keywords
        predicate = new TagContainsKeywordsPredicate(Arrays.asList("manager", "HR"));
        Assertions.assertTrue(predicate.test(new PersonBuilder().withName("Alice Bob").withTags("manager")
                .build()));

        // Only one matching keyword
        predicate = new TagContainsKeywordsPredicate(Arrays.asList("manager", "HR"));
        Assertions.assertTrue(predicate.test(new PersonBuilder().withName("Alice Carol").withTags("manager", "HR")
                .build()));

    }

    @Test
    public void test_tagDoesNotContainKeywords_returnsFalse() {
        // Zero keywords
        TagContainsKeywordsPredicate predicate = new TagContainsKeywordsPredicate(Collections.emptyList());
        Assertions.assertFalse(predicate.test(new PersonBuilder().withName("Alice").withTags("manager").build()));

        // Non-matching keyword
        predicate = new TagContainsKeywordsPredicate(Arrays.asList("manager"));
        Assertions.assertFalse(predicate.test(new PersonBuilder().withName("Alice Bob").build()));
    }

    @Test
    public void toStringMethod() {
        List<String> keywords = List.of("keyword1", "keyword2");
        TagContainsKeywordsPredicate predicate = new TagContainsKeywordsPredicate(keywords);

        String expected = TagContainsKeywordsPredicate.class.getCanonicalName() + "{tags="
                + keywords.stream().map(Tag::new).collect(Collectors.toList()) + "}";
        Assertions.assertEquals(expected, predicate.toString());
    }
}
