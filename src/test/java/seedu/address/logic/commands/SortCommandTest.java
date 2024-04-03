package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.PersonCompanyNameComparator;
import seedu.address.model.person.PersonInterviewTimeComparator;
import seedu.address.model.person.PersonNameComparator;
import seedu.address.model.person.PersonPriorityComparator;
import seedu.address.model.person.PersonSalaryComparator;

public class SortCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_validPriorityCliUnsortedList_success() {
        Integer prefixToSort = 0; //sort by priority
        SortCommand sortCommand = new SortCommand(prefixToSort, false);

        String expectedMessage = SortCommand.MESSAGE_LIST_SORTED_SUCCESS;

        ModelManager expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.updateSortedPersonList(new PersonPriorityComparator(false));

        assertCommandSuccess(sortCommand, model, expectedMessage, expectedModel);

        Integer prefixToSort2 = 0; //sort by priority
        SortCommand sortCommand2 = new SortCommand(prefixToSort2, true);

        String expectedMessage2 = SortCommand.MESSAGE_LIST_SORTED_SUCCESS;

        ModelManager expectedModel2 = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel2.updateSortedPersonList(new PersonPriorityComparator(true));

        assertCommandSuccess(sortCommand2, model, expectedMessage2, expectedModel2);
    }
    @Test
    public void execute_validCompanyNameCliUnsortedList_success() {
        Integer prefixToSort = 1; //sort by Company Name
        SortCommand sortCommand = new SortCommand(prefixToSort, false);

        String expectedMessage = SortCommand.MESSAGE_LIST_SORTED_SUCCESS;

        ModelManager expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.updateSortedPersonList(new PersonCompanyNameComparator(false));

        assertCommandSuccess(sortCommand, model, expectedMessage, expectedModel);

        Integer prefixToSort2 = 1; //sort by Company Name
        SortCommand sortCommand2 = new SortCommand(prefixToSort2, false);

        String expectedMessage2 = SortCommand.MESSAGE_LIST_SORTED_SUCCESS;

        ModelManager expectedModel2 = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel2.updateSortedPersonList(new PersonCompanyNameComparator(false));

        assertCommandSuccess(sortCommand2, model, expectedMessage2, expectedModel2);
    }

    @Test
    public void execute_validNameCliUnsortedList_success() {
        Integer prefixToSort = 2; //sort by Name
        SortCommand sortCommand = new SortCommand(prefixToSort, false);

        String expectedMessage = SortCommand.MESSAGE_LIST_SORTED_SUCCESS;

        ModelManager expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.updateSortedPersonList(new PersonNameComparator(false));

        assertCommandSuccess(sortCommand, model, expectedMessage, expectedModel);

        Integer prefixToSort2 = 2; //sort by Name
        SortCommand sortCommand2 = new SortCommand(prefixToSort2, true);

        String expectedMessage2 = SortCommand.MESSAGE_LIST_SORTED_SUCCESS;

        ModelManager expectedModel2 = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel2.updateSortedPersonList(new PersonNameComparator(true));

        assertCommandSuccess(sortCommand2, model, expectedMessage2, expectedModel2);
    }

    @Test
    public void execute_validInterviewTimeCliUnsortedList_success() {
        Integer prefixToSort = 3; //sort by InterviewTime
        SortCommand sortCommand = new SortCommand(prefixToSort, false);

        String expectedMessage = SortCommand.MESSAGE_LIST_SORTED_SUCCESS;

        ModelManager expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.updateSortedPersonList(new PersonInterviewTimeComparator(false));

        assertCommandSuccess(sortCommand, model, expectedMessage, expectedModel);

        Integer prefixToSort2 = 3; //sort by InterviewTime
        SortCommand sortCommand2 = new SortCommand(prefixToSort, true);

        String expectedMessage2 = SortCommand.MESSAGE_LIST_SORTED_SUCCESS;

        ModelManager expectedModel2 = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel2.updateSortedPersonList(new PersonInterviewTimeComparator(true));

        assertCommandSuccess(sortCommand2, model, expectedMessage2, expectedModel2);
    }

    @Test
    public void execute_validSalaryCliUnsortedList_success() {
        Integer prefixToSort = 4; //sort by InterviewTime
        SortCommand sortCommand = new SortCommand(prefixToSort, false);

        String expectedMessage = SortCommand.MESSAGE_LIST_SORTED_SUCCESS;

        ModelManager expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.updateSortedPersonList(new PersonSalaryComparator(false));

        assertCommandSuccess(sortCommand, model, expectedMessage, expectedModel);

        Integer prefixToSort2 = 4; //sort by InterviewTime
        SortCommand sortCommand2 = new SortCommand(prefixToSort2, true);

        String expectedMessage2 = SortCommand.MESSAGE_LIST_SORTED_SUCCESS;

        ModelManager expectedModel2 = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel2.updateSortedPersonList(new PersonSalaryComparator(true));

        assertCommandSuccess(sortCommand2, model, expectedMessage2, expectedModel2);
    }

    @Test
    public void execute_validJobDifficultyCliUnsortedList_success() throws CommandException {
        Integer prefixToSort = 5; //sort by Job Difficulty
        SortCommand sortCommand = new SortCommand(prefixToSort, false);

        String expectedMessage = SortCommand.MESSAGE_LIST_SORTED_SUCCESS;

        ModelManager expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.updateSortedPersonList(new PersonSalaryComparator(false));

        CommandResult result = sortCommand.execute(model);

        assertEquals(result.getFeedbackToUser(), expectedMessage);

        Integer prefixToSort2 = 5; //sort by Job Difficulty
        SortCommand sortCommand2 = new SortCommand(prefixToSort2, true);

        String expectedMessage2 = SortCommand.MESSAGE_LIST_SORTED_SUCCESS;

        ModelManager expectedModel2 = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel2.updateSortedPersonList(new PersonSalaryComparator(true));

        CommandResult result2 = sortCommand2.execute(model);

        assertEquals(result2.getFeedbackToUser(), expectedMessage2);
    }

    @Test
    public void execute_invalidCliUnsortedList_throwsCommandException() {
        Integer prefixToSort = 6; //outside [0-5]
        SortCommand sortCommand = new SortCommand(prefixToSort, false);

        assertCommandFailure(sortCommand, model, Messages.MESSAGE_INVALID_SORT_COMMAND_INDEX);
    }

    @Test
    public void equals() {
        SortCommand firstSort = new SortCommand(1, false);
        SortCommand secondSort = new SortCommand(2, false);

        // same object -> returns true
        assertTrue(firstSort.equals(firstSort));
        // same values -> returns true
        SortCommand firstSortCopy = new SortCommand(1, false);
        assertTrue(firstSort.equals(firstSortCopy));
        // different types -> returns false
        assertFalse(firstSort.equals(1));
        // null -> returns false
        assertFalse(firstSort.equals(null));
        // different person -> returns false
        assertFalse(firstSort.equals(secondSort));
    }

}
