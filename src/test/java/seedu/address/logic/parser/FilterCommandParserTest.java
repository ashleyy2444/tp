package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_NON_EMPTY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_SALARY_RANGE_MAX;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_FRIEND;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SALARY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.FilterCommand;
import seedu.address.logic.commands.FilterSalaryCommand;
import seedu.address.model.person.SalaryContainsKeywordsPredicate;
import seedu.address.model.person.SalaryRange;

public class FilterCommandParserTest {
    private FilterCommandParser parser = new FilterCommandParser();

    @Test
    public void parse_moreThanOnePrefix_throwsParseException() {
        assertParseFailure(parser, " " + PREFIX_SALARY + VALID_SALARY_RANGE_MAX + " " + PREFIX_TAG + VALID_TAG_FRIEND,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                FilterCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_nonEmptyPreamble_throwsParseException() {
        assertParseFailure(parser, PREAMBLE_NON_EMPTY + " " + PREFIX_SALARY + VALID_SALARY_RANGE_MAX,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                        FilterCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validArgs_returnsFilterSalaryCommand() {
        FilterCommand expected =
                new FilterSalaryCommand(new SalaryContainsKeywordsPredicate(Arrays.asList(new SalaryRange("2000"),
                        new SalaryRange("5000-7000"))));
        assertParseSuccess(parser, " " + PREFIX_SALARY + "2000 5000-7000", expected);
    }

}
