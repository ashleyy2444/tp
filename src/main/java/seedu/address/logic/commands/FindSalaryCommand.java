package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.model.Model;
import seedu.address.model.person.SalaryContainsKeywordsPredicate;



/**
 * Finds and lists all persons in address book whose name contains any of the argument keywords.
 * Keyword matching is case insensitive.
 */
public class FindSalaryCommand extends Command {

    public static final String COMMAND_WORD = "finds";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all companies whose salary range contain any of "
            + "the specified salary (case-insensitive) and displays them as a list with index numbers.\n"
            + "Parameters: SALARY_RANGE\n"
            + "Example: " + COMMAND_WORD + " 5000-6000";

    private final SalaryContainsKeywordsPredicate predicate;

    public FindSalaryCommand(SalaryContainsKeywordsPredicate predicate) {
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
        if (!(other instanceof FindSalaryCommand)) {
            return false;
        }

        FindSalaryCommand otherFindCommand = (FindSalaryCommand) other;
        return predicate.equals(otherFindCommand.predicate);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("predicate", predicate)
                .toString();
    }
}
