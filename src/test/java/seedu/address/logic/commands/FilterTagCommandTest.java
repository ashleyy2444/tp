package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.Messages.MESSAGE_PERSONS_LISTED_OVERVIEW;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.parser.FilterTagCommandParser.createTags;
import static seedu.address.testutil.TypicalPersons.ALICE;
import static seedu.address.testutil.TypicalPersons.BENSON;
import static seedu.address.testutil.TypicalPersons.DANIEL;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import java.util.Arrays;
import java.util.Collections;

import org.junit.jupiter.api.Test;

import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.TagContainsKeywordsPredicate;


/**
 * Contains integration tests (interaction with the Model) for {@code FilterTagCommand}.
 */
public class FilterTagCommandTest {
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void equals() throws ParseException {
        TagContainsKeywordsPredicate firstPredicate =
                new TagContainsKeywordsPredicate(createTags("first"));
        TagContainsKeywordsPredicate secondPredicate =
                new TagContainsKeywordsPredicate(createTags("second"));

        FilterTagCommand findTagFirstCommand = new FilterTagCommand(firstPredicate);
        FilterTagCommand findTagSecondCommand = new FilterTagCommand(secondPredicate);

        // same object -> returns true
        assertTrue(findTagFirstCommand.equals(findTagFirstCommand));

        // same values -> returns true
        FilterTagCommand findTagFirstCommandCopy = new FilterTagCommand(firstPredicate);
        assertTrue(findTagFirstCommand.equals(findTagFirstCommandCopy));

        // different types -> returns false
        assertFalse(findTagFirstCommand.equals(1));

        // null -> returns false
        assertFalse(findTagFirstCommand.equals(null));

        // different person -> returns false
        assertFalse(findTagFirstCommand.equals(findTagSecondCommand));
    }

    @Test
    public void execute_zeroKeywords_noPersonFound() throws ParseException {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 0);
        TagContainsKeywordsPredicate predicate = preparePredicate(" ");
        FilterTagCommand command = new FilterTagCommand(predicate);
        expectedModel.updateFilteredPersonList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredPersonList());
    }

    @Test
    public void execute_multipleKeywords_multiplePersonsFound() throws ParseException {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 3);
        TagContainsKeywordsPredicate predicate = preparePredicate("friends friend");
        FilterTagCommand command = new FilterTagCommand(predicate);
        expectedModel.updateFilteredPersonList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(ALICE, BENSON, DANIEL), model.getFilteredPersonList());
    }

    @Test
    public void toStringMethod() throws ParseException {
        TagContainsKeywordsPredicate predicate = new TagContainsKeywordsPredicate(createTags("friends"));
        FilterTagCommand filterTagCommand = new FilterTagCommand(predicate);
        String expected = FilterTagCommand.class.getCanonicalName() + "{tag=" + predicate + "}";
        assertEquals(expected, filterTagCommand.toString());
    }

    /**
     * Parses {@code userInput} into a {@code NameContainsKeywordsPredicate}.
     */
    private TagContainsKeywordsPredicate preparePredicate(String userInput) throws ParseException {
        return new TagContainsKeywordsPredicate(createTags(userInput.split("\\s+")));
    }
}
