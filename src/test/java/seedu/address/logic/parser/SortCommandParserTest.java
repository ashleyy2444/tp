package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.logic.parser.SortCommandParser.MESSAGE_INVALID_INVERSE_COMMAND_KEYWORD;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.SortCommand;

public class SortCommandParserTest {

    private SortCommandParser parser = new SortCommandParser();

    @Test
    public void parse_validPrefix_returnsSortCommand() {
        assertParseSuccess(parser, "pri/", new SortCommand(0, false));
        assertParseSuccess(parser, "cn/", new SortCommand(1, false));
        assertParseSuccess(parser, "n/", new SortCommand(2, false));
        assertParseSuccess(parser, "tt/", new SortCommand(3, false));
        assertParseSuccess(parser, "s/", new SortCommand(4, false));
        assertParseSuccess(parser, "jd/", new SortCommand(5, false));
        assertParseSuccess(parser, "pri/", new SortCommand(0, true));
        assertParseSuccess(parser, "cn/", new SortCommand(1, true));
        assertParseSuccess(parser, "n/", new SortCommand(2, true));
        assertParseSuccess(parser, "tt/", new SortCommand(3, true));
        assertParseSuccess(parser, "s/", new SortCommand(4, true));
        assertParseSuccess(parser, "jd/", new SortCommand(5, true));
    }

    @Test
    public void parse_invalidPrefix_throwsParseException() {
        assertParseFailure(parser, "a", "Invalid command keyword for sort command");
    }

    @Test
    public void parse_emptyInput_throwsParseException() {
        assertParseFailure(parser, "", String.format(
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, SortCommand.MESSAGE_USAGE)));
    }

    @Test
    public void parse_multipleArguments_throwsParseException() {
        assertParseFailure(
                parser, "pri/ com/", String.format(MESSAGE_INVALID_INVERSE_COMMAND_KEYWORD,
                        SortCommand.MESSAGE_USAGE));
    }
}
