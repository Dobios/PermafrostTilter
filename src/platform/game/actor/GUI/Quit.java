package platform.game.actor.GUI;

import platform.util.Input;
import platform.util.Vector;

public class Quit extends Button{
	
	public Quit(Vector position, double height, Cursor cursor){
		super(position, height, "Exit", cursor);
	}
	@Override
	public void update(Input input) {
		super.update(input);
		if(super.getActive() && input.getMouseButton(1).isPressed()){
			System.exit(0);
		}
	}
	
}
