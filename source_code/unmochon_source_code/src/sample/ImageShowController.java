package sample;

import javafx.fxml.FXML;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Screen;


public class ImageShowController {
    private Main main;

    @FXML
    ImageView imageView;

    public void SetMain(Main main) {
        this.main = main;
    }
    public void setImageView(Image img){
        Rectangle2D visualBounds = Screen.getPrimary().getVisualBounds();
        imageView.setImage(img);
        imageView.setFitHeight(visualBounds.getHeight());
        imageView.setFitWidth(visualBounds.getWidth());
    }

}
