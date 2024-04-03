package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PROGRAMMING_LANGUAGE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SALARY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.stream.Stream;

import seedu.address.logic.commands.FilterCommand;
import seedu.address.logic.parser.exceptions.ParseException;


/**
 * Parses input arguments and creates a new FilterCommand object
 */
public class FilterCommandParser implements Parser<FilterCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the FilterCommand
     * and returns a FilterCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public FilterCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(
                        args, PREFIX_SALARY, PREFIX_TAG, PREFIX_PROGRAMMING_LANGUAGE);
        if (!onlyOnePrefixPresent(argMultimap, PREFIX_SALARY, PREFIX_TAG, PREFIX_PROGRAMMING_LANGUAGE)
            || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, FilterCommand.MESSAGE_USAGE));
        }

        argMultimap.verifyNoDuplicatePrefixesFor(PREFIX_SALARY, PREFIX_TAG, PREFIX_PROGRAMMING_LANGUAGE);

        if (argMultimap.getValue(PREFIX_SALARY).isPresent()) {
            return new FilterSalaryCommandParser().parse(argMultimap.getValue(PREFIX_SALARY).get());
        } else if (argMultimap.getValue(PREFIX_TAG).isPresent()) {
            return new FilterTagCommandParser().parse(argMultimap.getValue(PREFIX_TAG).get());
        } else if (argMultimap.getValue(PREFIX_PROGRAMMING_LANGUAGE).isPresent()) {
            return new FilterProgrammingLanguageCommandParser().parse(argMultimap.getValue(PREFIX_PROGRAMMING_LANGUAGE)
                    .get());
        } else {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, FilterCommand.MESSAGE_USAGE));
        }
    }

    /**
     * Returns true if at least one of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean onlyOnePrefixPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        long count = Stream.of(prefixes)
                .filter(prefix -> argumentMultimap.getValue(prefix).isPresent())
                .count();
        return count == 1;
    }

}

