package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.DeleteTagCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.tag.Tag;
import seedu.address.model.tag.TagContainsKeywordsPredicate;

import java.util.ArrayList;
import java.util.List;

/**
 * Contains unit tests for {@code DeleteTagCommandParser}.
 */
public class DeleteTagCommandParserTest {

    private DeleteTagCommandParser parser = new DeleteTagCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertThrows(ParseException.class, () -> parser.parse("     "),
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteTagCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validArgs_returnsDeleteTagCommand() throws ParseException {
        // no leading and trailing whitespaces
        DeleteTagCommand expectedDeleteTagCommand =
                new DeleteTagCommand(new TagContainsKeywordsPredicate(createTags("friends")));
        assertEquals(expectedDeleteTagCommand.toString(), parser.parse("friends").toString());

        // multiple whitespaces between keywords
        assertEquals(expectedDeleteTagCommand.toString(), parser.parse(" \n friends \n \t").toString());
    }

    /**
     * Parses {@code tagKeywords} into a {@code List<Tag>}.
     */
    public static List<Tag> createTags(String... tagKeywords) throws ParseException {
        List<Tag> tags = new ArrayList<>();
        for (String keyword : tagKeywords) {
            tags.add(ParserUtil.parseTag(keyword));
        }
        return tags;
    }
}

