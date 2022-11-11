package platform.game.actor.interactors;

import platform.game.actor.Actor;
import platform.game.signals.*;
import platform.util.Box;
import platform.util.Input;
import platform.util.Output;
import platform.util.Sprite;
import platform.util.Vector;

public class PressurePlate extends Actor implements Signal{
	private boolean value = false;
	private Vector position;
	private double width = 1;
	private double height = 0.1;

	public PressurePlate(Vector position) {
		this.setPriority(52);
		this.position = position;
	}
	
	@Override
	public void preUpdate(Input input){
		value = false;
	}

	@Override
	public void update(Input input) {
		super.update(input);

		if (value) {
			height = 0.05;
		} else {
			height = 0.2;
		}
	}

	public void interact(Actor other) {
		Vector delta = other.getBox().getCollision(getBox()); 
		if (delta!=null && other.isPlayer()) {
			value = true;
		}
	}


	public Sprite getSprite() {
		return getSprite("box.double");
	}

	public void draw(Input input, Output output) {
			output.drawSprite(getSprite(), getBox());
	}
	
	@Override
	public boolean isActive() {
		return value;
	}

	public Box getBox() { 
		return new Box(position, width, height);
	}

}
