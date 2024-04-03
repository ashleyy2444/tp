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
import seedu.address.model.person.SalaryContainsKeywordsPredicate;
import seedu.address.model.person.SalaryRange;


/**
 * Contains integration tests (interaction with the Model) for {@code FindSalaryCommand}.
 */
public class FilterSalaryCommandTest {
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void equals() {
        SalaryContainsKeywordsPredicate firstPredicate =
                new SalaryContainsKeywordsPredicate(Collections.singletonList(new SalaryRange("2000")));
        SalaryContainsKeywordsPredicate secondPredicate =
                new SalaryContainsKeywordsPredicate(Collections.singletonList(new SalaryRange("2000-5000")));

        FilterSalaryCommand filterSalaryFirstCommand = new FilterSalaryCommand(firstPredicate);
        FilterSalaryCommand filterSalarySecondCommand = new FilterSalaryCommand(secondPredicate);

        // same object -> returns true
        assertTrue(filterSalaryFirstCommand.equals(filterSalaryFirstCommand));

        // same values -> returns true
        FilterSalaryCommand findSalaryFirstCommandCopy = new FilterSalaryCommand(firstPredicate);
        assertTrue(filterSalaryFirstCommand.equals(findSalaryFirstCommandCopy));

        // different types -> returns false
        assertFalse(filterSalaryFirstCommand.equals(1));

        // null -> returns false
        assertFalse(filterSalaryFirstCommand.equals(null));

        // different person -> returns false
        assertFalse(filterSalaryFirstCommand.equals(filterSalarySecondCommand));
    }


    @Test
    public void execute_oneSalary_multiplePersonsFound() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 2);
        SalaryContainsKeywordsPredicate predicate = preparePredicate(new SalaryRange(">=100"));
        FilterSalaryCommand command = new FilterSalaryCommand(predicate);
        expectedModel.updateFilteredPersonList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(ALICE, BENSON), model.getFilteredPersonList());
    }
    @Test
    public void execute_multipleSalaries_multiplePersonsFound() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 2);
        SalaryContainsKeywordsPredicate predicate = preparePredicate(new SalaryRange("1000"),
                new SalaryRange(">=10000"));
        FilterSalaryCommand command = new FilterSalaryCommand(predicate);
        expectedModel.updateFilteredPersonList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(ALICE, BENSON), model.getFilteredPersonList());
    }


    @Test
    public void toStringMethod() {
        SalaryContainsKeywordsPredicate predicate =
                new SalaryContainsKeywordsPredicate(Arrays.asList(new SalaryRange("5000")));
        FilterSalaryCommand filterSalaryCommand = new FilterSalaryCommand(predicate);
        String expected = FilterSalaryCommand.class.getCanonicalName() + "{salary=" + predicate + "}";
        assertEquals(expected, filterSalaryCommand.toString());
    }

    /**
     * Parses {@code userInput} into a {@code SalaryContainsKeywordsPredicate}.
     */
    private SalaryContainsKeywordsPredicate preparePredicate(SalaryRange... salaries) {
        return new SalaryContainsKeywordsPredicate(Arrays.asList(salaries));
    }
}
