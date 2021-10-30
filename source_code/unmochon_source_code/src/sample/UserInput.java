package sample;

import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.beans.EventHandler;
import java.io.File;
import java.net.URL;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Optional;
import java.util.ResourceBundle;

import static sample.Main.aClass;
import static sample.NewFirstController.*;

public class UserInput implements Initializable {
    @FXML
    Button ok,cancel;
    @FXML
    TextArea name_inp,contact_inp,comment_inp;

    public static Stage stage;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ok.getStylesheets().add(getClass().getResource("button.css").toExternalForm());
        cancel.getStylesheets().add(getClass().getResource("button.css").toExternalForm());
        //comment_inp.getLayoutBounds().(2,2,2,2);
    }

    @FXML
    void OKAction()
    {
        try {
            UserInputNext2.name=name_inp.getText().toString();
            UserInputNext2.contact=contact_inp.getText().toString();
            UserInputNext2.comment=comment_inp.getText().toString();

            double x=simage_wrapper.getScaleX();
            double y=simage_wrapper.getScaleY();
            simage_wrapper.setScaleX(1);
            simage_wrapper.setScaleY(1);


            BufferedImage bImage = SwingFXUtils.fromFXImage(simage_wrapper.snapshot(null, null), null);
            image2 = SwingFXUtils.toFXImage(bImage, null);

            simage_wrapper.setScaleX(x);
            simage_wrapper.setScaleY(y);

            FXMLLoader loader = new FXMLLoader();
            //loader.setLocation(getClass().getResource("confirmdialouge.fxml"));
            //loader.setLocation(getClass().getResource("hints.fxml"));
            loader.setLocation(aClass.getResource("user_input_next2.fxml"));
            Parent root = loader.load();
            // Loading the controller
            UserInputNext2 hintsController = loader.getController();
            //ConfirmDialouge hintsController = loader.getController();
            //firstWindowController.SetMain(main);
            // Set the primary stage
            Stage stage = new Stage();
            stage.setMinWidth(800);
            stage.setMinHeight(600);

            Scene scene = new Scene(root);
            stage.setTitle("Do you want to submit this?");
            stage.setScene(scene);
            //scene.getStylesheets().add(this.getClass().getResource("style.css").toExternalForm());
            stage.setResizable(true);
            stage.show();
            System.out.println("stage called");
            hintsController.stage=stage;
            ///////////stage.setIconified(true);
        }catch (Exception e)
        {
            e.printStackTrace();
            System.out.println(""+e.getMessage());
        }
    }

    @FXML
    void CancelAction()
    {
        stage.close();
    }

}
