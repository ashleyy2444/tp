package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class JobDifficultyTest {

    @Test
    public void testGetDifficulty() {
        // Test with a company that is in the JobDifficultyCompanyList and a salary of 1500
        JobDifficulty jobDifficulty1 = new JobDifficulty(new CompanyName("oracle"), new Salary("1500"));
        int expectedDifficulty1 = JobDifficultyCompanyList.getJobDifficultyCompanyList().get("oracle") + 5;
        assertTrue(jobDifficulty1.getDifficulty() >= 70);

        // Test with a company that is not in the JobDifficultyCompanyList and a salary of 3000
        JobDifficulty jobDifficulty2 = new JobDifficulty(new CompanyName("unknown"), new Salary("3000"));
        int actualDifficulty2 = jobDifficulty2.getDifficulty();
        assert(actualDifficulty2 >= 20 && actualDifficulty2 <= 70);

        // Test with a company that is in the JobDifficultyCompanyList and a salary of 5000
        JobDifficulty jobDifficulty3 = new JobDifficulty(new CompanyName("ibm"), new Salary("5000"));
        int expectedDifficulty3 = JobDifficultyCompanyList.getJobDifficultyCompanyList().get("ibm") + 40;
        if (expectedDifficulty3 > 100) {
            expectedDifficulty3 = 100;
        }
        assertEquals(expectedDifficulty3, jobDifficulty3.getDifficulty());

        // Test with a company that is not in the JobDifficultyCompanyList and a salary of 4000
        JobDifficulty jobDifficulty4 = new JobDifficulty(new CompanyName("unknown"), new Salary("4000"));
        int actualDifficulty4 = jobDifficulty4.getDifficulty();
        assert(actualDifficulty4 >= 30 && actualDifficulty4 <= 70);

        // Test with a company that is not in the JobDifficultyCompanyList and a salary of 10000
        JobDifficulty jobDifficulty5 = new JobDifficulty(new CompanyName("unknown"), new Salary("10000"));
        int actualDifficulty5 = jobDifficulty5.getDifficulty();
        assert(actualDifficulty5 >= 60 && actualDifficulty5 <= 100);
    }

    @Test
    public void testToString() {
        JobDifficulty jobDifficulty1 = new JobDifficulty(new CompanyName("oracle"), new Salary("1500"));
        assertEquals(Integer.toString(jobDifficulty1.getDifficulty()), jobDifficulty1.toString());

        JobDifficulty jobDifficulty2 = new JobDifficulty(new CompanyName("unknown"), new Salary("3000"));
        assertEquals(Integer.toString(jobDifficulty2.getDifficulty()), jobDifficulty2.toString());

        JobDifficulty jobDifficulty3 = new JobDifficulty(new CompanyName("ibm"), new Salary("5000"));
        assertEquals(Integer.toString(jobDifficulty3.getDifficulty()), jobDifficulty3.toString());

        JobDifficulty jobDifficulty4 = new JobDifficulty(new CompanyName("unknown"), new Salary("4000"));
        assertEquals(Integer.toString(jobDifficulty4.getDifficulty()), jobDifficulty4.toString());

        JobDifficulty jobDifficulty5 = new JobDifficulty(new CompanyName("unknown"), new Salary("10000"));
        assertEquals(Integer.toString(jobDifficulty5.getDifficulty()), jobDifficulty5.toString());
    }
}
