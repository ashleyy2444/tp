package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.logic.commands.FilterInterviewTimeCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.InterviewTime;
import seedu.address.model.person.InterviewTimeContainsKeywordsPredicate;


/**
 * Parses input arguments and creates a new FilterInterviewCommand object
 */
public class FilterInterviewTimeCommandParser {
    private static final Prefix PREFIX_BEFORE_INTERVIEW_TIME = new Prefix("before/");
    private static final Prefix PREFIX_AFTER_INTERVIEW_TIME = new Prefix("after/");
    private static final Prefix PREFIX_FROM_INTERVIEW_TIME = new Prefix("from/");
    private static final String VALIDATION_REGEX_FROM_PREFIX = "^[^-]*-[^-]*$";

    /**
     * Parses the given {@code String} of arguments in the context of the FilterInterviewTimeCommand
     * and returns a FilterInterviewTimeCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public FilterInterviewTimeCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, FilterInterviewTimeCommand.MESSAGE_USAGE));
        }
        trimmedArgs = " " + trimmedArgs;
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(
                        trimmedArgs, PREFIX_BEFORE_INTERVIEW_TIME, PREFIX_AFTER_INTERVIEW_TIME,
                        PREFIX_FROM_INTERVIEW_TIME);
        if (!argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    FilterInterviewTimeCommand.MESSAGE_USAGE));
        }
        List<List<InterviewTime>> interviewTimes = new ArrayList<>();
        if (argMultimap.getValue(PREFIX_BEFORE_INTERVIEW_TIME).isPresent()) {
            interviewTimes.addAll(handleBeforeInterviewTime(argMultimap));
        }
        if (argMultimap.getValue(PREFIX_AFTER_INTERVIEW_TIME).isPresent()) {
            interviewTimes.addAll(handleAfterInterviewTime(argMultimap));
        }
        if (argMultimap.getValue(PREFIX_FROM_INTERVIEW_TIME).isPresent()) {
            interviewTimes.addAll(handleFromInterviewTime(argMultimap));
        }
        return new FilterInterviewTimeCommand(new InterviewTimeContainsKeywordsPredicate(interviewTimes));
    }
    private static List<List<InterviewTime>> handleFromInterviewTime(ArgumentMultimap argMultimap)
            throws ParseException {
        List<String> rangeInterviewTimes = argMultimap.getAllValues(PREFIX_FROM_INTERVIEW_TIME);
        List<List<InterviewTime>> interviewTimes = new ArrayList<>();
        for (String rangeInterviewTime : rangeInterviewTimes) {
            if (!rangeInterviewTime.matches(VALIDATION_REGEX_FROM_PREFIX)) {
                throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                        FilterInterviewTimeCommand.WRONG_INTERVIEW_TIME_RANGE_MESSAGE));
            } else {
                String[] range = rangeInterviewTime.split("-");
                List<InterviewTime> rangeTimes = new ArrayList<>();
                for (String interviewTime : range) {
                    rangeTimes.add(ParserUtil.parseInterviewTime(interviewTime));
                }
                interviewTimes.add(rangeTimes);
            }
        }
        return interviewTimes;
    }
    private static List<List<InterviewTime>> handleBeforeInterviewTime(ArgumentMultimap argMultimap)
            throws ParseException {
        List<String> beforeInterviewTimes = argMultimap.getAllValues(PREFIX_BEFORE_INTERVIEW_TIME);
        List<List<InterviewTime>> interviewTimes = new ArrayList<>();
        for (String beforeInterviewTime : beforeInterviewTimes) {
            interviewTimes.add(Arrays.asList(null, ParserUtil.parseInterviewTime(beforeInterviewTime)));
        }
        return interviewTimes;
    }
    private static List<List<InterviewTime>> handleAfterInterviewTime(ArgumentMultimap argMultimap)
            throws ParseException {
        List<String> afterInterviewTimes = argMultimap.getAllValues(PREFIX_AFTER_INTERVIEW_TIME);
        List<List<InterviewTime>> interviewTimes = new ArrayList<>();
        for (String afterInterviewTime : afterInterviewTimes) {
            interviewTimes.add(Arrays.asList(ParserUtil.parseInterviewTime(afterInterviewTime), null));
        }
        return interviewTimes;
    }

}
