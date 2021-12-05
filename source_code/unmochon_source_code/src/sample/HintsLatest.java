package sample;

import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.awt.*;
import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

import static sample.NewFirstController.*;

public class HintsLatest implements Initializable {
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

    public static double ratio;

    List<HintsData> hintsDataList=new ArrayList<>();
    int index=0;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            hintsDataList.clear();
            hintsDataList.add(new HintsData("1. Open a browser tab and go-to messenger.\n" +
                    "2. Open our application and keep it just above the messengar tab like this.\n" +
                    "3. Click on the screenshot button and wait for a while to take a screenshot.", "/1.png"));
            hintsDataList.add(new HintsData("1. Click on Edit and you will see a color bar with eraser and brash width.\n" +
                    "2. Choose color and brush width appropriately and hide sensitive information of the image if you want.\n" +
                    "3. You can remove your drawings by using the eraser.", "/2.png"));
            hintsDataList.add(new HintsData("1. Click Upload if you want to upload the screenshot.\n" +
                    "2  A new window will appear and you can share your identification if you want.\n" +
                    "3. Click on Next to proceed.", "/3.png"));
            hintsDataList.add(new HintsData("1. You can check your provided information here.\n" +
                    "2. Click on submit if you think that all your provided information is ok.\n" +
                    "3. You need to confirm once you click on Submit.", "/4.png"));
            //hintsDataList.add(new HintsData("To take screenshot of harrassment,click on screenshot button.", "/5.png"));
            //hintsDataList.add(new HintsData("To upload image to server,click on upload button", "/6.png"));
            //hintsDataList.add(new HintsData("To take screenshot of harrassment,click on screenshot button.", "/7.png"));


            ok.getStylesheets().add(getClass().getResource("button.css").toExternalForm());
            cancel.getStylesheets().add(getClass().getResource("button.css").toExternalForm());
            output.setText("Your submission:\n\n" + name + "\n" + contact + "\n\n" + comment);
            //System.out.println();
            ok.getStylesheets().add(getClass().getResource("button.css").toExternalForm());

            //wrapper.setImage(new Image(tempfile.toURI().toString()));

            index = 0;


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
                                        double tempratio=root.getWidth()/root.getHeight();
                                        double valuew,valueh;
                                        if(tempratio<ratio)
                                        {
                                            valuew=root.getWidth()/1.7;
                                            valueh=valuew/ratio;
                                        }
                                        else
                                        {
                                            valueh=root.getHeight()/1.7;
                                            valuew=valueh*ratio;
                                        }
                                        wrapper.setFitWidth(valuew);
                                        wrapper.setFitHeight(valueh);
                                    }
                                });
                                stage.heightProperty().addListener((ChangeListener<? super Number>) new ChangeListener<Number>() {
                                    @Override
                                    public void changed(ObservableValue<? extends Number> arg0, Number arg1, Number arg2) {
                                        double tempratio=root.getWidth()/root.getHeight();
                                        double valuew,valueh;
                                        if(tempratio<ratio)
                                        {
                                            valuew=root.getWidth()/1.7;
                                            valueh=valuew/ratio;
                                        }
                                        else
                                        {
                                            valueh=root.getHeight()/1.7;
                                            valuew=valueh*ratio;
                                        }
                                        wrapper.setFitWidth(valuew);
                                        wrapper.setFitHeight(valueh);
                                    }
                                });

                                /*stage.maximizedProperty().addListener(new ChangeListener<Boolean>() {
                                    @Override
                                    public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                                        final double width,height;
                                        if(stage.isMaximized()) {
                                            GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
                                            width = gd.getDisplayMode().getWidth();
                                            height = gd.getDisplayMode().getHeight();
                                        }
                                        else
                                        {
                                            GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
                                            width = stage.getWidth();//gd.getDisplayMode().getWidth();
                                            height = stage.getHeight();//gd.getDisplayMode().getHeight();
                                        }
                                        double tempratio=width/height;
                                        double valuew,valueh;
                                        if(tempratio<ratio)
                                        {
                                            valuew=root.getWidth()/1.7;
                                            valueh=valuew/ratio;
                                        }
                                        else
                                        {
                                            valueh=root.getHeight()/1.7;
                                            valuew=valueh*ratio;
                                        }
                                        wrapper.setFitWidth(valuew);
                                        wrapper.setFitHeight(valueh);
                                    }
                                });*/

                                stage.maximizedProperty().addListener(new ChangeListener<Boolean>() {
                                    @Override
                                    public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                                        double tempratio=root.getWidth()/root.getHeight();
                                        double valuew,valueh;
                                        if(tempratio<ratio)
                                        {
                                            valuew=root.getWidth()/1.7;
                                            valueh=valuew/ratio;
                                        }
                                        else
                                        {
                                            valueh=root.getHeight()/1.7;
                                            valuew=valueh*ratio;
                                        }
                                        wrapper.setFitWidth(valuew);
                                        wrapper.setFitHeight(valueh);
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
        }catch (Exception e)
        {
            e.printStackTrace();
        }
        root.getStylesheets().add(getClass().getResource("button.css").toExternalForm());
        output.getStylesheets().add(getClass().getResource("button.css").toExternalForm());
        output.setFocusTraversable(false);
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
            output.setText("\n"+hintsDataList.get(index).text);
            File f = new File(hintsDataList.get(index).iname);
            Image image=new Image(f.toURI().toString());
            ratio=wrapper.getFitWidth()/wrapper.getFitHeight();
            System.out.println(ratio);
            wrapper.setImage(image);
            wrapper.setImage(new Image(hintsDataList.get(index).iname));
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
