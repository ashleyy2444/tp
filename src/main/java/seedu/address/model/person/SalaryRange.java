package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;


/**
 * Represents a Person's salary in the address book.
 */
public class SalaryRange {
    public static final String MESSAGE_CONSTRAINTS = "SALARY_RANGE should only contain numbers, with range [0, "
            + "4294967295] "
            + "or two pure digital numbers with ‘-’ in between or '<' or '>' followed by a digital number. "
            + "Numbers can vary from large to small or from small to large.\n"
            + "Both digital numbers should be within the range [0, 4294967295]\n"
            + "Examples:\n"
            + "2000-4000\n"
            + ">5000\n"
            + "<10000\n";
    public static final String VALIDATION_REGEX = "^(\\d+-\\d+)|((<|>)\\d+)|(\\d+)$";
    public static final int UPPERBOUND = 2147483647;
    public static final int LOWERBOUND = 0;

    private int maxSalary;
    private int minSalary;
    private boolean isRange;
    private boolean isMax;
    private boolean isMin;

    /**
     * Constructs a {@code Salary}.
     *
     * @param salaryRange A valid salary range.
     */
    public SalaryRange(String salaryRange) {
        requireNonNull(salaryRange);
        checkArgument(isValidSalaryRange(salaryRange), MESSAGE_CONSTRAINTS);
        parseSalaryRange(salaryRange);
    }

    /**
     * Constructs a {@code Salary}.
     *
     * @param salaryRange A valid salary in string format.
     */
    public void parseSalaryRange(String salaryRange) {
        if (salaryRange.contains("-")) {
            String[] range = salaryRange.split("-");
            int minSalary = Integer.parseInt(range[0]);
            int maxSalary = Integer.parseInt(range[1]);
            this.minSalary = Math.min(minSalary, maxSalary);
            this.maxSalary = Math.max(minSalary, maxSalary);
            this.isRange = true;
        } else if (salaryRange.contains(">")) {
            String[] range = salaryRange.split(">");
            this.minSalary = Integer.parseInt(range[1]);
            this.maxSalary = UPPERBOUND;
            this.isMin = true;
        } else if (salaryRange.contains("<")) {
            String[] range = salaryRange.split("<");
            this.maxSalary = Integer.parseInt(range[1]);
            this.minSalary = LOWERBOUND;
            this.isMax = true;
        } else {
            this.minSalary = Integer.parseInt(salaryRange);
            this.maxSalary = Integer.parseInt(salaryRange);
            this.isRange = false;
        }
    }

    /**
     * Returns true if a given string is a valid salary format.
     */
    public static boolean isValidSalaryRange(String test) {
        if (!test.matches(VALIDATION_REGEX)) {
            return false;
        }
        if (test.contains("-")) {
            return isValidRange(test);
        } else if (test.contains(">")) {
            return isValidMin(test);
        } else if (test.contains("<")) {
            return isValidMax(test);
        } else {
            return isValidNum(test);
        }
    }
    private static boolean isValidRange(String test) {
        try {
            String[] salaryRange = test.split("-");
            int salary1 = Integer.parseInt(salaryRange[0]);
            int salary2 = Integer.parseInt(salaryRange[1]);
            if (salary1 < LOWERBOUND || salary2 < LOWERBOUND) {
                return false;
            }
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }
    private static boolean isValidMin(String test) {
        try {
            String[] salaryRange = test.split(">");
            int minSalary = Integer.parseInt(salaryRange[1]);
            if (minSalary < LOWERBOUND) {
                return false;
            }
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }
    private static boolean isValidMax(String test) {
        try {
            String[] salaryRange = test.split("<");
            int maxSalary = Integer.parseInt(salaryRange[1]);
            if (maxSalary < LOWERBOUND) {
                return false;
            }
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }

    private static boolean isValidNum(String test) {
        try {
            int salary = Integer.parseInt(test);
            if (salary < LOWERBOUND) {
                return false;
            }
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }

    /**
     * Returns true if the given Salary is within the salary range
     */
    public boolean isWithinSalaryRange(Salary salaryToTest) {
        SalaryRange test = new SalaryRange(salaryToTest.toString());
        boolean isMinWithinRange = this.minSalary <= test.minSalary;
        boolean isMaxWithinRange = this.maxSalary >= test.minSalary;
        return isMinWithinRange && isMaxWithinRange;
    }

    @Override
    public String toString() {
        if (isRange) {
            return this.minSalary + "-" + maxSalary;
        } else if (isMax) {
            return "<" + this.maxSalary;
        } else if (isMin) {
            return ">" + this.minSalary;
        } else {
            return String.valueOf(minSalary);
        }
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        // instanceof handles nulls
        if (!(other instanceof SalaryRange)) {
            return false;
        }
        else {
            boolean isMinSame = ((SalaryRange) other).minSalary == this.minSalary;
            boolean isMaxSame = ((SalaryRange) other).maxSalary == this.maxSalary;
            return isMinSame && isMaxSame;
        }
    }
}
