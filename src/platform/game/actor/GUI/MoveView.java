package platform.game.actor.GUI;

import platform.util.Input;
import platform.util.Vector;

public class MoveView extends Button {
	private Vector target;

	public MoveView(Vector position, double height, String text, Cursor cursor, Vector target) {
		super(position, height, text, cursor);
		this.target = target;
	}
	
	@Override
	public void update(Input input) {
		super.update(input);
		if(super.getActive() && input.getMouseButton(1).isPressed()){
			getWorld().setView(target, 11);
		}
	}
	
	

}
