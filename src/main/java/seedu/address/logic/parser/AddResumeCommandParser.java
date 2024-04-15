package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_COMPANY_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EDUCATION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PROGRAMMING_LANGUAGE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SALARY;

import java.util.Set;
import java.util.stream.Stream;

import seedu.address.logic.commands.AddResumeCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.language.ProgrammingLanguage;
import seedu.address.model.person.Address;
import seedu.address.model.person.CompanyName;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Phone;
import seedu.address.model.person.Salary;
import seedu.address.model.person.user.Education;
import seedu.address.model.person.user.User;

/**
 * Parses input arguments and creates a new AddResumeCommand object
 */
public class AddResumeCommandParser implements Parser<AddResumeCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the AddResumeCommand
     * and returns an AddResumeCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public AddResumeCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(
                        args, PREFIX_COMPANY_NAME, PREFIX_NAME, PREFIX_PHONE, PREFIX_EMAIL, PREFIX_ADDRESS,
                        PREFIX_SALARY, PREFIX_EDUCATION, PREFIX_PROGRAMMING_LANGUAGE);

        if (!arePrefixesPresent(argMultimap, PREFIX_NAME, PREFIX_ADDRESS, PREFIX_PHONE, PREFIX_EMAIL,
                PREFIX_COMPANY_NAME, PREFIX_SALARY, PREFIX_EDUCATION) || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddResumeCommand.MESSAGE_USAGE));
        }
        argMultimap.verifyNoDuplicatePrefixesFor(PREFIX_COMPANY_NAME, PREFIX_NAME, PREFIX_PHONE, PREFIX_EMAIL,
                PREFIX_ADDRESS, PREFIX_SALARY, PREFIX_EDUCATION);
        CompanyName companyName = ParserUtil.parseCompanyName(argMultimap.getValue(PREFIX_COMPANY_NAME).get());
        Name name = ParserUtil.parseName(argMultimap.getValue(PREFIX_NAME).get());
        Phone phone = ParserUtil.parsePhone(argMultimap.getValue(PREFIX_PHONE).get());
        Email email = ParserUtil.parseEmail(argMultimap.getValue(PREFIX_EMAIL).get());
        Address address = ParserUtil.parseAddress(argMultimap.getValue(PREFIX_ADDRESS).get());
        Salary salary = ParserUtil.parseSalary(argMultimap.getValue(PREFIX_SALARY).orElse("0"));
        Education education = ParserUtil.parseEducation(argMultimap.getValue(PREFIX_EDUCATION).get());
        Set<ProgrammingLanguage> skills = ParserUtil.parseProgrammingLanguages(argMultimap
                .getAllValues(PREFIX_PROGRAMMING_LANGUAGE));

        User user = User.getInstance();
        user.reset();
        user.setCompanyName(companyName);
        user.setName(name);
        user.setPhone(phone);
        user.setEmail(email);
        user.setAddress(address);
        user.setSalary(salary);
        user.setEducation(education);
        user.setSkills(skills);

        return new AddResumeCommand(user);
    }
    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}
