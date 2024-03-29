package seedu.address.model.person;

import java.util.Random;

/**
 * Calculate the difficulty of a job in the address book.
 */
public class JobDifficulty {
    private int difficulty = 0;

    /**
     * Constructs a {@code JobDifficulty}.
     * The difficulty is calculated based on the company name and salary.
     *
     * @param companyName A valid company name.
     * @param salary A valid salary.
     */
    public JobDifficulty(CompanyName companyName, Salary salary) {
        Random random = new Random();
        String companyNameString = companyName.toString().toLowerCase();
        int salaryInt = salary.getSalary1();
        if (JobDifficultyCompanyList.getJobDifficultyCompanyList().containsKey(companyNameString)) {
            difficulty += JobDifficultyCompanyList.getJobDifficultyCompanyList().get(companyNameString);
        } else {
            difficulty += random.nextInt(30);
        }
        if (salaryInt < 1000) {
            difficulty += random.nextInt(5);
        } else if (salaryInt <= 1500) {
            difficulty += 5 + random.nextInt(15);
        } else if (salaryInt <= 3000) {
            difficulty += 20 + random.nextInt(10);
        } else if (salaryInt <= 4000) {
            difficulty += 30 + random.nextInt(10);
        } else if (salaryInt <= 5000) {
            difficulty += 40 + random.nextInt(20);
        } else {
            difficulty += 60 + random.nextInt(20);
        }
        if (difficulty > 100) {
            difficulty = 100;
        }
    }

    public int getDifficulty() {
        return difficulty;
    }

    @Override
    public String toString() {
        return Integer.toString(difficulty);
    }
}
