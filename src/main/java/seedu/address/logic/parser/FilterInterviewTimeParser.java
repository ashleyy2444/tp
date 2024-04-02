package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.logic.commands.FilterInterviewTimeCommand;
import seedu.address.logic.parser.exceptions.ParseException;


/**
 * Parses input arguments and creates a new FilterInterviewCommand object
 */
public class FilterInterviewTimeParser {
    private static final Prefix BEFORE_INTERVIEW_TIME = new Prefix("before/");
    private static final Prefix AFTER_INTERVIEW_TIME = new Prefix("after/");
    private static final Prefix FROM_INTERVIEW_TIME = new Prefix("from/");
    private static final Prefix TO_INTERVIEW_TIME = new Prefix("to/");




    public FilterInterviewTimeCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, FilterInterviewTimeCommand.MESSAGE_USAGE));
        }

//        return new FilterSalaryCommand(new SalaryContainsKeywordsPredicate(Arrays.asList(salaries)));
    }
}
