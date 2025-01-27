package liv.ui;

import java.io.IOException;
import java.util.Collections;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;

/**
 * An example of a custom control using FXML.
 * This control represents a dialog box consisting of an ImageView to represent the speaker's face and a label
 * containing text from the speaker.
 */
public class DialogBox extends HBox {
    @FXML
    private Text dialog;
    @FXML
    private ImageView displayPicture;

    /**
     * Constructs a new DialogBox with the specified text and image.
     *
     * @param text        The text to display in the dialog box.
     * @param img         The image to display in the dialog box.
     * @param styleClass  The CSS style class to apply to the dialog box.
     * @throws IOException if the FXML file cannot be loaded.
     */
    public DialogBox(String text, Image img, String styleClass) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource("/view/DialogBox.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        dialog.setText(text);
        displayPicture.setImage(img);
        this.getStyleClass().add(styleClass);
    }

    /**
     * Flips the dialog box so that the ImageView is on the left and the text on the right.
     * This method is used to change the dialog box's alignment dynamically.
     */
    private void flip() {
        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
        Collections.reverse(tmp);
        getChildren().setAll(tmp);
        setAlignment(Pos.TOP_LEFT);
    }

    /**
     * Factory method to create a user dialog box with the specified text and image.
     *
     * @param text The text to display in the dialog box.
     * @param img  The image to display in the dialog box.
     * @return A new DialogBox instance configured for a user dialog.
     */
    public static DialogBox getUserDialog(String text, Image img) {
        return new DialogBox(text, img, "user-text-box");
    }

    /**
     * Factory method to create a Liv dialog box with the specified text and image.
     * This method also flips the dialog box so that the image is on the right.
     *
     * @param text The text to display in the dialog box.
     * @param img  The image to display in the dialog box.
     * @return A new DialogBox instance configured for a Liv dialog.
     */
    public static DialogBox getLivDialog(String text, Image img) {
        var db = new DialogBox(text, img, "liv-text-box");
        db.flip();
        return db;
    }
}
