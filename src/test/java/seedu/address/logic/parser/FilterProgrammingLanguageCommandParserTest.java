package seedu.address.logic.parser;

import org.junit.jupiter.api.Test;
import seedu.address.logic.commands.FilterProgrammingLanguageCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.language.ProgrammingLanguageContainsKeywordsPredicate;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.logic.parser.FilterProgrammingLanguageCommandParser.createLanguages;

public class FilterProgrammingLanguageCommandParserTest {
    private FilterProgrammingLanguageCommandParser parser = new FilterProgrammingLanguageCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                FilterProgrammingLanguageCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validArgs_returnsFilterTagCommand() throws ParseException {
        // no leading and trailing whitespaces
        FilterProgrammingLanguageCommand expectedFilterProgrammingLanguageCommand =
                new FilterProgrammingLanguageCommand(new ProgrammingLanguageContainsKeywordsPredicate(
                        createLanguages("Java", "Python")));
        assertParseSuccess(parser, "Java Python", expectedFilterProgrammingLanguageCommand);

        // multiple whitespaces between keywords
        assertParseSuccess(parser, " \n Java \n \t Python  \t", expectedFilterProgrammingLanguageCommand);
    }
}
