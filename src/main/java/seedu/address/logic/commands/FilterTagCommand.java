package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.model.Model;
import seedu.address.model.person.TagContainsKeywordsPredicate;


/**
 * Filters and lists all persons in address book whose tags contains any of the argument keywords.
 */
public class FilterTagCommand extends FilterCommand {

    public static final String COMMAND_WORD = FilterCommand.COMMAND_WORD + " " + PREFIX_TAG;

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Filters all contacts whose tags contain any of "
            + "the specified tags and displays them as a list with index numbers.\n"
            + "Parameters: TAG [MORE_TAGS]...\n"
            + "Example: " + COMMAND_WORD + " owes money";

    private final TagContainsKeywordsPredicate predicate;

    public FilterTagCommand(TagContainsKeywordsPredicate predicate) {
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
        if (!(other instanceof FilterTagCommand)) {
            return false;
        }

        FilterTagCommand otherFindCommand = (FilterTagCommand) other;
        return predicate.equals(otherFindCommand.predicate);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("tag", predicate)
                .toString();
    }
}
