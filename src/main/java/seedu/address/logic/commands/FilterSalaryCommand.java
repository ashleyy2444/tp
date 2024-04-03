package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SALARY;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.model.Model;
import seedu.address.model.person.SalaryContainsKeywordsPredicate;



/**
 * Filters and lists all contacts in address book whose Salary is within the salary range provided.
 */
public class FilterSalaryCommand extends FilterCommand {
    public static final String COMMAND_WORD = FilterCommand.COMMAND_WORD + " " + PREFIX_SALARY;
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Filters all companies whose "
            + "salary range contain any of "
            + "the specified salary and displays them as a list with index numbers.\n"
            + "Parameters: SALARY_RANGE [MORE_SALARY_RANGE]...\n"
            + "Example: " + COMMAND_WORD + "5000-6000 >=8000";

    private final SalaryContainsKeywordsPredicate predicate;

    public FilterSalaryCommand(SalaryContainsKeywordsPredicate predicate) {
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredPersonList(predicate);
        return new CommandResult(
                String.format(Messages.MESSAGE_PERSONS_LISTED_OVERVIEW, model.getFilteredPersonList().size()));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof FilterSalaryCommand)) {
            return false;
        }

        FilterSalaryCommand otherFindCommand = (FilterSalaryCommand) other;
        return predicate.equals(otherFindCommand.predicate);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("salary", predicate)
                .toString();
    }
}
