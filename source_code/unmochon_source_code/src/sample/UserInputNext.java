package sample;

import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import static sample.NewFirstController.*;

public class UserInputNext implements Initializable {
    @FXML
    Button ok,cancel;
    @FXML
    TextArea output;
    @FXML
    ImageView wrapper;
    @FXML
    AnchorPane root;

    public static Stage stage;
    public static String name="",contact="",comment="";

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            ok.getStylesheets().add(getClass().getResource("button.css").toExternalForm());
            cancel.getStylesheets().add(getClass().getResource("button.css").toExternalForm());
            output.setText("Your submission:\n\n" + name + "\n" + contact + "\n\n" + comment);
            //System.out.println();
            ok.getStylesheets().add(getClass().getResource("button.css").toExternalForm());

            wrapper.setImage(image2);//new Image(tempfile.toURI().toString()));
            Thread thread=new Thread()
            {
                @Override
                public void start()
                {
                    try {
                        Platform.runLater(new Runnable() {
                            @Override
                            public void run() {
                                try {
                                    Thread.sleep(500);
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                                stage.widthProperty().addListener((ChangeListener<? super Number>) new ChangeListener<Number>() {
                                    @Override
                                    public void changed(ObservableValue<? extends Number> arg0, Number arg1, Number arg2) {
                                        wrapper.setFitWidth(root.getWidth()/1.1);
                                        wrapper.setFitHeight(root.getHeight()/1.1);
                                    }
                                });
                            }
                        });
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            };
            thread.start();

        }catch (Exception e)
        {
            e.printStackTrace();
        }
        output.getStylesheets().add(getClass().getResource("text.css").toExternalForm());
    }
    @FXML
    void OKAction()
    {
        final Alert alt = new Alert(Alert.AlertType.CONFIRMATION);
        alt.setContentText("Are you sure to submit this?");
        alt.setHeaderText("");
        alt.setTitle("Confirm Submit");
        alt.getDialogPane().lookupButton(ButtonType.OK).addEventFilter(ActionEvent.ACTION, event -> {
            Runnable runnable=new Runnable() {
                @Override
                public void run() {
                    if(isInternetOn())
                    {
                        uploadToServer(name,contact,comment);
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
                            //stage.close();
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
            //stage.close();
        });
        Optional<ButtonType> option = alt.showAndWait();
    }

    @FXML
    void CancelAction()
    {
        stage.close();
    }

}
