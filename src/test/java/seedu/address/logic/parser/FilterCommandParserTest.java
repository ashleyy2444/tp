package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_NON_EMPTY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_SALARY_RANGE_MAX;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_FRIEND;
import static seedu.address.logic.parser.CliSyntax.PREFIX_INTERVIEWTIME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SALARY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.FilterCommand;
import seedu.address.logic.commands.FilterInterviewTimeCommand;
import seedu.address.logic.commands.FilterSalaryCommand;
import seedu.address.logic.commands.FilterTagCommand;
import seedu.address.model.person.InterviewTime;
import seedu.address.model.person.InterviewTimeContainsKeywordsPredicate;
import seedu.address.model.person.SalaryContainsKeywordsPredicate;
import seedu.address.model.person.SalaryRange;
import seedu.address.model.tag.Tag;
import seedu.address.model.tag.TagContainsKeywordsPredicate;

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
    public void parse_emptyArgs_throwsParseException() {
        String args = "";
        assert args.length() == 0;
        assertParseFailure(parser, args, String.format(MESSAGE_INVALID_COMMAND_FORMAT, FilterCommand.MESSAGE_USAGE));
        }

    @Test
    public void parse_validArgs_returnsFilterSalaryCommand() {
        FilterCommand expected =
                new FilterSalaryCommand(new SalaryContainsKeywordsPredicate(Arrays.asList(new SalaryRange("2000"),
                        new SalaryRange("5000-7000"))));
        assertParseSuccess(parser, " " + PREFIX_SALARY + "2000 5000-7000", expected);
    }

    @Test
    public void parse_validArgs_returnsFilterTagCommand() {
        FilterCommand expected =
                new FilterTagCommand(new TagContainsKeywordsPredicate(Arrays.asList(new Tag("friends"),
                        new Tag("owesMoney"))));
        assertParseSuccess(parser, " " + PREFIX_TAG + "friends owesMoney", expected);
    }

    @Test
    public void parse_validArgs_returnsFilterInterviewTimeCommand() {
        FilterCommand expected =
                new FilterInterviewTimeCommand(new InterviewTimeContainsKeywordsPredicate(
                        Arrays.asList(
                        Arrays.asList(null, new InterviewTime("020220220900")),
                        Arrays.asList(new InterviewTime("020520252200"), null),
                        Arrays.asList(new InterviewTime("050520220800"),
                                new InterviewTime("050520231800"))
                        )));
        assertParseSuccess(parser, " " + PREFIX_INTERVIEWTIME + "before/020220220900 after/020520252200 "
                + "from/050520220800-050520231800", expected);
    }
}
