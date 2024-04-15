package seedu.address.logic.commands;

import java.util.List;
import java.util.stream.Collectors;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Person;
import seedu.address.model.tag.Tag;
import seedu.address.model.tag.TagContainsKeywordsPredicate;

/**
 * Command to delete all contacts with a specified tag from the address book.
 */
public class DeleteTagCommand extends DeleteAbstractCommand {
    public static final String COMMAND_WORD = "delete t/";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes all contacts with the specified tag.\n"
            + "Parameters: TAG\n"
            + "Example: " + COMMAND_WORD + " friend";

    public static final String MESSAGE_DELETE_TAG_SUCCESS = "Deleted all contacts with tag: %1$s";
    public static final String MESSAGE_TAG_DOES_NOT_EXIST = "The following tags do not exist: %1$s";

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
        List<Tag> tags = tagToDelete.getTags();
        List<Tag> allTags = model.getAddressBook().getPersonList().stream()
                .flatMap(person -> person.getTags().stream())
                .distinct()
                .collect(Collectors.toList());

        if (!allTags.containsAll(tags)) {
            List<Tag> nonExistentTags = tags.stream()
                    .filter(tag -> !allTags.contains(tag))
                    .collect(Collectors.toList());
            throw new CommandException(String.format(MESSAGE_TAG_DOES_NOT_EXIST, nonExistentTags));
        }

        List<Person> personsToDelete = model.getFilteredPersonList().stream()
                .filter(person -> tagToDelete.test(person))
                .collect(Collectors.toList());

        personsToDelete.forEach(model::deletePerson);

        return new CommandResult(String.format(MESSAGE_DELETE_TAG_SUCCESS, tagToDelete));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof DeleteTagCommand)) {
            return false;
        }

        DeleteTagCommand otherDeleteTagCommand = (DeleteTagCommand) other;
        return tagToDelete.equals(otherDeleteTagCommand.tagToDelete);
    }

}
