package seedu.address.logic.commands;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.model.Model;
import seedu.address.model.person.SalaryContainsKeywordsPredicate;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.*;


/**
 * Finds and lists all persons in address book whose name contains any of the argument keywords.
 * Keyword matching is case insensitive.
 */
public abstract class FilterCommand extends Command {

    public static final String COMMAND_WORD = "filter";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Filters the contact by various categories and "
            + "displays them as a list with index numbers.\n"
            + "Parameters:\n"
            + "- filter " + PREFIX_TAG + "TAG...\n"
            + "- filter " + PREFIX_SALARY + "SALARY...\n"
            + "- filter " + PREFIX_INTERVIEWTIME + "INTERVIEW_TIME...\n"
            + "- filter " + PREFIX_PROGRAMMING_LANGUAGE + "PROGRAMMING_LANGUAGE...\n"
            + "Example: " + COMMAND_WORD + " s/2000 5000-7000";

//    private final Command specificFilterCommand;

//    public FilterCommand(Command specificFilterCommand) {
//        this.specificFilterCommand = specificFilterCommand;
//    }

//    @Override
//    public CommandResult execute(Model model) {
//        requireNonNull(model);
//        model.updateFilteredPersonList(predicate);
//        return new CommandResult(
//                String.format(Messages.MESSAGE_PERSONS_LISTED_OVERVIEW, model.getFilteredPersonList().size()));
//    }

//    @Override
//    public boolean equals(Object other) {
//        if (other == this) {
//            return true;
//        }
//
//        // instanceof handles nulls
//        if (!(other instanceof FilterCommand)) {
//            return false;
//        }
//
//        FilterCommand otherFindCommand = (FilterCommand) other;
//        return predicate.equals(otherFindCommand.predicate);
//    }
//
//    @Override
//    public String toString() {
//        return new ToStringBuilder(this)
//                .add("salary", predicate)
//                .toString();
//    }
}
