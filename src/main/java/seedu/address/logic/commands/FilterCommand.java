package seedu.address.logic.commands;

import static seedu.address.logic.parser.CliSyntax.PREFIX_INTERVIEWTIME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PROGRAMMING_LANGUAGE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SALARY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;


/**
 * Abstract class representing a FilterCommand for filtering contacts in an address book.
 * Subclasses will implement specific filter commands for different categories.
 */
public abstract class FilterCommand extends Command {

    public static final String COMMAND_WORD = "filter";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Filters the contact by various categories and "
            + "displays them as a list with index numbers.\n"
            + "Parameters:\n"
            + "- " + PREFIX_TAG + "TAG [MORE TAG]...\n"
            + "- " + PREFIX_SALARY + "SALARY_RANGE [MORE SALARY_RANGE]...\n"
            + "- " + PREFIX_INTERVIEWTIME + "INTERVIEW_TIME_RANGE [MORE INTERVIEW_TIME_RANGE]...\n"
            + "- " + PREFIX_PROGRAMMING_LANGUAGE
            + "PROGRAMMING_LANGUAGE [MORE PROGRAMMING_LANGUAGE]...\n"
            + "Example: " + COMMAND_WORD + " s/2000 5000-7000";
}
