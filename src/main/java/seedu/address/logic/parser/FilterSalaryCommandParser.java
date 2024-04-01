package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.Arrays;

import seedu.address.logic.commands.FilterSalaryCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Salary;
import seedu.address.model.person.SalaryContainsKeywordsPredicate;
import seedu.address.model.person.SalaryRange;


/**
 * Parses input arguments and creates a new FindSalaryCommand object
 */
public class FilterSalaryCommandParser implements Parser<FilterSalaryCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the FindCommand
     * and returns a FindCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public FilterSalaryCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, FilterSalaryCommand.MESSAGE_USAGE));
        }

        String[] salaryKeywords = trimmedArgs.split("\\s+");
        SalaryRange[] salaries = new SalaryRange[salaryKeywords.length];
        for (int i = 0; i < salaryKeywords.length; i++) {
            salaries[i] = ParserUtil.parseSalaryRange(salaryKeywords[i]);
        }
        return new FilterSalaryCommand(new SalaryContainsKeywordsPredicate(Arrays.asList(salaries)));
    }

}
