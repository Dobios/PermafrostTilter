package platform.game.actor.block;

import platform.util.Input;
import platform.util.Vector;
import platform.game.actor.projectiles.Arrow;
import platform.game.signals.*;


public class Trap extends Block {
	private double cooldown = 0;
	private Signal triggered;
	
	public Trap(Vector position, Signal signal, String skin) {
		super(position, 1, 1, skin);
		triggered = signal;
	}
	
	@Override 
	public void update (Input input) {
		super.update(input);
		cooldown -= input.getDeltaTime();
		if (triggered.isActive()) {
			if (cooldown <= 0) {
			this.getWorld().register(new Arrow(getPosition(), new Vector(20, 0),this));
			cooldown = 3;
			}
		}
	}
}
