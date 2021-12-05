package sample;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class Timer implements Initializable {
    public static Stage stage;
    @FXML
    StackPane root;
    @FXML
    Label counter;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        root.getStylesheets().add(getClass().getResource("timer.css").toExternalForm());
        counter.setStyle("-fx-text-fill: red;-fx-font-size:150;-fx-text-alignment: center;");//.add(getClass().getResource("timer.css").toExternalForm());
        //counter.setTextAlignment(TextAlignment.CENTER);
        //root.()
        //root.setStyle("-fx-background-color:null;");
        Thread thread=new Thread()
        {
            int value=5;
            @Override
            public void run()
            {
                //System.out.println("run kortese");
                while (value>0) {
                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            counter.setText(""+ value);
                        }
                    });
                    try {
                        Thread.sleep(940);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    value--;
                }
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        stage.close();
                    }
                });
                return;
            }
        };
        thread.start();
    }
}
