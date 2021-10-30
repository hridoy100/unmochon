package sample;

import javafx.event.EventType;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;

import static sample.NewFirstController.editColor;
import static sample.NewFirstController.pb;


public class IEPencil extends IETool {

	@Override
	public void handleMouseAction(MouseEvent me, GraphicsContext gc)
	{
		@SuppressWarnings("unchecked")
		EventType<MouseEvent> e = (EventType<MouseEvent>)me.getEventType();
		float value=1;
		value+=pb.getProgress()*50.00;
		if(editColor==null)
		{
			gc.clearRect(me.getX() - value, me.getY() - value, 2*value, 2*value);
			return;
		}
		if(e.equals(MOUSE_DRAGGED)||e.equals(MOUSE_CLICKED))
		{
			gc.fillRect(me.getX() - value, me.getY() - value, 2*value, 2*value);
		}
	}

}
