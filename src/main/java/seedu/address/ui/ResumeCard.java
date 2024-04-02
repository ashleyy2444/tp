package seedu.address.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.address.model.person.user.User;

public class ResumeCard extends UiPart<Region> {
    private static final String FXML = "PersonListCard.fxml";
    public final User user;
    @FXML
    private HBox cardPane;
    @FXML
    private Label companyName;
    @FXML
    private Label name;
    @FXML
    private Label phone;
    @FXML
    private Label address;
    @FXML
    private Label email;
    @FXML
    private Label salary;

    public ResumeCard(User user) {
        super(FXML);
        this.user = user;
        name.setText(user.getName().toString());
    }
}
