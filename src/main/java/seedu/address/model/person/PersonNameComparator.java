package seedu.address.model.person;

import java.util.Comparator;

/**
 * Compares two persons based on their name.
 */
public class PersonNameComparator implements Comparator<Person> {
    private boolean isReverseOrder = false;

    public PersonNameComparator(boolean isReverseOrder) {
        this.isReverseOrder = isReverseOrder;
    }
    @Override
    public int compare(Person p1, Person p2) {
        int comparisonResult = p1.getName().fullName.compareTo(p2.getName().fullName);
        return isReverseOrder ? -comparisonResult : comparisonResult;
    }
}
