package seedu.address.model.person;

import java.util.Comparator;

/**
 * Compares two persons based on their company name.
 */
public class PersonCompanyNameComparator implements Comparator<Person> {
    private boolean isReverseOrder = false;

    // Constructor that accepts a boolean parameter to set the sorting order
    public PersonCompanyNameComparator(boolean isReverseOrder) {
        this.isReverseOrder = isReverseOrder;
    }
    @Override
    public int compare(Person p1, Person p2) {
        int comparisonResult = p1.getCompanyName().companyName.compareTo(p2.getCompanyName().companyName);
        return isReverseOrder ? -comparisonResult : comparisonResult;
    }
}
