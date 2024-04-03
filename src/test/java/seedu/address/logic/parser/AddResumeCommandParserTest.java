package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;

import org.junit.jupiter.api.Test;

import seedu.address.logic.Messages;
import seedu.address.logic.commands.AddResumeCommand;

public class AddResumeCommandParserTest {
    private AddResumeCommandParser parser = new AddResumeCommandParser();

    @Test
    public void parse_requiredFieldsMissing_throwsParseException() {
        assertParseFailure(parser, " ", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                AddResumeCommand.MESSAGE_USAGE));
    }
    @Test
    public void parse_duplicatePrefixes_failure() {
        assertParseFailure(parser, " n/Amy" + " n/Bob" + " a/test" + " p/123" + " e/test",
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_NAME));
    }
}
