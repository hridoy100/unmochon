package sample;

import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.awt.*;
import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class HintsLatest2 implements Initializable {
    @FXML
    Button ok,cancel;
    @FXML
    TextArea output;
    @FXML
    Label head;
    @FXML
    AnchorPane root;
    @FXML
    AnchorPane nimage;
    @FXML
    Canvas canvas;

    public static Stage stage;
    public static String name="",contact="",comment="";

    public static double ratio;

    List<HintsData> hintsDataList=new ArrayList<>();
    int index=0;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            hintsDataList.clear();
            hintsDataList.add(new HintsData("\u2022 Open a browser tab and go-to messenger.\n" +
                    "\u2022 Open our application and keep it just above the messengar tab like this.\n" +
                    "\u2022 Click on the screenshot button and wait for a while to take a screenshot.", "/1.png"));
            hintsDataList.add(new HintsData("\u2022 Click on Edit and you will see a color bar with eraser and brash width.\n" +
                    "\u2022 Choose color and brush width appropriately and hide sensitive information of the image if you want.\n" +
                    "\u2022 You can remove your drawings by using the eraser.", "/2.png"));
            hintsDataList.add(new HintsData("\u2022 Click Upload if you want to upload the screenshot.\n" +
                    "\u2022  A new window will appear and you can share your identification if you want.\n" +
                    "\u2022 Click on Next to proceed.", "/3.png"));
            hintsDataList.add(new HintsData("\u2022 You can check your provided information here.\n" +
                    "\u2022 Click on submit if you think that all your provided information is ok.\n" +
                    "\u2022 You need to confirm once you click on Submit.", "/4.png"));
            //hintsDataList.add(new HintsData("To take screenshot of harrassment,click on screenshot button.", "/5.png"));
            //hintsDataList.add(new HintsData("To upload image to server,click on upload button", "/6.png"));
            //hintsDataList.add(new HintsData("To take screenshot of harrassment,click on screenshot button.", "/7.png"));


            ok.getStylesheets().add(getClass().getResource("button.css").toExternalForm());
            cancel.getStylesheets().add(getClass().getResource("button.css").toExternalForm());

            ok.getStylesheets().add(getClass().getResource("button.css").toExternalForm());

            //wrapper.setImage(new Image(tempfile.toURI().toString()));

            index = 0;

            loadNewHints();
        }catch (Exception e)
        {
            e.printStackTrace();
        }
        root.getStylesheets().add(getClass().getResource("button.css").toExternalForm());
        output.getStylesheets().add(getClass().getResource("button3.css").toExternalForm());
        head.getStylesheets().add(getClass().getResource("button3.css").toExternalForm());
        head.setFocusTraversable(false);
        output.setFocusTraversable(false);
        //head.setEditable(false);
        //head.setMouseTransparent(true);
    }


    @FXML
    public void closeAction(ActionEvent actionEvent) {
        if(index>hintsDataList.size()-1)
        {
            return;
        }
        else
        {
            if(index==hintsDataList.size()-1)
            {
                stage.close();
            }
            else if (index==0){
                stage.close();
            }
            else
            {
                index++;
                if(index==hintsDataList.size()-1)
                {
                    ok.setText("Finish");
                    cancel.setText("Previous");
                }
                loadNewHints();
            }
        }
    }
    @FXML
    public void previousAction(ActionEvent actionEvent) {
        if(index==0)
        {
            if (index==0){
                cancel.setText("Previous");
                ok.setText("Next");
                index++;
            }
        }
        else
        {
            index--;
            if(index==0)
            {
                //next.setVisible(false);
                //previous.setTranslateX(previous.getTranslateX()+50);
                //close.setTranslateX(close.getTranslateX()-50);
                ok.setText("Finish");
                cancel.setText("Next");
            }
            else if(index==hintsDataList.size()-2)
            {
                ok.setText("Next");
                cancel.setText("Previous");
            }
        }
        loadNewHints();
    }

    private void loadNewHints() {
        try {
            output.setText(hintsDataList.get(index).text);
            File f = new File(hintsDataList.get(index).iname);
            //Image image=new Image(f.toURI().toString());
            Image image=new Image(hintsDataList.get(index).iname);
            //ratio=wrapper.getFitWidth()/wrapper.getFitHeight();
            //System.out.println(ratio);
            //wrapper.setImage(image);
            //wrapper.setImage(new Image(hintsDataList.get(index).iname));
            canvas.setHeight(image.getHeight()/2.00);
            canvas.setWidth(image.getWidth()/2.00);
            nimage.setPrefHeight(image.getHeight()/2.00);
            nimage.setPrefWidth(image.getWidth()/2.00);
            canvas.getGraphicsContext2D().drawImage(image,0,0,image.getWidth()/2.00,image.getHeight()/2.00);

            head.setText("Step: 0"+(index+1));

            //canvas.setWidth(300);
            //canvas.setHeight(200);
            //canvas.getGraphicsContext2D().drawImage(image, 0, 0,300,200);
            //wrapper.setPreserveRatio(true);
            //final Image image = new Image(hintsDataList.get(index).iname);
            //wrapper.pr(image.getWidth());
            //wr.setHeight(image.getHeight());
            //scanvas.getGraphicsContext2D().drawImage(image, 0, 0, image.getWidth(), image.getHeight());
        }catch (Exception e)
        {
            e.printStackTrace();
        }
    }

}
