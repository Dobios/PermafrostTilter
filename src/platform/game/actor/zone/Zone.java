package platform.game.actor.zone;

import platform.game.actor.Actor;
import platform.util.Box;
import platform.util.Vector;

public class Zone extends Actor {
	private Box box;

	public Zone(Box box) {
		setPriority(56);
		this.box = box;
	}
	
	public Box getBox() {
		return box;
	}
	
	public Vector getPosition(){
		return box.getCenter();
	}

}
