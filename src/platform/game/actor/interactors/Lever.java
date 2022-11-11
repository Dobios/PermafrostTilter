package platform.game.actor.interactors;

import platform.game.actor.Actor;
import platform.game.actor.Damage;
import platform.game.signals.*;
import platform.util.Vector;
import platform.util.Box;
import platform.util.Input;
import platform.util.Output;
import platform.util.Sprite;

public class Lever extends Actor implements Signal{
	private boolean value = false;
	private double duration;
	private double time = 0;
	private Vector position;
	private double width = 0.5;
	private double height = 0.5;

	public Lever(Vector position, double duration) {
		this.setPriority(22);
		//set duration to Double.POSITIVE_INFINITY to remove the timer functionality
		this.duration = duration;
		this.position = position;
	}

	@Override
	public void update(Input input) {
		super.update(input);
		if (time > 0) {
			time -= input.getDeltaTime();
		} else if (time <= 0) {
			value = false;
		}
	}
	
	public Sprite setSprite() {
		if (value) {
			return getSprite("lever.left");
		} else {
			return getSprite("lever.right");
		}
	}
	
	public void draw(Input input, Output output) {
		output.drawSprite(setSprite(), getBox());
}
	@Override
	public boolean isActive() {
		return value;
	}
	
	public Box getBox() { 
		return new Box(position, width, height);
	}
	public boolean isInteract(){
		return true;
	}

	@Override
	public boolean hurt(Actor instigator, Damage type, double amount, Vector location) {
		switch (type) {
			case ACTIVATION:
				value = !value;
				time = duration;
				return true;
			default:
				return false;
		}
	}
}
