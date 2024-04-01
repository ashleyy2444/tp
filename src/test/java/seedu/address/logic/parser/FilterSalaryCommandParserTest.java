package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.FilterSalaryCommand;
import seedu.address.model.person.SalaryContainsKeywordsPredicate;
import seedu.address.model.person.SalaryRange;


public class FilterSalaryCommandParserTest {

    private FilterSalaryCommandParser parser = new FilterSalaryCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                FilterSalaryCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validArgs_returnsFilterSalaryCommand() {
        // no leading and trailing whitespaces
        FilterSalaryCommand expectedFilterSalaryCommand =
                new FilterSalaryCommand(new SalaryContainsKeywordsPredicate(Arrays.asList(new SalaryRange("2000"),
                        new SalaryRange("4000-9000"))));
        assertParseSuccess(parser, "2000 4000-9000", expectedFilterSalaryCommand);

        // multiple whitespaces between keywords
        assertParseSuccess(parser, " \n 2000 \n \t 4000-9000  \t", expectedFilterSalaryCommand);
    }

}
