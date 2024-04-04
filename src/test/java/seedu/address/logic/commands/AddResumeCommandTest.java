package seedu.address.logic.commands;

import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.address.model.person.user.User;

public class AddResumeCommandTest {
    //Create user for testing
    private User user = User.getInstance();
    @Test
    public void constructor_nullUser_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddResumeCommand(null));
    }
    @Test
    public void execution_nullModel_throwsNullPointerException() throws Exception {
        AddResumeCommand addResumeCommand = new AddResumeCommand(user);
        assertThrows(NullPointerException.class, () -> addResumeCommand.execute(null));
    }

}
