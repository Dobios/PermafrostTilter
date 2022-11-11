package platform.game.actor;

import platform.util.Box;
import platform.util.Input;
import platform.util.Output;
import platform.util.Sprite;
import platform.util.Vector;

public class Spike extends Actor {

	private double cooldown;
	private Vector position;
	private double width;
	private double height = 0.5;
	private Sprite skin;
	private Box collision;

	public Spike(Vector position, double width) {
		this.setPriority(45);
		this.position = position;
		this.width = width;
		collision = new Box(position, width, height);
	}

	@Override
	public void update(Input input) {
		super.update(input);
		cooldown -= input.getDeltaTime();
	}

	@Override
	public void interact(Actor other) {
		super.interact(other);
		Vector delta = other.getBox().getCollision(getBox());
		if (cooldown <= 0 && delta !=null) {
			if (other.hurt(this, Damage.PHYSICAL, 2.5, delta)) {
				cooldown = 0.5;
			}
		}
		if(other.isPlayer()) {
			other.interact(this);
		}
	}

	public Sprite setSprite() {
		skin = getWorld().getLoader().getSprite("spikes");
		return skin;
	}

	public void draw(Input input, Output output) {
		if (width > 1) {
			for (int i = 0; i < width; ++i) {
				output.drawSprite(setSprite(), new Box(new Vector(position.getX()+i, position.getY()), 1, height));
			}
			collision = new Box(new Vector((position.getX()+0.5*(width-1)), position.getY()), width, height);
		} else { 
			output.drawSprite(setSprite(), getBox());
			collision = new Box(new Vector((position.getX()), position.getY()), width, height);
		}
	}

	public Box getBox() { 
		return collision;
	}

	@Override
	public boolean isSolid() { 
		return true;
	}

	public Vector getPosition() { 
		return position;
	}

}


