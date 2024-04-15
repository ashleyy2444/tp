package seedu.address.model.person;

/**
 * Represents a Person's info in the address book.
 */
public class Info {
    public final String value;

    /**
     * Constructs a {@code Info}.
     *
     * @param info Information about the person in the address book
     */
    public Info(String info) {
        String curr = info.trim();
        if (curr.length() == 0) {
            value = "No additional info";
        } else { value = curr; }
    }


    public Info() {
        value = "No additional info";
    }

    public static boolean isValidInfo(String test) {
        return true;
    }

    public String getInfo() {
        return value;
    }

    @Override
    public String toString() {

        if (value.length() == 0) {
            return "No additional info";
        }
        return value;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof Info)) {
            return false;
        }

        Info otherInfo = (Info) other;
        return value.equals(otherInfo.value);
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}
