package seedu.address.model.person;

import java.util.Comparator;

/**
 * Compares two persons based on their job difficulty.
 */
public class PersonJobDifficultyComparator implements Comparator<Person> {

    @Override
    public int compare(Person p1, Person p2) {
        return Double.compare(p1.getJobDifficulty().getDifficulty(), p2.getJobDifficulty().getDifficulty());
    }
}
