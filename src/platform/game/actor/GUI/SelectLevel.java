package platform.game.actor.GUI;

import platform.game.level.Level;
import platform.util.Input;
import platform.util.Vector;

public class SelectLevel extends Button{
	
	public SelectLevel(Vector position, double height, String text, Cursor cursor, Level level){
		super(position,height,text,cursor,level);
	}
	@Override
	public void update(Input input) {
		super.update(input);
		if(super.getActive() && input.getMouseButton(1).isPressed()){
			getWorld().setNextLevel(level);
			getWorld().nextLevel();
		}
	}
	
}
