package seedu.address.model.person;

import java.util.List;
import java.util.Set;
import java.util.function.Predicate;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.model.language.ProgrammingLanguage;

/**
 * Tests that a {@code Person}'s {@code Tag} matches any of the keywords given.
 */
public class ProgrammingLanguageContainsKeywordsPredicate implements Predicate<Person> {
    private final List<ProgrammingLanguage> languages;

    public ProgrammingLanguageContainsKeywordsPredicate(List<ProgrammingLanguage> languages) {
        this.languages = languages;
    }

    @Override
    public boolean test(Person person) {
        Set<ProgrammingLanguage> personProgrammingLanguages = person.getProgrammingLanguages();
        return languages.stream()
                .anyMatch(personProgrammingLanguages::contains);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof ProgrammingLanguageContainsKeywordsPredicate)) {
            return false;
        }

        ProgrammingLanguageContainsKeywordsPredicate otherProgrammingLanguageContainsKeywordsPredicate =
                (ProgrammingLanguageContainsKeywordsPredicate) other;
        return languages.equals(otherProgrammingLanguageContainsKeywordsPredicate.languages);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).add("programming_language", languages).toString();
    }
}
