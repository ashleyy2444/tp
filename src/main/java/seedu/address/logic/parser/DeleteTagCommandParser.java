package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.ArrayList;
import java.util.List;

import seedu.address.logic.commands.DeleteTagCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.tag.Tag;
import seedu.address.model.tag.TagContainsKeywordsPredicate;

/**
 * Parses input arguments and creates a new DeleteTagCommand object.
 */
public class DeleteTagCommandParser implements Parser<DeleteTagCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the DeleteTagCommand
     * and returns a DeleteTagCommand object for execution.
     * @throws ParseException if the user input does not conform to the expected format
     */
    @Override
    public DeleteTagCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteTagCommand.MESSAGE_USAGE));
        }
        String[] tagKeywords = trimmedArgs.split("\\s+");
        try {
            List<Tag> tags = parseTags(tagKeywords);
            return new DeleteTagCommand(new TagContainsKeywordsPredicate(tags));
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteTagCommand.MESSAGE_USAGE), pe);
        }
    }

    /**
     * Parses {@code Collection<String> tagNames} into a {@code List<Tag>}.
     *
     * @param tagNames The collection of tag names to parse.
     * @return A list of parsed tags.
     * @throws ParseException If any of the tag names are invalid.
     */
    public static List<Tag> parseTags(String... tagNames) throws ParseException {
        requireNonNull(tagNames);
        List<Tag> parsedTags = new ArrayList<>();
        for (String tagName : tagNames) {
            try {
                parsedTags.add(ParserUtil.parseTag(tagName));
            } catch (ParseException pe) {
                throw new ParseException(Tag.MESSAGE_CONSTRAINTS);
            }
        }
        return parsedTags;
    }
}
