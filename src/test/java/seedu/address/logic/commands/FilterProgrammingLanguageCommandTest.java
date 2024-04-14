package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.Messages.MESSAGE_PERSONS_LISTED_OVERVIEW;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.parser.FilterProgrammingLanguageCommandParser.createLanguages;
import static seedu.address.logic.parser.FilterTagCommandParser.createTags;
import static seedu.address.testutil.TypicalPersons.*;
import static seedu.address.testutil.TypicalPersons.DANIEL;

import java.util.Arrays;
import java.util.Collections;

import org.junit.jupiter.api.Test;

import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.language.ProgrammingLanguageContainsKeywordsPredicate;
import seedu.address.model.tag.TagContainsKeywordsPredicate;

/**
 * Contains integration tests (interaction with the Model) for {@code FilterProgrammingLanguageCommand}.
 */
public class FilterProgrammingLanguageCommandTest {
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void equals() throws ParseException {
        ProgrammingLanguageContainsKeywordsPredicate firstPredicate =
                new ProgrammingLanguageContainsKeywordsPredicate(createLanguages("Java"));
        ProgrammingLanguageContainsKeywordsPredicate secondPredicate =
                new ProgrammingLanguageContainsKeywordsPredicate(createLanguages("Python"));

        FilterProgrammingLanguageCommand filterProgrammingLanguageFirstCommand =
                new FilterProgrammingLanguageCommand(firstPredicate);
        FilterProgrammingLanguageCommand filterProgrammingLanguageSecondCommand =
                new FilterProgrammingLanguageCommand(secondPredicate);

        // same object -> returns true
        assertTrue(filterProgrammingLanguageFirstCommand.equals(filterProgrammingLanguageFirstCommand));

        // same values -> returns true
        FilterProgrammingLanguageCommand filterProgrammingLanguageFirstCommandCopy =
                new FilterProgrammingLanguageCommand(firstPredicate);
        assertTrue(filterProgrammingLanguageFirstCommand.equals(filterProgrammingLanguageFirstCommandCopy));

        // different types -> returns false
        assertFalse(filterProgrammingLanguageFirstCommand.equals(1));

        // null -> returns false
        assertFalse(filterProgrammingLanguageFirstCommand.equals(null));

        // different person -> returns false
        assertFalse(filterProgrammingLanguageFirstCommand.equals(filterProgrammingLanguageSecondCommand));
    }

    @Test
    public void execute_zeroKeywords_noPersonFound() throws ParseException {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 0);
        ProgrammingLanguageContainsKeywordsPredicate predicate =
                preparePredicate(" ");
        FilterProgrammingLanguageCommand command = new FilterProgrammingLanguageCommand(predicate);
        expectedModel.updateFilteredPersonList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredPersonList());
    }

    @Test
    public void execute_multipleKeywords_multiplePersonsFound() throws ParseException {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 4);
        ProgrammingLanguageContainsKeywordsPredicate predicate = preparePredicate("Java java");
        FilterProgrammingLanguageCommand command = new FilterProgrammingLanguageCommand(predicate);
        expectedModel.updateFilteredPersonList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(ALICE, BENSON, CARL, DANIEL), model.getFilteredPersonList());
    }

    @Test
    public void toStringMethod() throws ParseException {
        ProgrammingLanguageContainsKeywordsPredicate predicate = new ProgrammingLanguageContainsKeywordsPredicate(createLanguages("Java"));
        FilterProgrammingLanguageCommand filterProgrammingLanguageCommand = new FilterProgrammingLanguageCommand(predicate);
        String expected = FilterProgrammingLanguageCommand.class.getCanonicalName() + "{programming_language=" + predicate + "}";
        assertEquals(expected, filterProgrammingLanguageCommand.toString());
    }

    /**
     * Parses {@code userInput} into a {@code ProgrammingLanguageContainsKeywordsPredicate}.
     */
    private ProgrammingLanguageContainsKeywordsPredicate preparePredicate(String userInput) throws ParseException {
        return new ProgrammingLanguageContainsKeywordsPredicate(createLanguages(userInput.split("\\s+")));
    }
}
