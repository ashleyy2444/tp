package seedu.address.model.tag;

import java.util.List;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import seedu.address.model.person.Person;

/**
 * Tests that a {@code Person}'s {@code Tag} matches any of the keywords given.
 */
public class TagContainsKeywordsPredicate implements Predicate<Person> {
    private final List<Tag> tags;

    public TagContainsKeywordsPredicate(List<Tag> tags) {
        this.tags = tags;
    }

    @Override
    public boolean test(Person person) {
        Set<Tag> personTags = person.getTags();
        return tags.stream()
                .anyMatch(personTags::contains);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof TagContainsKeywordsPredicate)) {
            return false;
        }

        TagContainsKeywordsPredicate otherTagContainsKeywordsPredicate = (TagContainsKeywordsPredicate) other;
        return tags.equals(otherTagContainsKeywordsPredicate.tags);
    }

    @Override
    public String toString() {
        List<String> tagNames = tags.stream()
                .map(Tag::toString)
                .collect(Collectors.toList());

        return String.join(", ", tagNames);
    }
}
