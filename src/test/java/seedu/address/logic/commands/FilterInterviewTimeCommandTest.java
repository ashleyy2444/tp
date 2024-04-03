package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.Messages.MESSAGE_PERSONS_LISTED_OVERVIEW;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalPersons.ALICE;
import static seedu.address.testutil.TypicalPersons.BENSON;
import static seedu.address.testutil.TypicalPersons.CARL;
import static seedu.address.testutil.TypicalPersons.DANIEL;
import static seedu.address.testutil.TypicalPersons.ELLE;
import static seedu.address.testutil.TypicalPersons.FIONA;
import static seedu.address.testutil.TypicalPersons.GEORGE;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import java.util.Arrays;
import java.util.Collections;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.InterviewTime;
import seedu.address.model.person.InterviewTimeContainsKeywordsPredicate;


/**
 * Contains integration tests (interaction with the Model) for {@code FilterInterviewCommand}.
 */
public class FilterInterviewTimeCommandTest {
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void equals() {
        InterviewTimeContainsKeywordsPredicate firstPredicate =
                new InterviewTimeContainsKeywordsPredicate(Collections.singletonList(
                        Arrays.asList(
                                new InterviewTime("121220210000"), null)
                ));
        InterviewTimeContainsKeywordsPredicate secondPredicate =
                new InterviewTimeContainsKeywordsPredicate(Collections.singletonList(Arrays.asList(
                        new InterviewTime("121220220000"), new InterviewTime("121220250000"))
                ));

        FilterInterviewTimeCommand filterInterviewTimeFirstCommand = new FilterInterviewTimeCommand(firstPredicate);
        FilterInterviewTimeCommand filterInterviewTimeSecondCommand = new FilterInterviewTimeCommand(secondPredicate);

        // same object -> returns true
        assertTrue(filterInterviewTimeFirstCommand.equals(filterInterviewTimeFirstCommand));

        // same values -> returns true
        FilterInterviewTimeCommand findInterviewTimeFirstCommandCopy = new FilterInterviewTimeCommand(firstPredicate);
        assertTrue(filterInterviewTimeFirstCommand.equals(findInterviewTimeFirstCommandCopy));

        // different types -> returns false
        assertFalse(filterInterviewTimeFirstCommand.equals(1));

        // null -> returns false
        assertFalse(filterInterviewTimeFirstCommand.equals(null));

        // different person -> returns false
        assertFalse(filterInterviewTimeFirstCommand.equals(filterInterviewTimeSecondCommand));
    }

    @Test
    public void execute_oneInterviewTime_multiplePersonsFound() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 1);
        InterviewTimeContainsKeywordsPredicate predicate = new InterviewTimeContainsKeywordsPredicate(
                Collections.singletonList(Arrays.asList(
                        new InterviewTime("121220221430"), new InterviewTime("121220222359")
                ))
        );
        FilterInterviewTimeCommand command = new FilterInterviewTimeCommand(predicate);
        expectedModel.updateFilteredPersonList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(BENSON), model.getFilteredPersonList());
    }

    @Test
    public void execute_multipleInterviewTimes_multiplePersonsFound() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 7);
        InterviewTimeContainsKeywordsPredicate predicate = new InterviewTimeContainsKeywordsPredicate(Arrays.asList(
                Arrays.asList(null, new InterviewTime("121220221430")),
                Arrays.asList(new InterviewTime("121220221430"), null)
        ));
        FilterInterviewTimeCommand command = new FilterInterviewTimeCommand(predicate);
        expectedModel.updateFilteredPersonList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(ALICE, BENSON, CARL, DANIEL, ELLE, FIONA, GEORGE), model.getFilteredPersonList());
    }


    @Test
    public void toStringMethod() {
        InterviewTimeContainsKeywordsPredicate predicate =
                new InterviewTimeContainsKeywordsPredicate(Arrays.asList(
                        Arrays.asList(null, new InterviewTime("121220221430")),
                        Arrays.asList(new InterviewTime("121220221430"), null)
                ));
        FilterInterviewTimeCommand filterInterviewTimeCommand = new FilterInterviewTimeCommand(predicate);
        String expected = FilterInterviewTimeCommand.class.getCanonicalName() + "{interviewTime=" + predicate + "}";
        assertEquals(expected, filterInterviewTimeCommand.toString());
    }
}
