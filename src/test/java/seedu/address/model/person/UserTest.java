package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import seedu.address.model.person.user.User;

public class UserTest {
    private User user = User.getInstance();
    @Test
    public void nullCompanyName_isNull() {
        assertEquals(null, user.getCompanyName());
    }
    @Test
    public void nullName_isNull() {
        assertEquals(null, user.getName());
    }
    @Test
    public void nullSalary_isNull() {
        assertEquals(null, user.getSalary());
    }
    @Test
    public void nullEmail_isNull() {
        assertEquals(null, user.getEmail());
    }
    @Test
    public void nullPhone_isNull() {
        assertEquals(null, user.getPhone());
    }
    @Test
    public void nullEducation_isNull() {
        assertEquals(null, user.getEducation());
    }
}
