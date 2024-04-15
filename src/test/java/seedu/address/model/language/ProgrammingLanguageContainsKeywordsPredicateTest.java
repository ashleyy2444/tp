package seedu.address.model.language;

import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import seedu.address.testutil.PersonBuilder;

public class ProgrammingLanguageContainsKeywordsPredicateTest {

    @Test
    public void equals() {
        List<ProgrammingLanguage> firstLanguages = Collections.singletonList(new ProgrammingLanguage("Java"));
        List<ProgrammingLanguage> secondLanguages = Collections.singletonList(new ProgrammingLanguage("C++"));

        ProgrammingLanguageContainsKeywordsPredicate firstPredicate =
                new ProgrammingLanguageContainsKeywordsPredicate(firstLanguages);
        ProgrammingLanguageContainsKeywordsPredicate secondPredicate =
                new ProgrammingLanguageContainsKeywordsPredicate(secondLanguages);

        // same object -> returns true
        Assertions.assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        ProgrammingLanguageContainsKeywordsPredicate firstPredicateCopy =
                new ProgrammingLanguageContainsKeywordsPredicate(firstLanguages);
        Assertions.assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        Assertions.assertFalse(firstPredicate.equals(1));

        // null -> returns false
        Assertions.assertFalse(firstPredicate.equals(null));

        // different person -> returns false
        Assertions.assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_languageContainsKeywords_returnsTrue() {
        // One keyword
        List<ProgrammingLanguage> languages = Collections.singletonList(new ProgrammingLanguage("Java"));
        ProgrammingLanguageContainsKeywordsPredicate predicate =
                new ProgrammingLanguageContainsKeywordsPredicate(languages);
        Assertions.assertTrue(predicate.test(new PersonBuilder().withProgrammingLanguages("Java", "C++").build()));

        // Multiple keywords
        languages = Collections.singletonList(new ProgrammingLanguage("C++"));
        predicate = new ProgrammingLanguageContainsKeywordsPredicate(languages);
        Assertions.assertTrue(predicate.test(new PersonBuilder().withProgrammingLanguages("Java", "C++").build()));

        // Only one matching keyword
        languages = Collections.singletonList(new ProgrammingLanguage("Java"));
        predicate = new ProgrammingLanguageContainsKeywordsPredicate(languages);
        Assertions.assertTrue(predicate.test(new PersonBuilder().withProgrammingLanguages("Java", "Python").build()));
    }

    @Test
    public void test_languageDoesNotContainKeywords_returnsFalse() {
        // Zero keywords
        List<ProgrammingLanguage> languages = Collections.emptyList();
        ProgrammingLanguageContainsKeywordsPredicate predicate =
                new ProgrammingLanguageContainsKeywordsPredicate(languages);
        Assertions.assertFalse(predicate.test(new PersonBuilder().withProgrammingLanguages("Java", "C++").build()));

        // Non-matching keyword
        languages = Collections.singletonList(new ProgrammingLanguage("Python"));
        predicate = new ProgrammingLanguageContainsKeywordsPredicate(languages);
        Assertions.assertFalse(predicate.test(new PersonBuilder().withProgrammingLanguages("Java", "C++").build()));
    }

    @SuppressWarnings("checkstyle:OperatorWrap")
    @Test
    public void toStringMethod() {
        List<ProgrammingLanguage> languages = Collections.singletonList(new ProgrammingLanguage("Java"));
        ProgrammingLanguageContainsKeywordsPredicate predicate =
                new ProgrammingLanguageContainsKeywordsPredicate(languages);
        String expected = ProgrammingLanguageContainsKeywordsPredicate.class.getCanonicalName()
                + "{programming_language=[[Java]]}";
        Assertions.assertEquals(expected, predicate.toString());
    }
}
