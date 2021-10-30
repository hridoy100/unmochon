package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import static sample.NewFirstController.isInternetOn;
import static sample.NewFirstController.uploadToServer;

public class UserInput2 implements Initializable {
    @FXML
    Button ok,cancel;
    @FXML
    TextArea name_inp,contact_inp,comment_inp;

    public static Stage stage;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ok.getStylesheets().add(getClass().getResource("button.css").toExternalForm());
        cancel.getStylesheets().add(getClass().getResource("button.css").toExternalForm());
    }
    @FXML
    void OKAction()
    {
        final Alert alt = new Alert(Alert.AlertType.CONFIRMATION);
        alt.setContentText("Are you sure to upload this screenshot?Please confirm it....");
        alt.setHeaderText("");
        alt.setTitle("Confirm Upload!");
        alt.getDialogPane().lookupButton(ButtonType.OK).addEventFilter(ActionEvent.ACTION, event -> {
            Runnable runnable=new Runnable() {
                @Override
                public void run() {
                    if(isInternetOn())
                    {
                        uploadToServer(name_inp.getText(),contact_inp.getText().toString(),comment_inp.getText());
                        ////upload alert,reset kora
                    }
                    else
                    {
                        final Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setContentText("You don't have active internet connection.Please confirm it first and then retry.");
                        alert.setHeaderText("");
                        alert.setTitle("Connection Error!");
                        alert.getDialogPane().lookupButton(ButtonType.OK).addEventFilter(ActionEvent.ACTION, event -> {
                            alert.close();
                            stage.close();
                        });
                        Optional<ButtonType> option = alert.showAndWait();
                    }
                }
            };
            runnable.run();
            alt.close();
            stage.close();
        });
        alt.getDialogPane().lookupButton(ButtonType.CANCEL).addEventFilter(ActionEvent.ACTION, event -> {
            alt.close();
            stage.close();
        });
        Optional<ButtonType> option = alt.showAndWait();
    }

    @FXML
    void CancelAction()
    {
        stage.close();
    }

}
