package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

public class ConfirmDialouge implements Initializable {
    public Stage stage;
    @FXML
    Pane root;

    @FXML
    ImageView icon;

    @FXML
    Text heading;

    @FXML
    Text description;

    @FXML
    Button ok;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        root.getStylesheets().add(getClass().getResource("dialogue.css").toExternalForm());
        //stage.setResizable(false);
    }

    public void okAction(ActionEvent actionEvent) {
        stage.close();
        if(UserInputNext.stage!=null)
            UserInputNext.stage.close();
        if(UserInput.stage!=null)
            UserInput.stage.close();
    }

    public void showMessage(String fheading,String fdescription,String filepath)
    {
        heading.setText(fheading);
        description.setText(fdescription);
        icon.setImage(new Image(filepath));
    }

}
