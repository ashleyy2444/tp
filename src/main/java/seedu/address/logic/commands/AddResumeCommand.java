package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_COMPANY_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EDUCATION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PROGRAMMING_LANGUAGE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SALARY;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.user.User;

/**
 * Allows user to add their resume
 */
public class AddResumeCommand extends Command {

    public static final String COMMAND_WORD = "resume";
    public static final String MESSAGE_SUCCESS = "Resume added";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds your resume to the address book. "
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_COMPANY_NAME + "Google "
            + PREFIX_NAME + "John Doe "
            + PREFIX_PHONE + "98765432 "
            + PREFIX_EMAIL + "johnd@example.com "
            + PREFIX_SALARY + "3000 "
            + PREFIX_EDUCATION + "NUS "
            + PREFIX_ADDRESS + "311, Clementi Ave 2, #02-25 "
            + PREFIX_PROGRAMMING_LANGUAGE + "Java "
            + PREFIX_PROGRAMMING_LANGUAGE + "C++ ";
    private final User user;

    /**
     * Creates an AddCommand to add the specified {@code User}
     */
    public AddResumeCommand(User user) {
        requireNonNull(user);
        this.user = user;
    }
    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        return new CommandResult(MESSAGE_SUCCESS, false, true, false);
    }
}
