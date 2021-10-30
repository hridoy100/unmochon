package sample;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Line;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class About implements Initializable {
    public static Stage stage;
    @FXML
    AnchorPane root;
    @FXML
    TextArea output;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        output.setText("\nZoom is for you. We help you express ideas, connect to"+ "\n" +
                " others, and build toward a future limited only by your imagination."+"\n" +
                " Our frictionless communications platform is the only b one that started"+"\n" +
                " with video as its foundation, and we have set the standard for "+"\n" +
                "innovation ever since. That is why we are an intuitive, scalable, "+"\n" +
                "and secure choice for large enterprises, small businesses, and "+"\n" +
                "individuals alike. Founded in 2011, Zoom is publicly traded (NASDAQ:ZM)"+"\n" +
                " and headquartered in San Jose, California.");
        output.getStylesheets().add(getClass().getResource("button2.css").toExternalForm());
        root.getStylesheets().add(getClass().getResource("text.css").toExternalForm());
        output.setFocusTraversable(false);
        //text.setMouseTransparent(true);
    }
}
