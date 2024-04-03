package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.ArrayList;
import java.util.List;

import seedu.address.logic.commands.FilterProgrammingLanguageCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.language.ProgrammingLanguage;
import seedu.address.model.person.ProgrammingLanguageContainsKeywordsPredicate;

/**
 * Parses input arguments and creates a new FindCommand object
 */
public class FilterProgrammingLanguageCommandParser implements Parser<FilterProgrammingLanguageCommand> {


    /**
     * Parses the given {@code String} of arguments in the context of the FilterProgrammingLanguageCommand
     * and returns a FilterProgrammingLanguageCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    /*public FilterProgrammingLanguageCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, FilterProgrammingLanguageCommand.MESSAGE_USAGE));
        }
        String[] languageKeywords = trimmedArgs.split("\\s+");
        return new FilterProgrammingLanguageCommand(
                new ProgrammingLanguageContainsKeywordsPredicate(createLanguages(languageKeywords)));
    }*/

    /**
     * Parses {@code languageKeywords} into a {@code List<ProgrammingLanguage>}.
     */
    /*public static List<ProgrammingLanguage> createLanguages(String... languageKeywords) throws ParseException {
        List<ProgrammingLanguage> languages = new ArrayList<>();
        for (String keyword : languageKeywords) {
            languages.add(ParserUtil.parseProgrammingLanguage(keyword));
        }
        return languages;
    }*/

    /**
     * Parses the given {@code String} of arguments in the context of the FilterProgrammingLanguageCommand
     * and returns a FilterProgrammingLanguageCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public FilterProgrammingLanguageCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, FilterProgrammingLanguageCommand.MESSAGE_USAGE));
        }
        String[] languageKeywords = trimmedArgs.split("\\s+");
        return new FilterProgrammingLanguageCommand(
                new ProgrammingLanguageContainsKeywordsPredicate(createLanguages(languageKeywords)));
    }

    /**
     * Parses {@code languageKeywords} into a {@code List<ProgrammingLanguage>}.
     */
    public static List<ProgrammingLanguage> createLanguages(String... languageKeywords) throws ParseException {
        List<ProgrammingLanguage> languages = new ArrayList<>();
        for (String keyword : languageKeywords) {
            languages.add(ParserUtil.parseProgrammingLanguage(keyword.toLowerCase())); // Convert to lowercase
        }
        return languages;
    }
}
