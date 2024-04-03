package seedu.address.model.person;

import java.util.Comparator;

/**
 * Compares two persons based on their job difficulty.
 */
public class PersonJobDifficultyComparator implements Comparator<Person> {
    private boolean isReverseOrder = false;

    public PersonJobDifficultyComparator(boolean isReverseOrder) {
        this.isReverseOrder = isReverseOrder;
    }
    @Override
    public int compare(Person p1, Person p2) {
        int comparisonResult = Double.compare(p1.getJobDifficulty().getDifficulty(),
                p2.getJobDifficulty().getDifficulty());
        return isReverseOrder ? -comparisonResult : comparisonResult;
    }
}
