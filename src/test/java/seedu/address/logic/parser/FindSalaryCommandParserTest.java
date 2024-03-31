package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.FindSalaryCommand;
import seedu.address.model.person.Salary;
import seedu.address.model.person.SalaryContainsKeywordsPredicate;


public class FindSalaryCommandParserTest {

    private FindSalaryCommandParser parser = new FindSalaryCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                FindSalaryCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validArgs_returnsFindSalaryCommand() {
        // no leading and trailing whitespaces
        FindSalaryCommand expectedFindSalaryCommand =
                new FindSalaryCommand(new SalaryContainsKeywordsPredicate(Arrays.asList(new Salary("2000"),
                        new Salary("4000-9000"))));
        assertParseSuccess(parser, "2000 4000-9000", expectedFindSalaryCommand);

        // multiple whitespaces between keywords
        assertParseSuccess(parser, " \n 2000 \n \t 4000-9000  \t", expectedFindSalaryCommand);
    }

}
