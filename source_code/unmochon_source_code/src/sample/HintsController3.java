package sample;

import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import static java.lang.Math.min;
import static sample.NewFirstController.window_height;
import static sample.NewFirstController.window_width;

public class HintsController3 implements Initializable {
    @FXML
    TextArea text;
    @FXML
    ImageView imagew;
    @FXML
    Image image;
    @FXML
    Button previous,next,close;
    @FXML
    AnchorPane root;

    @FXML
    Canvas canvas;

    @FXML
    StackPane imageWrapper;

    public static Canvas scanvas;


    List<HintsData> hintsDataList=new ArrayList<>();
    public static int index=0;
    public static Stage stage;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        scanvas=canvas;
        hintsDataList.clear();
        hintsDataList.add(new HintsData("To take screenshot of harrassment,click on screenshot button.","/1.png"));
        hintsDataList.add(new HintsData("To upload image to server,click on upload button","/2.png"));
        hintsDataList.add(new HintsData("To take screenshot of harrassment,click on screenshot button.","/3.png"));
        hintsDataList.add(new HintsData("To upload image to server,click on upload button","/4.png"));
        hintsDataList.add(new HintsData("To take screenshot of harrassment,click on screenshot button.","/5.png"));
        hintsDataList.add(new HintsData("To upload image to server,click on upload button","/6.png"));
        hintsDataList.add(new HintsData("To take screenshot of harrassment,click on screenshot button.","/7.png"));
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
        previous.setVisible(true);
        next.setVisible(false);
        index=0;


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
                                    //imagew.setFitHeight(root.getHeight()/1.15);
                                    //imagew.setFitWidth(root.getWidth()/1.1);
                                    imageWrapper.setScaleX(min(0.4*root.getWidth()/window_width,0.4*root.getHeight()/window_height));
                                    imageWrapper.setScaleY(min(0.4*root.getWidth()/window_width,0.4*root.getHeight()/window_height));
                                }
                            });
                            stage.heightProperty().addListener((ChangeListener<? super Number>) new ChangeListener<Number>() {
                                @Override
                                public void changed(ObservableValue<? extends Number> arg0, Number arg1, Number arg2) {
                                    //imagew.setFitHeight(root.getHeight()/1.15);
                                    //imagew.setFitWidth(root.getWidth()/1.1);
                                    imageWrapper.setScaleX(min(0.4*root.getWidth()/window_width,0.4*root.getHeight()/window_height));
                                    imageWrapper.setScaleY(min(0.4*root.getWidth()/window_width,0.4*root.getHeight()/window_height));
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

        loadNewHints();
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
                loadNewHints();
            }
        }
    }
    @FXML
    public void previousAction(ActionEvent actionEvent) {
        if(index==0)
        {
            if (index==0){
                previous.setText("Previous");
                close.setText("Next");
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
                close.setText("Finish");
                previous.setText("Next");
            }
        }
        loadNewHints();
    }

    private void loadNewHints() {
        text.setText(hintsDataList.get(index).text);
        File f=new File(hintsDataList.get(index).iname);
        //imagew.setImage(new Image(f.toURI().toString()));
        //imagew.setImage(new Image(hintsDataList.get(index).iname));
        //imagew.setPreserveRatio(true);
        final Image image =new Image("2.png");// new Image(hintsDataList.get(index).iname);
        scanvas.setWidth(image.getWidth());
        scanvas.setHeight(image.getHeight());
        scanvas.getGraphicsContext2D().drawImage(image, 0, 0, image.getWidth(), image.getHeight());

    }

    @FXML
    public void nextAction(ActionEvent actionEvent) {
        stage.close();
    }
}
