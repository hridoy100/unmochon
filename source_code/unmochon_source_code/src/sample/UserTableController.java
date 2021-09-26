package sample;

import DB.CommunicateWithPhp;
import DB.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.util.List;

public class UserTableController {
    private Main main;
    @FXML
    private TableView<UserTable> tableView;

    ObservableList<UserTable> data;
    @FXML
    private Button delete;
    @FXML
    private Button button;
    @FXML
    private TextField tokenField;
    @FXML
    private Button insert;
    private boolean init = true;

    private void initializeColumns()
    {
        TableColumn<UserTable, String> userIdCol = new TableColumn<>("User ID");
        userIdCol.setMinWidth(300);
        userIdCol.setCellValueFactory(new PropertyValueFactory<UserTable,String>("userid"));

        TableColumn<UserTable, String> userlinkCol = new TableColumn<>("User Link");
        userlinkCol.setMinWidth(700);
        userlinkCol.setCellValueFactory(new PropertyValueFactory<UserTable,String>("userlink"));

//        TableColumn<UserTable, ImageView> screenshotCol = new TableColumn<>("Screenshot");
//        screenshotCol.setMinWidth(300);
//        screenshotCol.setCellValueFactory(new PropertyValueFactory<UserTable, ImageView>("screenshot"));
        tableView.setOnMouseClicked(e->{
            UserTable userTable = (UserTable) tableView.getSelectionModel().getSelectedItem();
            try {
                //main.ShowImage(new DBUsers().getImage(userTable.getUserid()));
                //main.ShowImage(new CommunicateWithPhp().getImage(userTable.getUserid()));
                main.ShowScreenshotTable(userTable.getUserid());
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        });
        tableView.getColumns().addAll(userIdCol, userlinkCol);
    }

    public void load()
    {
        if (init)
        {
            initializeColumns();
            init = false;
        }

        data = FXCollections.observableArrayList();

        //List<User> userDataList = new DBUsers().getAllUser();
        List<User> userDataList = new CommunicateWithPhp().Connection();
        for (User row : userDataList)
        {
            String userid = row.userid;
//            userid = userid.replace("$", "&");
//            userid = userid.replace("_", "+");

            String userlink = row.userlink;
//            userlink = userlink.replace("$", "&");
//            userlink = userlink.replace("_", "+");

            UserTable u = new UserTable(userid, userlink, row.screenshot);
            //data.add(new User(row.get(0), row.get(1), row.get(2), row.get(3), row.get(4)));
            data.add(u);
            //System.out.println(u.toString());
            //System.out.println(data);
            //System.out.println(row.get(0)+" "+ row.get(1)+ " "+ row.get(2)+ " "+ row.get(3)+ " "+ row.get(4));
        }
        tableView.setEditable(true);
        tableView.setItems(data);
        //data.get(0).setFirstName("Jacob2");

    }

    @FXML
    void logoutFunc(ActionEvent event)
    {
        try
        {
            main.ShowAdminPage();
        } catch (Exception e)
        {
            e.printStackTrace();
        }

    }
    @FXML
    void showHome(ActionEvent event) throws IOException {
        main.ShowFirstWindow();

    }

    void AlertDialogShow(boolean success)
    {
        if(success)
        {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Update Successful");
            alert.setHeaderText("Update Notice.");
            alert.setContentText("Food Details have been successfully updated");
            alert.showAndWait();
        }
        else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Update Notice");
            alert.setHeaderText("Failed To Update");
            alert.setContentText("Food Details update Failed");
            alert.showAndWait();
        }
    }

    @FXML
    public void updateAccessToken(ActionEvent event){
        CommunicateWithPhp communicateWithPhp = new CommunicateWithPhp();
        if(tokenField.getText().length()<1) {
            errorAlert();
        }
        else {
            if(communicateWithPhp.setToken(tokenField.getText())){
                successAlert();
                tokenField.setText("");
            }
            else errorAlert();
        }
    }

    void errorAlert(){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Empty Field");
        alert.setHeaderText("Field Might Be Empty");
        alert.setContentText("No Access Token Inserted");
        alert.showAndWait();
    }

    void successAlert(){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Access Token Updated");
        alert.setHeaderText("Successfully Updated");
        alert.setContentText("Access Token Inserted Into Database");
        alert.showAndWait();
    }


    public void setMain(Main main)
    {
        this.main = main;
    }
}
