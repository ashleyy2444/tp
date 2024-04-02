package seedu.address.logic.commands;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.user.User;

import static java.util.Objects.requireNonNull;

public class AddResumeCommand extends Command {
    private final User user;

    public static final String COMMAND_WORD = "resume";

    public AddResumeCommand(User user) {
        requireNonNull(user);
        this.user = user;
    }
    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        return new CommandResult("yoohoo");
    }
}
