package seedu.address.model.person;

import java.util.Comparator;

/**
 * Compares two persons based on their priority.
 */
public class PersonPriorityComparator implements Comparator<Person> {
    private boolean isReverseOrder = false;

    public PersonPriorityComparator(boolean isReverseOrder) {
        this.isReverseOrder = isReverseOrder;
    }
    @Override
    public int compare(Person p1, Person p2) {
        int comparisonResult = Integer.compare(p1.getPriority(), p2.getPriority());
        return isReverseOrder ? -comparisonResult : comparisonResult;
    }
}
