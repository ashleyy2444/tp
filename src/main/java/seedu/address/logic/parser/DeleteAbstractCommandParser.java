package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.stream.Stream;

import seedu.address.logic.commands.DeleteAbstractCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new DeleteAbstractCommand object
 */
public class DeleteAbstractCommandParser implements Parser<DeleteAbstractCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the DeleteAbstractCommand
     * and returns a DeleteAbstractCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public DeleteAbstractCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_TAG);

        if (!exactlyOnePrefixPresent(argMultimap, PREFIX_TAG)) {
            System.out.println("1");
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    DeleteAbstractCommand.MESSAGE_USAGE));
        }

        if (argMultimap.getSize() == 1) {
            return new DeleteCommandParser().parse(args);
        } else if (!argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    DeleteAbstractCommand.MESSAGE_USAGE));
        }

        argMultimap.verifyNoDuplicatePrefixesFor(PREFIX_TAG);

        if (argMultimap.getValue(PREFIX_TAG).isPresent()) {
            return new DeleteTagCommandParser().parse(argMultimap.getValue(PREFIX_TAG).get());
        } else {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    DeleteAbstractCommand.MESSAGE_USAGE));
        }
    }

    /**
     * Returns true if exactly one of the prefixes contains a non-empty {@code Optional} value in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean exactlyOnePrefixPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        long count = Stream.of(prefixes)
                .filter(prefix -> argumentMultimap.getValue(prefix).isPresent())
                .count();
        return count <= 1;
    }
}

