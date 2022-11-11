package platform.game.actor.GUI;

import platform.util.Input;
import platform.util.Vector;

public class OneLife extends Button{
	
	public OneLife(Vector position, double height, Cursor cursor){
		super(position,height,"off",cursor);
	}
	
	@Override
	public void update(Input input) {
		super.update(input);
		if(super.getActive() && input.getMouseButton(1).isPressed()){
			getWorld().setRespawn(!getWorld().getRespawn());
		}
		if (getWorld().getRespawn()) {
			super.setText("on");
		} else {
			super.setText("off");
		}
	}
}
