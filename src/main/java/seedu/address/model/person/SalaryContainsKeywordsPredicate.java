package seedu.address.model.person;

import java.util.List;
import java.util.function.Predicate;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.parser.exceptions.ParseException;


/**
 * Tests that a {@code Person}'s {@code Name} matches any of the keywords given.
 */
public class SalaryContainsKeywordsPredicate implements Predicate<Person> {
    private final List<Salary> keywords;

    public SalaryContainsKeywordsPredicate(List<Salary> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Person person) {
        return keywords.stream()
                .anyMatch(salary -> person.getSalary().isWithinSalaryRange(salary));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof SalaryContainsKeywordsPredicate)) {
            return false;
        }

        SalaryContainsKeywordsPredicate otherSalaryContainsKeywordsPredicate = (SalaryContainsKeywordsPredicate) other;
        return keywords.equals(otherSalaryContainsKeywordsPredicate.keywords);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).add("salaries", keywords).toString();
    }
}
