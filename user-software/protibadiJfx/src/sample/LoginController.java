package sample;

import DB.CommunicateWithPhp;
import DB.DBAdministration;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.io.IOException;


public class LoginController {

    private Main main;
    @FXML
    private TextField usernameField;
    @FXML
    private TextField passwordField;
    @FXML
    private Button submitBtn;

    @FXML
    public void LoginAction(ActionEvent event) throws IOException {
        String userID = usernameField.getText();
        String passwordText = passwordField.getText();
        CommunicateWithPhp communicateWithPhp = new CommunicateWithPhp();
        if(communicateWithPhp.verifyAdmin(userID, passwordText))
        {
//            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
//            alert.setTitle("Correct Credentials");
//            alert.setHeaderText("correct Credentials");
//            alert.setContentText("Login Successful");
//            alert.showAndWait();
            //main.ShowAdminPage();
            main.ShowUser();
        }
        else {
            // failed login
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Incorrect Credentials");
            alert.setHeaderText("Incorrect Credentials");
            alert.setContentText("The username and password you provided is not correct.");
            alert.showAndWait();
        }
    }



    @FXML
    public void GoToFirstWindow(ActionEvent event) throws IOException {
        main.ShowFirstWindow();
    }

    public void SetMain(Main main) {
        this.main = main;
    }

}
