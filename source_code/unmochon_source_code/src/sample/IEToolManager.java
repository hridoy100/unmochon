package sample;

import javafx.event.EventHandler;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

import static sample.NewFirstController.editColor;

public class IEToolManager implements EventHandler<MouseEvent> {
	
	private 	   IETool 		 curTool;
	private 	   Color  		 curColor;
	
	public IEToolManager()
	{
		curColor = Color.RED;
		curTool  = new IEPencil();
	}

	public void setCurTool(IETool newTool)
	{
		curTool = newTool;
	}
	
	public void setCurColor(Color c)
	{
		curColor = c;
	}
	@Override
	public void handle(MouseEvent me) {
		GraphicsContext gc = ((Canvas)me.getSource()).getGraphicsContext2D();
		if(editColor!=null)
			gc.setFill(editColor);
		curTool.handleMouseAction(me, gc);
	}
}
