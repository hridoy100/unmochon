package sample;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class About2 implements Initializable {
    public static Stage stage;
    @FXML
    AnchorPane root;
    @FXML
    TextArea output;
    @FXML
    ImageView image;

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

        output.setText("Bangladeshi women are often harassed over their Facebook Messenger. There is a \n" +
                "high prevalence of such harassment among Bangladeshi Facebook users and that \n" +
                "often limits female users’ use of the Internet, and even causes various negative \n" +
                "social and psychological impacts on them. \n\n" +
                "Unmochon - a digital platform that will allow Bangladeshi women \n" +
                "\t•\tTo anonymously post authentic screenshots of an offensive conversation,\n" +
                "\t•\tTo involve the local community members in judgment, \n" +
                "\t•\tTo fight online harassment through public shaming of and boycotting the offenders.\n\n" +
                "Project Unmochon is funded by Information and Communication Technology \nDivision (ICTD), Bangladesh.");
        output.getStylesheets().add(getClass().getResource("button3.css").toExternalForm());
        root.getStylesheets().add(getClass().getResource("text.css").toExternalForm());
        output.setFocusTraversable(false);
        //text.setMouseTransparent(true);
        image.setImage(new Image("/logo.png"));
    }
}
