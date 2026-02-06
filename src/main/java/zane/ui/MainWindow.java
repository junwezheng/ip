package zane.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

/**
 * Controller for the main GUI.
 */
public class MainWindow extends AnchorPane {
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;
    private Zane zane;
    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/User.jpg"));
    private Image zaneImage = new Image(this.getClass().getResourceAsStream("/images/Zane.jpg"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    public void setZane(Zane zane) {
        this.zane = zane;
    }

    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = zane.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getZaneDialog(response, zaneImage)
        );
        userInput.clear();
    }
}
