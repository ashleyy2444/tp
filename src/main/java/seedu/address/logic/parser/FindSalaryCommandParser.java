package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.Arrays;

import seedu.address.logic.commands.FindSalaryCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.SalaryContainsKeywordsPredicate;


/**
 * Parses input arguments and creates a new FindSalaryCommand object
 */
public class FindSalaryCommandParser implements Parser<FindSalaryCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the FindCommand
     * and returns a FindCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public FindSalaryCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindSalaryCommand.MESSAGE_USAGE));
        }

        String[] nameKeywords = trimmedArgs.split("\\s+");

        return new FindSalaryCommand(new SalaryContainsKeywordsPredicate(Arrays.asList(nameKeywords)));
    }

}
