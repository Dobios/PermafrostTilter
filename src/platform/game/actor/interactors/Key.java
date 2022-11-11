package platform.game.actor.interactors;

import platform.util.Box;
import platform.util.Input;
import platform.util.Output;
import platform.util.Sprite;
import platform.util.Vector;
import platform.game.actor.Actor;
import platform.game.actor.Player;
import platform.game.signals.*;

public class Key extends Actor implements Signal {
	private Vector position;
	private double width = 0.8;
	private double height = 0.8;
	private Keys key;
	private boolean taken = false;

	public Key(Vector position, Keys key) {
		this.setPriority(45);
		this.position = position;
		this.key = key;
	}
	public Key(Vector position,double size, Keys key) {
		this.setPriority(45);
		this.position = position;
		this.key = key;
		this.width = size;
		this.height = size;
	}

	public boolean isActive() {
		return taken;
	}
	
	public Keys getId(){
		return key;
	}
	
	public Sprite getSprite() {
		return getSprite(key.getText());
	}



	@Override
	public void update(Input input) {
		super.update(input);
	}

	@Override
	public void interact(Actor other) {
		super.interact(other);
		Vector delta = other.getBox().getCollision(getBox());
		if (!taken && delta !=null) {
			if (other.isPlayer()) {
				((Player) other).addKey(key);
				taken = true;
				getWorld().unregister(this);
			}
		}
	}

	public void draw(Input input, Output output) {
		if (!taken) {
			output.drawSprite(getSprite(), getBox());
		}
	}

	public Box getBox() { 
		return new Box(position, width, height);
	}

	public Vector getPosition() { 
		return position;
	}

}

