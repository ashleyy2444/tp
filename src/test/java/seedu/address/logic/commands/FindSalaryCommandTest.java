package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.Messages.MESSAGE_PERSONS_LISTED_OVERVIEW;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalPersons.ALICE;
import static seedu.address.testutil.TypicalPersons.BENSON;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import java.util.Arrays;
import java.util.Collections;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Salary;
import seedu.address.model.person.SalaryContainsKeywordsPredicate;


/**
 * Contains integration tests (interaction with the Model) for {@code FindSalaryCommand}.
 */
public class FindSalaryCommandTest {
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void equals() {
        SalaryContainsKeywordsPredicate firstPredicate =
                new SalaryContainsKeywordsPredicate(Collections.singletonList(new Salary("2000")));
        SalaryContainsKeywordsPredicate secondPredicate =
                new SalaryContainsKeywordsPredicate(Collections.singletonList(new Salary("2000-5000")));

        FindSalaryCommand findSalaryFirstCommand = new FindSalaryCommand(firstPredicate);
        FindSalaryCommand findSalarySecondCommand = new FindSalaryCommand(secondPredicate);

        // same object -> returns true
        assertTrue(findSalaryFirstCommand.equals(findSalaryFirstCommand));

        // same values -> returns true
        FindSalaryCommand findSalaryFirstCommandCopy = new FindSalaryCommand(firstPredicate);
        assertTrue(findSalaryFirstCommand.equals(findSalaryFirstCommandCopy));

        // different types -> returns false
        assertFalse(findSalaryFirstCommand.equals(1));

        // null -> returns false
        assertFalse(findSalaryFirstCommand.equals(null));

        // different person -> returns false
        assertFalse(findSalaryFirstCommand.equals(findSalarySecondCommand));
    }


    @Test
    public void execute_oneSalary_multiplePersonsFound() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 2);
        SalaryContainsKeywordsPredicate predicate = preparePredicate(new Salary("500-3000"));
        FindSalaryCommand command = new FindSalaryCommand(predicate);
        expectedModel.updateFilteredPersonList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(ALICE, BENSON), model.getFilteredPersonList());
    }
    @Test
    public void execute_multipleSalaries_multiplePersonsFound() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 2);
        SalaryContainsKeywordsPredicate predicate = preparePredicate(new Salary("2000-3000"), new Salary("1000"));
        FindSalaryCommand command = new FindSalaryCommand(predicate);
        expectedModel.updateFilteredPersonList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(ALICE, BENSON), model.getFilteredPersonList());
    }




    @Test
    public void toStringMethod() {
        SalaryContainsKeywordsPredicate predicate =
                new SalaryContainsKeywordsPredicate(Arrays.asList(new Salary("5000")));
        FindSalaryCommand findSalaryCommand = new FindSalaryCommand(predicate);
        String expected = FindSalaryCommand.class.getCanonicalName() + "{salary=" + predicate + "}";
        assertEquals(expected, findSalaryCommand.toString());
    }

    /**
     * Parses {@code userInput} into a {@code SalaryContainsKeywordsPredicate}.
     */
    private SalaryContainsKeywordsPredicate preparePredicate(Salary... salaries) {
        return new SalaryContainsKeywordsPredicate(Arrays.asList(salaries));
    }
}
