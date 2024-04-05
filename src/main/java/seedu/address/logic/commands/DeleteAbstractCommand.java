package seedu.address.logic.commands;

import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

/**
 * Abstract class representing a DeleteCommand for deleting contacts or tags in an address book.
 * Subclasses will implement specific delete commands for different types of deletions.
 */
public abstract class DeleteAbstractCommand extends Command {

    public static final String COMMAND_WORD = "delete";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Deletes the contact identified by the index number "
            + "used in the displayed contact list or deletes contacts with the specified tag.\n"
            + "Parameters:\n"
            + "- " + COMMAND_WORD + " INDEX (must be a positive integer)\n"
            + "- " + COMMAND_WORD + " " + PREFIX_TAG + "TAG\n"
            + "Example: " + COMMAND_WORD + " 1\n"
            + "Example: " + COMMAND_WORD + " " + PREFIX_TAG + "friend";

}

