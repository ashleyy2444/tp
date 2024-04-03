package seedu.address.model.person;

import static java.util.Objects.requireNonNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.model.language.ProgrammingLanguage;

/**
 * Tests that a {@code Person}'s {@code Tag} matches any of the keywords given.
 */
public class ProgrammingLanguageContainsKeywordsPredicate implements Predicate<Person> {
    private final List<ProgrammingLanguage> originalLanguages;
    private final List<String> lowercaseLanguageNames;
    /**
     * Constructs a {@code ProgrammingLanguageContainsKeywordsPredicate} with the given list of programming languages.
     * Creates two lists: one to store the original ProgrammingLanguage objects and another to store their lowercase
     * string representations.
     *
     * @param languages The list of programming languages to be used in the predicate.
     * @throws NullPointerException if {@code languages} is null.
     */
    public ProgrammingLanguageContainsKeywordsPredicate(List<ProgrammingLanguage> languages) {
        requireNonNull(languages);
        this.originalLanguages = new ArrayList<>(languages);
        this.lowercaseLanguageNames = languages.stream()
                .map(ProgrammingLanguage::getLanguageName)
                .map(String::toLowerCase)
                .collect(Collectors.toList());
    }

    @Override
    public boolean test(Person person) {
        Set<ProgrammingLanguage> personProgrammingLanguages = person.getProgrammingLanguages();
        return personProgrammingLanguages.stream()
                .map(ProgrammingLanguage::getLanguageName)
                .map(String::toLowerCase)
                .anyMatch(lowercaseLanguageNames::contains);
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
        return originalLanguages.equals(otherProgrammingLanguageContainsKeywordsPredicate.originalLanguages);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).add("programming_language", originalLanguages).toString();
    }
}
