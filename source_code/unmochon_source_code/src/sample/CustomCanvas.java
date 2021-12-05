package sample;

import javafx.scene.canvas.Canvas;

public class CustomCanvas extends Canvas {
    CustomCanvas(double width,double height)
    {
        super(width,height);
    }
    @Override
    public boolean isResizable()
    {
        return true;
    }
    public void setup()
    {
    }
}
