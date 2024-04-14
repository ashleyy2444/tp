package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Locale;

/**
 *  Logic to interpret date and time from user input
 */
public class InterviewTime {

    public static final String MESSAGE_CONSTRAINTS = "Error thrown, date and time format may be wrong. Check UG.";

    public static final String REGEX_YYYY = "\\d{4}";
    public static final String REGEX_DD = "(0[1-9]|[1-2][0-9]|3[01])";
    public static final String REGEX_MM = "(0[1-9]|1[0-2])";
    public static final String REGEX_HHMM = "^([0-1][0-9]|2[0-3])[0-5][0-9]$";
    public static final String INTERVIEW_TIME_FORMAT = "ddMMyyyyHHmm";
    public static final String EXAMPLE_INTERVIEW_TIME_FORMAT_1 = "020520240800";
    public static final String EXAMPLE_INTERVIEW_TIME_FORMAT_2 = "030820242300";
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern(INTERVIEW_TIME_FORMAT); //set format

    public final LocalDateTime dateTime;

    /**
     * Constructs InterviewTime object
     * @param dateTime input
     */
    public InterviewTime(String dateTime) {
        if (dateTime == null) {
            this.dateTime = null;
        }
        else {
            checkArgument(isValidInterviewTime(dateTime), MESSAGE_CONSTRAINTS);
            this.dateTime = LocalDateTime.parse(dateTime, formatter); //set format
        }
    }

    /**
     * Checks if date input is valid
     * @param test input
     * @return true if correct format, false otherwise
     */
    public static boolean isValidInterviewTime(String test) {
        if (test == null) {
            return true;
        }
        else {
            try {
                LocalDateTime.parse(test, formatter);
                return true;
            }
            catch (DateTimeParseException e) {
                return false;
            }
        }
    }

    public LocalDateTime getDateTime() {
        return this.dateTime;
    }

    public String rawToString() {
        return dateTime == null ? "" : dateTime.format(formatter);
    }

    /**
     * Returns true if {@code this.dateTime} is within the range provided.
     * @param range A List containing two {@code InterviewTime}.
     */
    public boolean isWithinInterviewTimeRange(List<InterviewTime> range) {
        assert range.size() == 2 : "InterviewTime range should be of size 2";
        assert !(range.get(0) == null && range.get(1) == null)
                : "InterviewTime range cannot cannot contain more than 1 null Object";
        LocalDateTime after = range.get(0) == null ? null : range.get(0).getDateTime();
        LocalDateTime before = range.get(1) == null ? null : range.get(1).getDateTime();
        if (after == null) {
            return this.dateTime.isBefore(before);
        } else if (before == null) {
            return this.dateTime.isAfter(after);
        } else {
            return (this.dateTime.isBefore(before) || this.dateTime.isEqual(before))
                    && (this.dateTime.isAfter(after) || this.dateTime.isEqual(after));
        }
    }

    public boolean isBefore(InterviewTime date) {
        return this.dateTime.isBefore(date.getDateTime());
    }

    @Override
    public String toString() {
        if (dateTime == null) {
            return "No Interviews set";
        }
        else {
            DateTimeFormatter beautify = DateTimeFormatter.ofPattern("MMMM dd, yyyy hh:mm a", Locale.ENGLISH);
            return dateTime.format(beautify);
        }
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof InterviewTime)) {
            return false;
        }

        InterviewTime otherDateTime = (InterviewTime) other;
        return (dateTime == null && otherDateTime.dateTime == null) ||
                (dateTime != null && dateTime.equals(otherDateTime.dateTime));
    }

    @Override
    public int hashCode() {
        return dateTime == null ? 0 : dateTime.hashCode();
    }


}
