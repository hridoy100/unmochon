package sample;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;

import static sample.NewFirstController.canvas;
import static sample.NewFirstController.dcanvas;

public class NewDrawings {
    public static Canvas createNewCanvas(Image img,IEToolManager toolManager)
    {
//		Canvas canvas = new Canvas(img.getWidth(), img.getHeight());
        /*CustomCanvas canvas = new CustomCanvas(img.getWidth(), img.getHeight());
        //Canvas canvas=new Canvas(1555,1555);*/
        GraphicsContext gc = dcanvas.getGraphicsContext2D();
        //gc.drawImage(img, 0, 0);
        dcanvas.addEventHandler(MouseEvent.MOUSE_DRAGGED, toolManager);
        dcanvas.addEventHandler(MouseEvent.MOUSE_CLICKED, toolManager);
        dcanvas.addEventHandler(MouseEvent.MOUSE_DRAGGED, toolManager);
        dcanvas.addEventHandler(MouseEvent.MOUSE_CLICKED, toolManager);
        //canvas.getGraphicsContext2D().clearRect(0,0,600,600);
        return dcanvas;
    }
}
