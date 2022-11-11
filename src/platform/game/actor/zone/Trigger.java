package platform.game.actor.zone;

import platform.game.actor.Actor;
import platform.game.signals.Signal;
import platform.util.Box;
import platform.util.Input;
import platform.util.Vector;

public class Trigger extends Zone implements Signal {
	private boolean active = false;

	public Trigger(Box box){
		super(box);
	}
	
	public void preUpdate(Input input){
		active = false;
	}
	
	@Override
	public boolean isActive() {
		return active;
	}

	public void interact(Actor other) {
		super.interact(other);
		Vector delta = other.getBox().getCollision(super.getBox()); 
		if(delta!=null && other.isPlayer()){
			active = true;
		}
	}

}
