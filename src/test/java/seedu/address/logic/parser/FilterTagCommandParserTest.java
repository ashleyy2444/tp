package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.logic.parser.FilterTagCommandParser.createTags;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.FilterTagCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.TagContainsKeywordsPredicate;


public class FilterTagCommandParserTest {

    private FilterTagCommandParser parser = new FilterTagCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                FilterTagCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validArgs_returnsFilterTagCommand() throws ParseException {
        // no leading and trailing whitespaces
        FilterTagCommand expectedFilterTagCommand =
                new FilterTagCommand(new TagContainsKeywordsPredicate(createTags("Alice", "Bob")));
        assertParseSuccess(parser, "Alice Bob", expectedFilterTagCommand);

        // multiple whitespaces between keywords
        assertParseSuccess(parser, " \n Alice \n \t Bob  \t", expectedFilterTagCommand);
    }
}
