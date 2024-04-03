package seedu.address.ui;

import java.util.Comparator;
import java.util.logging.Logger;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.person.user.User;

/**
 * Controller for resume page
 */
public class ResumeWindow extends UiPart<Stage> {
    private static final Logger logger = LogsCenter.getLogger(HelpWindow.class);
    private static final String FXML = "ResumeWindow2.fxml";
    private User user = User.getInstance();

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
    @FXML
    private Label resume;
    @FXML
    private Label education;
    @FXML
    private Label skills;
    @FXML
    private VBox skillsVbox;
    @FXML
    private Label contactLabel;
    @FXML
    private VBox contactBox;

    /**
     * Creates a new ResumeWindow.
     *
     * @param root Stage to use as the root of the ResumeWindow.
     */
    public ResumeWindow(Stage root) {
        super(FXML, root);
        if (user.getName() != null) {
            resume.setText("Resume added");
            companyName.setText("Previous employment: " + user.getCompanyName().companyName);
            companyName.setVisible(true);
            name.setText("Name: " + user.getName().fullName);
            name.setVisible(true);
            phone.setText("Phone: " + user.getPhone().value);
            phone.setVisible(true);
            address.setText("Place of residence: " + user.getAddress().value);
            address.setVisible(true);
            email.setText("Email: " + user.getEmail().value);
            email.setVisible(true);
            salary.setText("Previous salary: " + "$" + user.getSalary().toString());
            salary.setVisible(true);
            education.setText("Highest Education: " + user.getEducation());
            education.setVisible(true);
            skills.setVisible(true);
            skillsVbox.setVisible(true);

            //for contact info box
            contactLabel.setVisible(true);
            contactBox.setVisible(true);

            user.getSkills().stream()
                    .sorted(Comparator.comparing(pl -> pl.languageName))
                    .forEach(pl -> skillsVbox.getChildren().add(
                            new Label(pl.languageName)));
        } else {
            resume.setText("No resume added");
        }
    }

    /**
     * Creates a new ResumeWindow.
     */
    public ResumeWindow() {
        this(new Stage());
    }

    /**
     * Shows the help window.
     * @throws IllegalStateException
     *     <ul>
     *         <li>
     *             if this method is called on a thread other than the JavaFX Application Thread.
     *         </li>
     *         <li>
     *             if this method is called during animation or layout processing.
     *         </li>
     *         <li>
     *             if this method is called on the primary stage.
     *         </li>
     *         <li>
     *             if {@code dialogStage} is already showing.
     *         </li>
     *     </ul>
     */
    public void show() {
        logger.fine("Showing user's resume.");
        getRoot().show();
        getRoot().centerOnScreen();
    }

    /**
     * Returns true if the help window is currently being shown.
     */
    public boolean isShowing() {
        return getRoot().isShowing();
    }

    /**
     * Hides the help window.
     */
    public void hide() {
        getRoot().hide();
    }

    /**
     * Focuses on the help window.
     */
    public void focus() {
        getRoot().requestFocus();
    }
}
