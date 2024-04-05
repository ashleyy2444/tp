package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.tag.TagContainsKeywordsPredicate;

/**
 * Command to delete all contacts with a specified tag from the address book.
 */
public class DeleteTagCommand extends DeleteAbstractCommand {
    // public static final String COMMAND_WORD = DeleteCommand.COMMAND_WORD + " " + PREFIX_TAG;
    public static final String COMMAND_WORD = "delete t/";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes all contacts with the specified tag.\n"
            + "Parameters: TAG\n"
            + "Example: " + COMMAND_WORD + " friend";

    public static final String MESSAGE_DELETE_TAG_SUCCESS = "Deleted all contacts with tag: %1$s";

    private final TagContainsKeywordsPredicate tagToDelete;

    /**
     * Constructs a {@code DeleteTagCommand} with the specified tag to delete.
     *
     * @param tagToDelete The tag to delete.
     */
    public DeleteTagCommand(TagContainsKeywordsPredicate tagToDelete) {
        this.tagToDelete = tagToDelete;
    }

    /**
     * Executes the command to delete all contacts with the specified tag from the address book.
     *
     * @param model The address book model.
     * @return The result of the command execution.
     * @throws CommandException If an error occurs during command execution.
     */
    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        model.getFilteredPersonList().stream()
                .filter(person -> person.getTags().contains(tagToDelete))
                .forEach(person -> model.deletePerson(person));

        return new CommandResult(String.format(MESSAGE_DELETE_TAG_SUCCESS, tagToDelete));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof DeleteCommand)) {
            return false;
        }

        DeleteTagCommand otherDeleteTagCommand = (DeleteTagCommand) other;
        return tagToDelete.equals(otherDeleteTagCommand.tagToDelete);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("tagToDelete", tagToDelete)
                .toString();
    }
}
