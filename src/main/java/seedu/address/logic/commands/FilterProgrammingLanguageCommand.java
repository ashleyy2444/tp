package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PROGRAMMING_LANGUAGE;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.model.Model;
import seedu.address.model.person.ProgrammingLanguageContainsKeywordsPredicate;

/**
 * Filters and lists all persons in address book whose programming language contains any of the argument keywords.
 */
public class FilterProgrammingLanguageCommand extends FilterCommand {

    public static final String COMMAND_WORD = FilterCommand.COMMAND_WORD + " " + PREFIX_PROGRAMMING_LANGUAGE;

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Filters all contacts whose programming language "
            + "contain any of the specified tags and displays them as a list with index numbers.\n"
            + "Parameters: PROGRAMMING_LANGUAGE [MORE_PROGRAMMING_LANGUAGES]...\n"
            + "Example: " + COMMAND_WORD + " Java";

    private final ProgrammingLanguageContainsKeywordsPredicate predicate;

    public FilterProgrammingLanguageCommand(ProgrammingLanguageContainsKeywordsPredicate predicate) {
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
        if (!(other instanceof FilterProgrammingLanguageCommand)) {
            return false;
        }

        FilterProgrammingLanguageCommand otherFindCommand = (FilterProgrammingLanguageCommand) other;
        return predicate.equals(otherFindCommand.predicate);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("programming_language", predicate)
                .toString();
    }
}
