package seedu.address.model.person;

import java.util.List;
import java.util.function.Predicate;

import seedu.address.commons.util.ToStringBuilder;



/**
 * Tests that a {@code Person}'s {@code Name} matches any of the keywords given.
 */
public class InterviewTimeContainsKeywordsPredicate implements Predicate<Person> {
    private final List<List<InterviewTime>> keywords;

    public InterviewTimeContainsKeywordsPredicate(List<List<InterviewTime>> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Person person) {
        return keywords.stream()
                .anyMatch(interviewTimeRange -> person.getDateTime().isWithinInterviewTimeRange(interviewTimeRange));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof InterviewTimeContainsKeywordsPredicate)) {
            return false;
        }

        InterviewTimeContainsKeywordsPredicate otherSalaryContainsKeywordsPredicate =
                (InterviewTimeContainsKeywordsPredicate) other;
        return keywords.equals(otherSalaryContainsKeywordsPredicate.keywords);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("interviewTimes", keywords).toString();
    }
}
