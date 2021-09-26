package sample;

import DB.CommunicateWithPhp;
import DB.ImageList;
import DB.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.util.List;

public class ScreenshotTableController {
    private Main main;
    @FXML
    private TableView<ScreenshotTable> tableView;

    ObservableList<ScreenshotTable> data;
    @FXML
    private Button delete;
    @FXML
    private Button button;
    @FXML
    private Button insert;
    private boolean init = true;

    private void initializeColumns()
    {
        TableColumn<ScreenshotTable, String> userIdCol = new TableColumn<>("User ID");
        userIdCol.setMinWidth(450);
        userIdCol.setCellValueFactory(new PropertyValueFactory<ScreenshotTable,String>("userid"));

        TableColumn<ScreenshotTable, String> imageNameCol = new TableColumn<>("Screenshot Name");
        imageNameCol.setMinWidth(450);
        imageNameCol.setCellValueFactory(new PropertyValueFactory<ScreenshotTable,String>("image_name"));

//        TableColumn<UserTable, ImageView> screenshotCol = new TableColumn<>("Screenshot");
//        screenshotCol.setMinWidth(300);
//        screenshotCol.setCellValueFactory(new PropertyValueFactory<UserTable, ImageView>("screenshot"));
        tableView.setOnMouseClicked(e->{
            ScreenshotTable screenshotTable = (ScreenshotTable) tableView.getSelectionModel().getSelectedItem();
            try {
                //main.ShowImage(new DBUsers().getImage(userTable.getUserid()));
                main.ShowImage(new CommunicateWithPhp().getImage(screenshotTable.getImage_name()));
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        });
        tableView.getColumns().addAll(userIdCol, imageNameCol);
    }

    public void load(String userid)
    {
        if (init)
        {
            initializeColumns();
            init = false;
        }

        data = FXCollections.observableArrayList();

        //List<User> userDataList = new DBUsers().getAllUser();
        List<ImageList> userDataList = new CommunicateWithPhp().getImageList(userid);
        for (ImageList row : userDataList)
        {
            ScreenshotTable screenshotTable = new ScreenshotTable(row.userid, row.image_name);
            //data.add(new User(row.get(0), row.get(1), row.get(2), row.get(3), row.get(4)));
            data.add(screenshotTable);
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
    void showBack(ActionEvent event) throws IOException {
        main.ShowUser();

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

    public void setMain(Main main)
    {
        this.main = main;
    }
}
