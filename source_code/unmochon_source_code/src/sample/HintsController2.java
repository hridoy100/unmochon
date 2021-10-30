package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class HintsController2 implements Initializable {
    @FXML
    TextArea text;
    @FXML
    ImageView imagew;
    @FXML
    Image image;
    @FXML
    Button previous,next,close;

    List<HintsData> hintsDataList=new ArrayList<>();
    public static int index=0;
    public static Stage stage;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        hintsDataList.clear();
        hintsDataList.add(new HintsData("To take screenshot of harrassment,click on screenshot button.","hintsitem/1.png"));
        hintsDataList.add(new HintsData("To upload image to server,click on upload button","hintsitem/2.png"));
        hintsDataList.add(new HintsData("To take screenshot of harrassment,click on screenshot button.","hintsitem/3.png"));
        hintsDataList.add(new HintsData("To upload image to server,click on upload button","hintsitem/4.png"));
        hintsDataList.add(new HintsData("To take screenshot of harrassment,click on screenshot button.","hintsitem/5.png"));
        hintsDataList.add(new HintsData("To upload image to server,click on upload button","hintsitem/6.png"));
        hintsDataList.add(new HintsData("To take screenshot of harrassment,click on screenshot button.","hintsitem/7.png"));
        text.setEditable(false);
        text.setMouseTransparent(true);
        text.setFocusTraversable(false);
        text.setText("fgdfgjbmd,fn kgjdghkdhgj\n\n\n\ndrfgdgfgfgfgggggggg  g d dg   fdfd  dhg dk dghjdkghd ");
        text.getStylesheets().add(getClass().getResource("button.css").toExternalForm());
        /*close.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if(event.getEventType()==MouseEvent.MOUSE_PRESSED)
                {
                    close.setStyle("{-fx-border-width:2;-fx-border-color:#124321;-fx-background-color:#9887aa;-fx-font-size:22;-fx-text-fill:#ff3333;}");
                }
                if(event.getEventType()==MouseEvent.MOUSE_RELEASED)
                {
                    close.setStyle("{-fx-border-width:2;-fx-border-color:#124321;-fx-background-color:#ffffff;-fx-font-size:2;-fx-text-fill:#ff3333;}");
                }
            }
        });*/
        close.getStylesheets().add(getClass().getResource("button.css").toExternalForm());
        previous.getStylesheets().add(getClass().getResource("button.css").toExternalForm());
        next.getStylesheets().add(getClass().getResource("button.css").toExternalForm());
        previous.setVisible(false);
        index=0;
        next.setTranslateX(next.getTranslateX()-50);
        close.setTranslateX(close.getTranslateX()-50);
        loadNewHints();
    }
    @FXML
    public void nextAction(ActionEvent actionEvent) {
        if(index>=hintsDataList.size()-1)
        {
            return;
        }
        else
        {
            index++;
            if(index==hintsDataList.size()-1)
            {
                next.setVisible(false);
                previous.setTranslateX(previous.getTranslateX()+50);
                close.setTranslateX(close.getTranslateX()-50);
            }

            if(!previous.isVisible())
            {
                previous.setVisible(true);
                next.setTranslateX(next.getTranslateX()+50);
                close.setTranslateX(close.getTranslateX()+50);
            }
            loadNewHints();
        }
    }
    @FXML
    public void previousAction(ActionEvent actionEvent) {
        if(index<=0)
        {
            return;
        }
        else
        {
            index--;
            if(index==0)
            {
                previous.setVisible(false);
                next.setTranslateX(next.getTranslateX()-50);
                close.setTranslateX(close.getTranslateX()-50);
            }
            if(!next.isVisible())
            {
                next.setVisible(true);
                previous.setTranslateX(previous.getTranslateX()-50);
                close.setTranslateX(close.getTranslateX()+50);
            }
            loadNewHints();
        }
    }

    private void loadNewHints() {
        text.setText(hintsDataList.get(index).text);
        File f=new File(hintsDataList.get(index).iname);
        imagew.setImage(new Image(f.toURI().toString()));
        imagew.setPreserveRatio(true);
    }

    @FXML
    public void closeAction(ActionEvent actionEvent) {
        stage.close();
    }
}
