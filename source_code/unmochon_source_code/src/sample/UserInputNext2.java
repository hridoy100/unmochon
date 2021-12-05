package sample;

import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.net.URL;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Optional;
import java.util.ResourceBundle;

import static sample.NewFirstController.*;

public class UserInputNext2 implements Initializable {
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

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            UserInput.stage.close();
            UserInput.stage=null;
            ok.getStylesheets().add(getClass().getResource("button.css").toExternalForm());
            cancel.getStylesheets().add(getClass().getResource("button.css").toExternalForm());
            output.setText("Name: " + name + "\nPhone/Mail: " + contact + "\nComment:\n" + comment);
            //System.out.println();
            root.getStylesheets().add(getClass().getResource("button.css").toExternalForm());

            //ok.getStylesheets().add(getClass().getResource("button.css").toExternalForm());

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
                                /*stage.widthProperty().addListener((ChangeListener<? super Number>) new ChangeListener<Number>() {
                                    @Override
                                    public void changed(ObservableValue<? extends Number> arg0, Number arg1, Number arg2) {
                                        wrapper.setFitWidth(root.getWidth()/1.1);
                                        wrapper.setFitHeight(root.getHeight()/1.1);
                                        ratio=wrapper.getFitWidth()/wrapper.getFitHeight();
                                    }
                                });*/


                                stage.widthProperty().addListener((ChangeListener<? super Number>) new ChangeListener<Number>() {
                                    @Override
                                    public void changed(ObservableValue<? extends Number> arg0, Number arg1, Number arg2) {
                                        double tempratio=root.getWidth()/root.getHeight();
                                        double valuew,valueh;
                                        if(tempratio<ratio)
                                        {
                                            valuew=root.getWidth()/1.8;
                                            valueh=valuew/ratio;
                                        }
                                        else
                                        {
                                            valueh=root.getHeight()/1.8;
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
                                            valuew=root.getWidth()/1.8;
                                            valueh=valuew/ratio;
                                        }
                                        else
                                        {
                                            valueh=root.getHeight()/1.8;
                                            valuew=valueh*ratio;
                                        }
                                        wrapper.setFitWidth(valuew);
                                        wrapper.setFitHeight(valueh);
                                    }
                                });

                                stage.maximizedProperty().addListener(new ChangeListener<Boolean>() {
                                    @Override
                                    public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                                        //output.setText("maximuzed calleed");
                                        Thread runnable=new Thread() {
                                            @Override
                                            public void run() {
                                                Platform.runLater(new Runnable() {
                                                    @Override
                                                    public void run() {
                                                        if(stage.isMaximized()) {
                                                            GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
                                                            final double width = gd.getDisplayMode().getWidth();
                                                            final double height = gd.getDisplayMode().getHeight();
                                                            //output.setText(width+"maximuzed calleed"+height);
                                                            double tempratio=width/height;
                                                            double valuew,valueh;
                                                            if(tempratio<ratio)
                                                            {
                                                                valuew=stage.getWidth()/1.8;
                                                                valueh=valuew/ratio;
                                                            }
                                                            else
                                                            {
                                                                valueh=stage.getHeight()/1.8;
                                                                valuew=valueh*ratio;
                                                            }
                                                            wrapper.setFitWidth(valuew);
                                                            wrapper.setFitHeight(valueh);

                                                        }
                                                        else
                                                        {
                                                            //output.setText("maximuzed calleed");
                                                            GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
                                                            final double width = stage.getWidth();//gd.getDisplayMode().getWidth();
                                                            final double height = stage.getHeight();//gd.getDisplayMode().getHeight();

                                                            double tempratio=width/height;
                                                            double valuew,valueh;
                                                            if(tempratio<ratio)
                                                            {
                                                                valuew=stage.getWidth()/1.8;
                                                                valueh=valuew/ratio;
                                                            }
                                                            else
                                                            {
                                                                valueh=stage.getHeight()/1.8;
                                                                valuew=valueh*ratio;
                                                            }
                                                            wrapper.setFitWidth(valuew);
                                                            wrapper.setFitHeight(valueh);
                                                        }
                                                    }
                                                });
                                            }
                                        };
                                        runnable.start();
                                    }
                                });
/*
                                stage.maximizedProperty().addListener(new ChangeListener<Boolean>() {
                                    @Override
                                    public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                                        double tempratio=root.getWidth()/root.getHeight();
                                        double valuew,valueh;
                                        if(tempratio<ratio)
                                        {
                                            valuew=root.getWidth()/1.8;
                                            valueh=valuew/ratio;
                                        }
                                        else
                                        {
                                            valueh=root.getHeight()/1.8;
                                            valuew=valueh*ratio;
                                        }
                                        wrapper.setFitWidth(valuew);
                                        wrapper.setFitHeight(valueh);
                                    }
                                });*/
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
        output.getStylesheets().add(getClass().getResource("button3.css").toExternalForm());
        //raf.getStylesheets().add(getClass().getResource("button.css").toExternalForm());
        //commentt.getStylesheets().add(getClass().getResource("text.css").toExternalForm());
        //contactt.getStylesheets().add(getClass().getResource("text.css").toExternalForm());
        ratio=wrapper.getFitWidth()/wrapper.getFitHeight();
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
                        try {
                            Date date = new Date();
                            //getTime() returns current time in milliseconds
                            long time = date.getTime();
                            Timestamp ts = new Timestamp(time);
                            String timeStamp = ts.toString();
                            timeStamp = timeStamp.substring(0, timeStamp.indexOf("."));
                            timeStamp = timeStamp.replaceAll(":", " ");
                            String fileName = timeStamp + ".png";
                            fileName = fileName.replaceAll("[ ]+", "");
                            fileName = fileName.replaceAll("[-]+", "");
                            //fileName="2.png";
                            File outputFile = new File(fileName);

                            BufferedImage bImage = SwingFXUtils.fromFXImage(simage_wrapper.snapshot(null, null), null);
                            //image2 = SwingFXUtils.toFXImage(bImage, null);
                            ImageIO.write(bImage, "png", outputFile);
                            tempfile = outputFile;
                        }catch (Exception e)
                        {
                            e.printStackTrace();
                        }

                        uploadToServer(name,contact,comment);
                        ////upload alert,reset kora
                        stage.close();
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
            //stage.close();
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
