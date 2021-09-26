package sample;

import DB.DBAdministration;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;


public class FirstWindowController {

    private Main main;
    @FXML
    public void TakeScreenshot(ActionEvent event) throws UnsupportedFlavorException {
        FullScreenCapture fullScreenCapture = new FullScreenCapture();
        fullScreenCapture.SetMain(main);


        if(fullScreenCapture.CaptureDetails()){
            ///ShowAlert("Correct Credentials", "Screenshot successful", "Successfully added to database", "CONFIRMATION");
        }
        else
        {
            ///ShowAlert("Incorrect Credentials", "Incorrect Credentials", "The username and password you provided is not correct.", "ERROR");
        }

    }
    @FXML
    public void ViewAdminPanel(ActionEvent event) throws IOException {
        main.ShowAdminPage();
    }
    public void SetMain(Main main) {
        this.main = main;
    }

    void ShowAlert(String title, String header, String content, String alertType){
        Alert alert;
        if(alertType=="ERROR")
            alert = new Alert(Alert.AlertType.ERROR);
        else{
            alert = new Alert(Alert.AlertType.CONFIRMATION);
        }
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.showAndWait();
    }

}
